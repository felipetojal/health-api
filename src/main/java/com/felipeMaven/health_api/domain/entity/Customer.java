package com.felipeMaven.health_api.domain.entity;

import com.felipeMaven.health_api.domain.enums.Sex;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "tb_customer")
public class Customer implements Serializable {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    @Column(updatable = false)
    private LocalDate birthDate;

    @Column
    private LocalDate creationDate;

    @Column
    private LocalDate lastUpdateDate;

    @OneToMany(
            mappedBy = "customer",
            cascade = CascadeType.ALL,  // Makes a JPA operation on the HealthProblem side when the operation is done at the Customer
            fetch = FetchType.LAZY,
            orphanRemoval = true    // Tells JPA to delete every HealthProblem removed from the Customer Collection
    )
    private Set<HealthProblem> healthProblems = new HashSet<>();    // The Collection is initialized to avoid NullPointerException

    @PrePersist     // Executed before INSERT operation on the database
    protected void onCreate() {
        creationDate = LocalDate.now();
        lastUpdateDate = LocalDate.now();
    }

    @PreUpdate      // Executed before UPDATE operation on the database
    protected void onUpdate() {
        lastUpdateDate = LocalDate.now();
    }
}
