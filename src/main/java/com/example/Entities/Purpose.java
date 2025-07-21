package com.example.Entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import com.example.Entities.User;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="purposes")

public class Purpose  extends PanacheEntityBase{

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name ="id" , updatable = false , nullable = false)
    private UUID id;

    @Column(name = "title" , nullable = false)
    private String title;

    @Column(name = "description" , nullable = false)
    private String description;

    @Column(name="state" , nullable = false)
    private String state = "pending";

    @OneToOne
    @JoinColumn(name = "createdUser" , nullable = false)
    private User createdUser;

    @Column(name="createdAt" , nullable = false , updatable = false)
    private LocalDateTime createdAt;

    @Column(name="updatedAt" , nullable = false)
    private LocalDateTime updatedAt;

    @PreUpdate
    protected void OnUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    @PrePersist
    protected void OnCreate() {
        this.updatedAt = LocalDateTime.now();
        this.createdAt = LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public User getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(User createdUser) {
        this.createdUser = createdUser;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}