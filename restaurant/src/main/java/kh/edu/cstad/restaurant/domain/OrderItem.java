package kh.edu.cstad.restaurant.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;

    private BigDecimal price;

    private String itemOptions; //  Any customizations or options chosen for the item (e.g., size, toppings)

    private String itemStatus; // Status of the item within the order (e.g., "Preparing", "Ready", "Delivered")

    @ManyToOne
    private MenuItem menuItem;

    @ManyToOne
    private Order order;

}
