package kh.edu.cstad.service.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "services")
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String serviceName;

    private BigDecimal price;

    private String description;

    @ManyToOne
    private ServiceType serviceType;

    @OneToMany(mappedBy = "service")
    private List<Appointment> appointments;

}
