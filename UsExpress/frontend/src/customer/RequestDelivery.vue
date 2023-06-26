<template>
    <div>

        <!--Request Delivery Form-->
        <form @submit.prevent="handleSubmit">

            <label>customer_id: </label>
            <input type="text" required v-model="newRequestDeli.customerId" readonly>
            <label>receiver_name: </label>
            <input type="text" required v-model="newRequestDeli.receiver_name">
            <label>receiver_phone_no: </label>
            <input type="tel" required v-model="newRequestDeli.receiver_phone_no">

            <div class="map">
                <l-map style="height: 350px" :zoom="zoom" :center="center" @click="handleMapClick">
                    <l-tile-layer :url="url" :attribution="attribution"></l-tile-layer>

                    <l-circle :lat-lng="circle.center" :radius="circle.radius" :color="circle.color" />
                    <l-marker :lat-lng="circle.center"></l-marker>
                    <l-marker v-for="(marker, index) in markers" :key="index" :lat-lng="marker" ref="markerRef"
                        :draggable="true" @dragend="handleMarkerDragEnd(index, $event)">
                    </l-marker>
                </l-map>
                <button class="addressButton" @click="sendApiAddresses">Confirm pick up and destination</button>
            </div>

            <label>weight: </label>
            <input type="tel" required v-model="newRequestDeli.item_weight">

            <label>distance: </label>
            <input type="text" v-model="newRequestDeli.distance" readonly>

            <label>cost: </label>
            <input type="text" required v-model="newRequestDeli.cost">


            <div class="submit">
                <button>Create an account</button>
            </div>


        </form>
    </div>
</template>
  
<script>
import { computed } from 'vue';
import { mapActions } from 'vuex';
import { LMap, LTileLayer, LCircle, LMarker } from 'vue2-leaflet';
import api from '../utils/api.js'

