"use client"

import { useEffect, useState } from 'react';
import NotificationList from "@/app/mvc/_components/NotificationList";
import {WebSocketService} from "@/lib/websocket";

export default function Home() {
    const [notifications, setNotifications] = useState([]);

    useEffect(() => {
        const wsService = new WebSocketService();

        wsService.onNotification((notification) => {
            setNotifications(prev => [notification, ...prev]);
        });

        wsService.connect();

        return () => {
            wsService.disconnect();
        };
    }, []);

    return (
        <div className="container mx-auto px-4 py-8">
            <h1 className="text-3xl font-bold mb-8">Notifications</h1>
            <NotificationList notifications={notifications} />
        </div>
    );
}