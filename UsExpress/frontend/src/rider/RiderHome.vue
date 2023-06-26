<template>
    <div>
        <h3>Rider id : {{ id }}</h3>

        <div class="map">
            <l-map style="height: 350px" :zoom="mapProp.zoom" :center="mapProp.center">
                <l-tile-layer :url="mapProp.url" :attribution="mapProp.attribution"></l-tile-layer>
                <l-marker :icon="markerIcon" :lat-lng="mapProp.center"></l-marker>
            </l-map>
        </div>

    </div>
</template>

<script>
import { connectWebSocket, sendMessage, disconnectWebSocket} from '../utils/websocket.js'
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

            stompClient: null,

            //riderId
            id: this.$route.params.id,

            rider: {
                id: '',
                latitude: '',
                longitude: '',
            },

            //map properties
            mapProp: {
                zoom: 14,
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
                this.rider.id = this.id;
                this.rider.latitude = latitude;
                this.rider.longitude = longitude;
            }
        );
    },
    mounted() {
        connectWebSocket(this.sendWebSocketMessage);
        //setInterval(this.sendWebSocketMessage, 1000);
    },
    beforeDestroy() {

        disconnectWebSocket();
    },
    destroyed() {

        disconnectWebSocket();
    },

    methods: {
        sendWebSocketMessage() {
            sendMessage('/app/rider-data', JSON.stringify(this.rider));
        },

    }
}
</script>

<style></style>