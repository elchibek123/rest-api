package java15.pharmacyproject.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "medicines")
@Getter @Setter
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String name;

    @Positive
    @NotNull
    @Column(nullable = false)
    private BigDecimal price;

    @ManyToMany(mappedBy = "medicines", fetch = FetchType.LAZY)
    private Set<Pharmacy> pharmacies = new HashSet<>();
}
