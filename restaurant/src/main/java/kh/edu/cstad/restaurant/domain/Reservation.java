package kh.edu.cstad.restaurant.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime reservationDateTime;

    private Integer partySize;

    private String specialRequest;

    private String reservationStatus;   // (e.g., "Pending", "Confirmed", "Cancelled")

}
