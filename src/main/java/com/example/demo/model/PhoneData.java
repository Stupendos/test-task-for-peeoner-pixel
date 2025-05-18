package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

import java.util.Objects;

@Entity
public class PhoneData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Pattern(
            regexp = "^\\+7\\d{10}$",
            message = "The phoneNumber number must start with +7 and contain 10 digits"
    )
    private String phoneNumber;

    public PhoneData(long id, User user, String phoneNumber) {
        this.id = id;
        this.user = user;
        this.phoneNumber = phoneNumber;
    }

    public PhoneData() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public @Pattern(
            regexp = "^\\+7\\d{10}$",
            message = "The phoneNumber number must start with +7 and contain 10 digits"
    ) String getPhone() {
        return phoneNumber;
    }

    public void setPhone(@Pattern(
            regexp = "^\\+7\\d{10}$",
            message = "The phoneNumber number must start with +7 and contain 10 digits"
    ) String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneData phoneNumberData = (PhoneData) o;
        return id == phoneNumberData.id && Objects.equals(user, phoneNumberData.user) && Objects.equals(phoneNumber, phoneNumberData.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, phoneNumber);
    }
}
