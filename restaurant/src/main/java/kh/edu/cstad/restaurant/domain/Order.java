package kh.edu.cstad.restaurant.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime orderDateTime;

    private BigDecimal totalPrice;

    private String deliveryAddress;

    private String orderStatus;

    @OneToMany
    private List<OrderItem> orderItems;

    //@ManyToOne
    //private Customer customer;

}
