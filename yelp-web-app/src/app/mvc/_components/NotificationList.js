export default function NotificationList({ notifications }) {
    return (
        <div className="space-y-4">
            {notifications.map((notification) => (
                <div
                    key={notification.id}
                    className="p-4 border rounded-lg shadow hover:shadow-md transition"
                >
                    <div className="flex justify-between items-start">
                        <div>
                            <p className="font-medium">{notification.message}</p>
                            <p className="text-sm text-gray-500">
                                {new Date(notification.timestamp).toLocaleString()}
                            </p>
                        </div>
                        <span
                            className={`px-2 py-1 rounded text-sm ${
                                notification.type === 'INFO'
                                    ? 'bg-blue-100 text-blue-800'
                                    : notification.type === 'WARNING'
                                        ? 'bg-yellow-100 text-yellow-800'
                                        : 'bg-red-100 text-red-800'
                            }`}
                        >
              {notification.type}
            </span>
                    </div>
                </div>
            ))}
        </div>
    );
}