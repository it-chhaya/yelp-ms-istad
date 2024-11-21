package kh.edu.cstad.notification.webmvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import kh.edu.cstad.notification.domain.Notification;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class NotificationMvcService {

    private final SimpMessagingTemplate messagingTemplate;

    @Bean
    public Function<KStream<String, Notification>, KStream<String, Notification>> notificationProcessor() {

        return input -> input.peek((key, notification) -> {

            System.out.println("Processing notification: " + notification);

            // Add your processing logic here
            // Save into your database

            messagingTemplate.convertAndSend("/topic/notifications", notification);
        });

    }

}
