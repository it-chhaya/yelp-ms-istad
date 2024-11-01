package kh.edu.cstad.product.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String skus;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private BigDecimal priceOut;

    private Integer quantity;

    private String image;

    private String description;

    private Boolean isAvailable;

    @OneToMany(mappedBy = "product")
    private List<Category> categories;

    @OneToMany(mappedBy = "product")
    private List<Inventory> inventories;

}
