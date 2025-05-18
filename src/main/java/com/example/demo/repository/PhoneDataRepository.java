package com.example.demo.repository;

import com.example.demo.model.PhoneData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PhoneDataRepository extends JpaRepository<PhoneData, Long> {
    Optional<PhoneData> findByPhoneNumber(String phoneNumber);
}
