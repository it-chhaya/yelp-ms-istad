package kh.edu.cstad.notification.webmvc;

import kh.edu.cstad.notification.domain.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class NotificationMessageController {

    @MessageMapping("/send-notification")
    @SendTo("/topic/notifications")
    public Notification notify(@Payload Notification notification) {
        System.out.println("Hello");
        System.out.println(notification);
        return notification;
    }

    // Handle subscription
    @SubscribeMapping("/topic/notifications")
    public String handleSubscription() {
        System.out.println("New client subscribed");
        return "Successfully subscribed!";
    }

}
