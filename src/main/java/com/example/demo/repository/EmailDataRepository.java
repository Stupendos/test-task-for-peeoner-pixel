package com.example.demo.repository;

import com.example.demo.model.EmailData;
import jakarta.validation.constraints.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailDataRepository extends JpaRepository<EmailData, Long> {
    Optional<EmailData> findByEmail(String email);
}
