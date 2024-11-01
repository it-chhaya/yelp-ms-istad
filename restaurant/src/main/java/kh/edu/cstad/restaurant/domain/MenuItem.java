package kh.edu.cstad.restaurant.domain;

import kh.edu.cstad.restaurant.config.jpa.Auditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "menu_items")
public class MenuItem  extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String alias;

    private String name;

    private String picture;

    private String description;

    private BigDecimal price;

    private Boolean isAvailable;

    @ManyToOne
    private MenuCategory menuCategory;

    @ManyToOne
    private Restaurant restaurant;

}
