package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
//    @Query("SELECT u FROM User u JOIN u.email e JOIN u.phone p WHERE e.email = :email OR p.phoneNumber = :phoneNumber")
//    User findByEmailOrPhoneNumber(@Param("email") String email, @Param("phone") String phoneNumber);
//
//    Optional<User> findByAccountEmail(String email);
//
//    Optional<User> findByAccountPhone(String phone);
}
