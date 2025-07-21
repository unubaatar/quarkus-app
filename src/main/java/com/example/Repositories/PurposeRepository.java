package com.example.Repositories;

import com.example.Entities.Purpose;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class PurposeRepository implements PanacheRepository<Purpose>  {

    public Optional<Purpose> findById(UUID id) {
        return find("id" , id).firstResultOptional();
    }

}
