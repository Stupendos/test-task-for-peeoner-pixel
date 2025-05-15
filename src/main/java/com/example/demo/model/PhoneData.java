package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

import java.util.Objects;

@Entity
public class PhoneData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Pattern(
            regexp = "^\\+7\\d{10}$",
            message = "The phone number must start with +7 and contain 10 digits"
    )
    private String phone;

    public PhoneData(long id, User user, String phone) {
        this.id = id;
        this.user = user;
        this.phone = phone;
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
            message = "The phone number must start with +7 and contain 10 digits"
    ) String getPhone() {
        return phone;
    }

    public void setPhone(@Pattern(
            regexp = "^\\+7\\d{10}$",
            message = "The phone number must start with +7 and contain 10 digits"
    ) String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneData phoneData = (PhoneData) o;
        return id == phoneData.id && Objects.equals(user, phoneData.user) && Objects.equals(phone, phoneData.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, phone);
    }
}
