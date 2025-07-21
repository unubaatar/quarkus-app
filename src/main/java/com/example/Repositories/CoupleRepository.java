package com.example.Repositories;

import com.example.Entities.Couple;
import io.quarkus.hibernate.orm.panache.Panache;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class CoupleRepository implements PanacheRepository<Couple> {
    public Optional<Couple> findById(UUID id) {
        return  find("id" , id).firstResultOptional();
    }

    public Optional<Couple> findByName(String coupleName) {
        return find("coupleName" , coupleName).firstResultOptional();
    }
}
