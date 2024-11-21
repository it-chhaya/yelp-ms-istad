package kh.edu.cstad.notification.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Notification {

    private String id;
    private String message;
    private String type;
    private String userId;
    private LocalDateTime timestamp;
    private boolean read;

    public Notification(String message, String type) {
    }
}
