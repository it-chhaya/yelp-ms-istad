package kh.edu.cstad.notification.notification;

import com.fasterxml.jackson.databind.ObjectMapper;
import kh.edu.cstad.notification.domain.Message;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CopyOnWriteArrayList;

public class NotificationWebSocketHandler extends TextWebSocketHandler {

    private final CopyOnWriteArrayList<WebSocketSession> sessions = new CopyOnWriteArrayList<>();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void afterConnectionEstablished(@NotNull WebSocketSession session) {
        sessions.add(session);
        sendNotification(session, "Connected to WebSocket server", "INFO");
    }

    @Override
    protected void handleTextMessage(@NotNull WebSocketSession session, TextMessage message) throws Exception {

        Message notification = objectMapper.readValue(message.getPayload(), Message.class);
        notification.setTimestamp(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        String responseMessage = objectMapper.writeValueAsString(notification);

        // Broadcast to all connected sessions
        for (WebSocketSession webSocketSession : sessions) {
            webSocketSession.sendMessage(new TextMessage(responseMessage));
        }

    }

    private void sendNotification(WebSocketSession session, String message, String type) {
        try {

            Message notification = new Message();
            notification.setMessage(message);
            notification.setType(type);
            notification.setTimestamp(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(notification)));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
