package com.example.Entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name="couple")
@NoArgsConstructor
@AllArgsConstructor
public class Couple extends PanacheEntityBase {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false , unique = true)
    private String coupleName;

    @Column(nullable = false)
    private String state = "pending";

    @OneToMany(mappedBy = "couple" , cascade = CascadeType.ALL , orphanRemoval = true)
    private List<User> users;
}
