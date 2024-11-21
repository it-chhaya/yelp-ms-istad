import {useCallback, useEffect, useState} from "react";

export function useWebSocket(url) {
    const [ws, setWs] = useState(null);
    const [lastMessage, setLastMessage] = useState(null);
    const [connectionStatus, setConnectionStatus] = useState('Connecting');

    useEffect(() => {
        const websocket = new WebSocket(url);

        websocket.onopen = () => {
            setConnectionStatus('Connected');
        };

        websocket.onclose = () => {
            setConnectionStatus('Disconnected');
            // Attempt to reconnect after 3 seconds
            setTimeout(() => {
                setConnectionStatus('Reconnecting');
            }, 3000);
        };

        websocket.onerror = (error) => {
            console.error('WebSocket error:', error);
            setConnectionStatus('Error');
        };

        websocket.onmessage = (event) => {
            setLastMessage(event.data);
        };

        setWs(websocket);

        return () => {
            websocket.close();
        };
    }, [setWs, url]);

    const sendMessage = useCallback(
        (message) => {
            if (ws && ws.readyState === WebSocket.OPEN) {
                ws.send(message);
            }
        },
        [ws]
    );

    return { lastMessage, connectionStatus, sendMessage };
}