<template>
    <div>
      <h1>WebSocket Connection</h1>
      <div v-if="connected">
        <p>Connected to WebSocket server</p>
        <button @click="sendMessage">Send Message</button>
      </div>
      <div v-else>
        <p>Not connected to WebSocket server</p>
        <button @click="connect">Connect</button>
      </div>
    </div>
  </template>
  
  <script>
import SockJS from 'sockjs-client';
import Stomp from 'stompjs';

  
  export default {
    data() {
      return {
        connected: false,
        stompClient: null
      };
    },
    mounted() {
      // Establish WebSocket connection
      this.connect();
    },
    methods: {
      connect() {
        const socket = new SockJS('http://localhost:7070/ws');
        this.stompClient = Stomp.over(socket);
  
        this.stompClient.connect({}, () => {
          this.connected = true;
          this.stompClient.subscribe('/topic/messages', (message) => {
            console.log('Received message:', message.body );
          });
        });
      },
      sendMessage() {
        const message = 'Hello, WebSocket!';
        this.stompClient.send('/app/sendMessage', {}, message);
      }
    }
  };
  </script>
  