export default {
    name: "FormTest",

    data() {
        return {
            url: 'https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png',
            attribution:
                '&copy; <a target="_blank" href="http://osm.org/copyright">OpenStreetMap</a> contributors',
            zoom: 15,
            center: [0, 0],
            circle: {
                center: [0, 0],
                radius: 1000, //metre
                color: 'red',
            },
            markers: [],

            newRequestDeli: {
                customerId: this.$route.params.id,
                receiver_name: '',
                receiver_phone_no: '',
                pickup_address: {
                    pickUpAddressId: '',
                },
                destination_address: {
                    destinationAddressId: '',
                },
                distance: '',
                cost: '',
                item_weight: '',


            },
            pickup_address: {
                latitude: '',
                longitude: '',
            },
            destination_address: {
                latitude: '',
                longitude: '',
            },


        }
    },

    components: {
        LMap,
        LTileLayer,
        LCircle,
        LMarker,
    },
    mounted() {
        navigator.geolocation.getCurrentPosition(pos => {
            const { latitude, longitude } = pos.coords;
            this.center = [latitude, longitude];
            this.circle.center = [latitude, longitude];
            this.pickup_address.latitude = latitude;
            this.pickup_address.longitude = longitude;
            console.log(this.circle.center)
        });
    },
    computed: {
        findDistance() {
            if (this.markers.length === 1) {
                const [lat1, lng1] = this.center;//pickup
                const [lat2, lng2] = this.markers[0];//destination

                // Haversine formula to calculate distance between two points on a sphere (Earth)
                const R = 6371e3; // Earth's radius in meters
                const φ1 = (lat1 * Math.PI) / 180;
                const φ2 = (lat2 * Math.PI) / 180;
                const Δφ = ((lat2 - lat1) * Math.PI) / 180;
                const Δλ = ((lng2 - lng1) * Math.PI) / 180;

                const a =
                    Math.sin(Δφ / 2) * Math.sin(Δφ / 2) +
                    Math.cos(φ1) * Math.cos(φ2) * Math.sin(Δλ / 2) * Math.sin(Δλ / 2);
                const c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

                const distance = R * c;
                return distance.toFixed(2);
            }
            return 0;
        },


    },
    watch: {
        markers: {
            handler(newMarkers) {
                if (newMarkers.length === 1) {
                    // Update the distance property of the newRequestDeli object
                    this.$set(this.newRequestDeli, 'distance', this.findDistance);
                }
            },
            deep: true,//to keep watching
        },
    },
    methods: {

        ...mapActions(['updateNearRiders']),
        async sendApiAddresses() {
            const pickupResponse = await api.save("pickup/save", {
                latitude: this.pickup_address.latitude,
                longitude: this.pickup_address.longitude
            })// "path/" and body

            const destinationResponse = await api.save("destination/save", {
                latitude: this.destination_address.latitude,
                longitude: this.destination_address.longitude
            })


            if (pickupResponse.status == 202 && destinationResponse.status == 202) {
                const pickUpData = await pickupResponse.json()
                const destinationData = await destinationResponse.json()
                this.newRequestDeli.pickup_address.pickUpAddressId = pickUpData.pickUpAddressId
                this.newRequestDeli.destination_address.destinationAddressId = destinationData.destinationAddressId

                //test
                console.log("pickupAddress ID : ", this.newRequestDeli.pickup_address.pickUpAddressId);
                console.log("destinationAddress ID : ", this.newRequestDeli.destination_address.destinationAddressId);

            }
            else {
                alert("Something Wrong at saving addresses")
            }

        },
        async handleSubmit() {

            //assigning the object to pass backend
            const reqDeli = {
                receiver_name: this.newRequestDeli.receiver_name,
                receiver_phone_no: this.newRequestDeli.receiver_phone_no,
                item_weight: this.newRequestDeli.item_weight,
                distance: this.newRequestDeli.distance,
                cost: this.newRequestDeli.cost,
                pickupaddress: {
                    pickUpAddressId: this.newRequestDeli.pickup_address.pickUpAddressId
                },
                destinationaddress: {
                    destinationAddressId: this.newRequestDeli.destination_address.destinationAddressId
                },
                customer: {
                    customerId: this.newRequestDeli.customerId
                }
            }

            //send object and receive pickupaddress and destination address id
            const requestDeliResp = await api.save("requestdelivery/create", reqDeli);

            if (requestDeliResp) {
                const requestDeliData = await requestDeliResp.json()
                console.log("Request Deli data :", requestDeliData)
                const pickupaddressSend = requestDeliData.pickupaddress;//***to send backend
                console.log(pickupaddressSend);//showing only id
                //stompClient.send('/app/pickupaddress', {}, JSON.stringify(pickupaddressSend));

            }
            // send and receive
            const nearRiderResp = await api.save("requestdelivery/find-near-riders",{
                latitude: this.pickup_address.latitude,
                longitude: this.pickup_address.longitude
            })
            
            const nearRiders = await nearRiderResp.json();

            if(nearRiders!=null){
                console.log("This is near Riders :" , nearRiders);

                const payload = {
                    customerId : this.newRequestDeli.customerId,
                    nearRiders: nearRiders,
                }
                this.updateNearRiders(payload);
               
            }else{
                console.log("near riders : null")
            }


            //router push
            this.$router.push({ name: 'NearByRiders', params: { id: this.newRequestDeli.customerId } });
        },

        handleMapClick(e) {
            const { lat, lng } = e.latlng;
            console.log(e.latlng)

            if (this.markers.length < 1) {
                this.markers.push([lat, lng]);
            }
            this.destination_address.latitude = lat;
            this.destination_address.longitude = lng;

            console.log(computed.distance)
        },

        handleMarkerDragEnd(index, e) {
            const { lat, lng } = e.target.getLatLng();
            this.markers.splice(index, 1, [lat, lng]);//note***
            this.destination_address.latitude = lat;
            this.destination_address.longitude = lng;
        },

    },

}
</script>
  
<style scoped>
form {
    max-width: 430px;
    background: rgb(250, 246, 246);
    text-align: left;
    margin: 30px auto;
    padding: 41px;
    border-radius: 10px;
}

label {
    color: #aaa;
    display: inline-block;
    margin: 25px 0 15px;
    font-size: 0.7em;
    text-transform: uppercase;
    letter-spacing: 1px;
    font-weight: bold;
}

input,
select {
    display: block;
    padding: 10px 6px;
    width: 100%;
    box-sizing: border-box;
    border: none;
    border-bottom: 1px solid #ddd;

}

input[type="checkbox"] {
    display: inline-block;
    width: 15px;
    position: relative;
    margin: 0px 10px 0px 0px;
    top: 3px;
}

.skill {
    display: inline-block;
    margin: 25px 10px 0 0;
    padding: 7px 14px;
    background: #eee;
    border-radius: 15px;
    font-size: 15px;
    letter-spacing: 1px;
    font-weight: bold;
    color: #777;
    cursor: pointer;
}

button {
    margin: 30px 0 20px 0;
    background: #0b6dff;
    border: 0;
    padding: 10px 20px;
    border-radius: 15px;
    color: white;
    font-weight: bold;
    font-size: 1em;
    cursor: pointer;
}

.addressButton {
    margin: 30px auto;
    align-items: center;
    justify-content: center;
}



.submit {
    text-align: center;
}

.error {
    color: #ff0062;
    margin-top: 10px;
    font-size: 0.7em;
    font-weight: bold;
}
</style>