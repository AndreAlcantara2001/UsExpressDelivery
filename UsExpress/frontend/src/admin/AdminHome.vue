<template>
    <div class="admin-panel">
        <aside class="sidebar">
            <div class="logo">UsExpress</div>
            <ul class="nav">
                <li class="nav-item">
                    <router-link to="/admin/dashboard" class="nav-link">Dashboard</router-link>
                </li>
                <li class="nav-item">
                    <router-link to="/admin/users" class="nav-link">Users</router-link>
                </li>
            </ul>
        </aside>


<div class="rider-list-container">
        <ul class="rider-list">
            <li class="rider-card" v-for="rider in riders" :key="rider.id">
                <div class="rider-info">
                    <p class="rider-id">RiderID: {{ rider.id }}</p>
                    <h2 class="rider-name">{{ rider.name }}</h2>
                </div>
            </li>
        </ul>
    </div>

        <div class="map" style=" padding: 10px;">
            <l-map style="height: 500px" :zoom="mapProp.zoom" :center="mapProp.center">
                <l-tile-layer :url="mapProp.url" :attribution="mapProp.attribution"></l-tile-layer>
                <l-marker :icon="markerIcon" v-for="rider in riders" :key="rider.id" :lat-lng="getLatLng(rider)">
                </l-marker>
            </l-map>
        </div>




    </div>
</template>

<script>
import { connectWebSocket, subscribeToDestination, disconnectWebSocket } from '../utils/websocket.js'
import L from 'leaflet';
import { LMap, LTileLayer, LMarker } from 'vue2-leaflet';
import 'leaflet/dist/leaflet.css';

export default {
    components: {
        LMap,
        LTileLayer,
        LMarker,
    },
    data() {
        return {

            //riders array
            riders: [],

            //map properties
            mapProp: {
                zoom: 12,
                center: [0, 0],
                url: 'https://{s}.tile.openstreetmap.fr/hot/{z}/{x}/{y}.png',
                attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors, Tiles style by <a href="https://www.hotosm.org/" target="_blank">Humanitarian OpenStreetMap Team</a> hosted by <a href="https://openstreetmap.fr/" target="_blank">OpenStreetMap France</a>',
            },

            //rider icon
            markerIcon: L.icon({
                iconUrl: require('@/assets/riderbikeicon.png'),
                iconSize: [50, 50],
            }),

        }
    },

    created() {

        navigator.geolocation.getCurrentPosition(
            pos => {
                const { latitude, longitude } = pos.coords;
                this.mapProp.center = [latitude, longitude];
            }
        );


    },
    mounted() {
        connectWebSocket(this.subscribeToWebSocket);
        //setInterval(this.subscribeToWebSocket, 1000);
    },

    beforeDestroy() {

        disconnectWebSocket();
    },
    destroyed() {

        disconnectWebSocket();
    },

    methods: {
        subscribeToWebSocket() {
            subscribeToDestination('/usexpress/admin/riders-location', this.handleRiderUpdate);
        },

        handleRiderUpdate(message) {

            const messageBody = JSON.parse(message.body);
            if (messageBody.destination === '/usexpress/admin/riders-location') {
                const ridersArray = messageBody.data;
                this.riders = ridersArray;
                console.log("riders:", this.riders);
            }

        },

        getLatLng(rider) {
            return [rider.latitude, rider.longitude];
        },

    }


}

</script>
  
  
<style scoped>

.rider-list-container{
    overflow-y: scroll;
}

.rider-list {
  list-style-type: none;

}

.rider-card {
  background-color: #fff;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  padding: 16px;
  margin-bottom: 10px;
}

.rider-info {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.rider-name {
  font-size: 18px;
  font-weight: bold;
  margin-bottom: 5px;
}

.rider-id {
  color: #666;
  font-size: 14px;
}


.sidebar {
    background-color: #333;
    color: #fff;
}

.logo {
    padding: 20px;
    font-size: 24px;
    font-weight: bold;
}

.nav {
    list-style-type: none;
    padding: 0;
    margin: 20px;
}

.nav-item {
    margin-bottom: 10px;
}

.nav-link {
    color: #fff;
    text-decoration: none;
}
</style>
  