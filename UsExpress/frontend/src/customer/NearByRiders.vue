<template>
    <div>

        <l-map style="height: 350px" :zoom="zoom" :center="center">
            <l-tile-layer :url="url" :attribution="attribution"></l-tile-layer>

            <l-circle :lat-lng="circle.center" :radius="circle.radius" :color="circle.color" />
            <l-marker :lat-lng="circle.center"></l-marker>
            <l-marker v-for="(marker, index) in markers" :key="index" :lat-lng="marker" ref="markerRef">
            </l-marker>
        </l-map>

        <button @click="showNearRiders">showNearRiders</button>

        <div>
            <ul>
                <li v-for="rider in nearRiders" :key="rider.id">
                    {{ rider.name }} - {{ rider.latitude }}
                </li>
            </ul>
        </div>
    </div>
</template>
  
<script>
import { mapGetters } from 'vuex';
import { LMap, LTileLayer, LCircle, LMarker } from 'vue2-leaflet';

export default {
    name: 'NearbyRiders',

    components: {
        LMap,
        LTileLayer,
        LCircle,
        LMarker,
    },

    data() {
        return {
            url: 'https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png',
            attribution:
                '&copy; <a target="_blank" href="http://osm.org/copyright">OpenStreetMap</a> contributors',
            zoom: 15,
            center: [16.8761033, 96.1767746],
            circle: {
                center: [16.8761033, 96.1767746],
                radius: 3000,
                color: 'red',

            },
            markers: [],

        };
    },

    mounted() {

        this.nearRiders.forEach((rider) => {
            this.markers.push([rider.latitude, rider.longitude]);
        });

    },

    computed: {
        ...mapGetters(['getNearRiders']),
        nearRiders() {
            const customerId = this.$route.params.id;
            return this.getNearRiders(customerId);
        }

    },


    methods: {
        showNearRiders() {
            console.log(this.nearRiders)
        }
    },
};
</script>