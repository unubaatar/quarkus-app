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

    public List<User> getAllUsers() {
        return UserRepository.listAll();
    }

    public Optional<User> getUserByEmail(String Email) {
        return UserRepository.findByEmail(Email);
    }

    public Optional<User> getUserById(UUID id) {
        return UserRepository.findById(id);
    }

    @Transactional
    public User createUser(User user) {
        System.out.println(user);
        UserRepository.persist(user);
        return user;
    }

    @Transactional
    public Optional<User> updateUser(UUID id, User updatedUser) {
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
    public long deleteUser(UUID id) {
        return UserRepository.delete("id" , id);
    }

}
