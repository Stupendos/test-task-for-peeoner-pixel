package com.example.demo.repository;

import com.example.demo.model.PhoneData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PhoneDataRepository extends JpaRepository<PhoneData, Long> {
    Optional<PhoneData> findByPhoneNumber(String phoneNumber);
}
