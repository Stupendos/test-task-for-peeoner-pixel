package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;

@Entity
public class EmailData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(
            regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
            message = "Invalid email format")
    private String email;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public EmailData(Long id, String email, User user) {
        this.id = id;
        this.email = email;
        this.user = user;
    }

    public EmailData() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void remove(EmailData email) {
        this.email = email.getEmail();
        this.user = null;
    }
}
