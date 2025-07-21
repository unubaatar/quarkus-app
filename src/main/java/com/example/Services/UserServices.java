package com.example.Services;

import com.example.Entities.User;
import com.example.Repositories.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class UserServices {

    @Inject
    UserRepository UserRepository;

    public List<User> getAll() {
        return UserRepository.listAll();
    }

    public Optional<User> getByEmail(String Email) {
        return UserRepository.findByEmail(Email);
    }

    public Optional<User> getById(UUID id) {
        return UserRepository.findById(id);
    }

    @Transactional
    public Optional<User> create(User user) {
        String phone = user.getPhone();
        String email = user.getEmail();

        if (email == null || email.isBlank() || phone == null || phone.isBlank()) {
            return Optional.empty();
        }

        Optional<User> foundByEmail = UserRepository.find("email", email).firstResultOptional();
        Optional<User> foundByPhone = UserRepository.find("phone", phone).firstResultOptional();

        if (foundByEmail.isPresent() || foundByPhone.isPresent()) {
            return Optional.empty();
        }

        UserRepository.persist(user);
        UserRepository.flush();

        System.out.println(user);

        if (user.getId() == null) {
            return Optional.empty();
        }

        return Optional.of(user);
    }

    @Transactional
    public Optional<User> update(UUID id, User updatedUser) {
        Optional<User> optionalUser = UserRepository.findById(id);

        if (optionalUser.isEmpty()) {
            return Optional.empty();
        }

        User existingUser = optionalUser.get();
        existingUser.setName(updatedUser.getName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPassword(updatedUser.getPassword());
        existingUser.setPhone(updatedUser.getPhone());
        return Optional.of(existingUser);
    }

    @Transactional
    public long delete(UUID id) {
        return UserRepository.delete("id" , id);
    }

}
