package com.example.Repositories;

import com.example.Entities.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {

    public  Optional<User>  findByEmail(String  email) {
        return  find("email" , email).firstResultOptional();
    }

    public Optional<User> findById(UUID id) {
        return find("id" , id ).firstResultOptional();
    }
}
