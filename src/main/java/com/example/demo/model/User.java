package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "user_entity")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "DATE_OF_BIRTH")
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDate dateOfBirth;

    @Column(nullable = false)
    @Size(min = 8, max = 500, message = "From 8 to 500 characters")
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PhoneData> phone;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EmailData> email;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Account account;

    public User(Long id, String name, LocalDate dateOfBirth, String password, List<PhoneData> phone, List<EmailData> email, Account account) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.account = account;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<PhoneData> getPhone() {
        return phone;
    }

    public void setPhone(List<PhoneData> phone) {
        this.phone = phone;
    }

    public List<EmailData> getEmail() {
        return email;
    }

    public void setEmail(List<EmailData> email) {
        this.email = email;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void removeEmail(EmailData email) {
        email.remove(email);
        email.setUser(null);
    }
}
