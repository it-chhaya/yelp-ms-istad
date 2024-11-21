import SockJS from 'sockjs-client';
import { Client } from '@stomp/stompjs';

export class WebSocketService {
    constructor() {
        this.client = new Client({
            webSocketFactory: () => new SockJS('http://localhost:8080/ws'),
            onConnect: () => {
                console.log('Connected to WebSocket');
                this.subscribe();
            },
            onStompError: (frame) => {
                console.error('STOMP error', frame);
            }
        });

        this.subscriptionCallback = null;
    }

    connect() {
        this.client.activate();
    }

    disconnect() {
        this.client.deactivate();
    }

    onNotification(callback) {
        this.subscriptionCallback = callback;
        if (this.client.connected) {
            this.subscribe();
        }
    }

    subscribe() {
        if (this.subscriptionCallback) {
            this.client.subscribe('/topic/notifications', (message) => {
                const notification = JSON.parse(message.body);
                this.subscriptionCallback(notification);
            });
        }
    }
}