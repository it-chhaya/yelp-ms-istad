'use client';

import { useState, useEffect } from 'react';

export default function KafkaConsumer() {
    const [messages, setMessages] = useState([]);
    const [connectionStatus, setConnectionStatus] = useState('Connecting');
    const [ws, setWs] = useState(null);
    const [error, setError] = useState(null);

    useEffect(() => {
        const websocket = new WebSocket('ws://localhost:8080/kafka');

        websocket.onopen = () => {
            setConnectionStatus('Connected');
            setError(null);
            setWs(websocket);
        };

        websocket.onclose = () => {
            setConnectionStatus('Disconnected');
            setWs(null);
            // Try to reconnect after 3 seconds
            setTimeout(() => {
                setConnectionStatus('Reconnecting');
            }, 3000);
        };

        websocket.onerror = (error) => {
            console.error('WebSocket error:', error);
            setError('Failed to connect to server');
            setConnectionStatus('Error');
        };

        websocket.onmessage = (event) => {
            try {
                const message = JSON.parse(event.data);
                setMessages(prev => [message, ...prev].slice(0, 100));
            } catch (error) {
                console.error('Failed to parse message:', error);
            }
        };

        setWs(websocket);

        return () => {
            if (websocket) {
                websocket.close();
            }
        };
    }, []);

    const sendMessage = (content) => {
        if (ws && ws.readyState === WebSocket.OPEN) {
            const message = {
                id: crypto.randomUUID(),
                content,
                timestamp: Date.now(),
                status: 'NEW'
            };
            ws.send(JSON.stringify(message));
        } else {
            setError('Not connected to server');
        }
    };

    return (
        <div className="p-6 max-w-4xl mx-auto">
            <h1>Hello</h1>
            <h1>{connectionStatus}</h1>
            <h1>{messages}</h1>
        </div>
    );
}