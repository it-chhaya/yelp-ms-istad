package kh.edu.cstad.notification.webflux;

import kh.edu.cstad.notification.domain.Notification;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.function.Consumer;

@Service
public class NotificationService {

    private final Sinks.Many<Notification> notificationSink = Sinks.many().multicast().onBackpressureBuffer();

    @Bean
    public Consumer<Notification> processNotification() {
        return notificationSink::tryEmitNext;
    }

    public Flux<Notification> getNotificationStream(String userId) {
        return notificationSink.asFlux()
                .filter(notification -> notification.getUserId().equals(userId));
    }

}
