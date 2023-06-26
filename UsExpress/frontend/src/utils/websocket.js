import SockJS from 'sockjs-client';
import { Stomp } from '@stomp/stompjs';

let stompClient = null;


export function connectWebSocket(funCallback) {
  const socket = new SockJS('http://localhost:7070/websocket');
  stompClient = Stomp.over(socket);
  stompClient.connect({}, () => {
    // WebSocket connection successful
    console.log("WebSocket connected");
    if (funCallback && typeof funCallback === 'function') {
      funCallback();
    }
  }, onWebSocketError);
}


function onWebSocketError(error) {
  // WebSocket error handling
  console.log("WebSocketError : ", error);
}

export function sendMessage(destination, body) {
  if (stompClient && stompClient.connected) {
    stompClient.send(destination, {}, body);
  }
}

export function subscribeToDestination(destination, callback) {
  if (stompClient && stompClient.connected) {
    stompClient.subscribe(destination, callback);
  }
}

export function disconnectWebSocket() {
  if (stompClient) {
    stompClient.disconnect(() => {
      console.log('WebSocket disconnected');
      stompClient = null;
    });
  }
}