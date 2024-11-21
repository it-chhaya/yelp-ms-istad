package kh.edu.cstad.notification.webmvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kh.edu.cstad.notification.domain.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/webmvc/notifications")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class NotificationWebMvcController {

    private final KafkaTemplate<String, Notification> kafkaTemplate;

    @PostMapping
    public Notification createNotification(@RequestBody Notification notification) throws JsonProcessingException {

        notification.setId(UUID.randomUUID().toString());
        notification.setTimestamp(LocalDateTime.now());
        notification.setRead(false);

        kafkaTemplate.send("notifications", notification.getId(), notification);

        return notification;
    }

}
