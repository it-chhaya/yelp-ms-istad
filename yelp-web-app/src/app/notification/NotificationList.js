'use client'

import React, { useEffect, useState } from 'react';
import {Card, CardContent} from "@/components/ui/card";

// interface Notification {
//     id: string;
//     message: string;
//     type: string;
//     userId: string;
//     timestamp: string;
//     read: boolean;
// }

export default function NotificationList() {
    const [notifications, setNotifications] = useState([]);
    const userId = "user123"; // In real app, get from auth context

    useEffect(() => {
        const eventSource = new EventSource(
            `http://localhost:8080/api/notifications/stream/${userId}`
        );

        eventSource.onmessage = (event) => {
            const notification = JSON.parse(event.data);
            setNotifications((prevNotifications) => [notification, ...prevNotifications]);
        };

        eventSource.onerror = (error) => {
            console.error('EventSource failed:', error);
            eventSource.close();
        };

        return () => {
            eventSource.close();
        };
    }, []);

    return (
        <div className="w-full max-w-md mx-auto space-y-4 p-4">
            <h2 className="text-2xl font-bold mb-4">Notifications</h2>
            {notifications.map((notification) => (
                <Card key={notification.id} className={`${notification.read ? 'opacity-70' : ''}`}>
                    <CardContent className="p-4">
                        <div className="flex justify-between items-start">
                            <p className="font-medium">{notification.message}</p>
                            <span className="text-sm text-gray-500">
                {new Date(notification.timestamp).toLocaleTimeString()}
              </span>
                        </div>
                        <div className="mt-2">
              <span className="inline-block px-2 py-1 text-sm rounded-full bg-blue-100 text-blue-800">
                {notification.type}
              </span>
                        </div>
                    </CardContent>
                </Card>
            ))}
        </div>
    );
}