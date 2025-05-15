package com.example.demo.service;

import com.example.demo.model.User;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class UserSpecification {
    public static Specification<User> filterByBirthday(LocalDate birthday) {
        return ((root, query, criteriaBuilder) -> {
            if (birthday != null) {
                return criteriaBuilder.greaterThan(root.get("birthday"), birthday);
            }
            return criteriaBuilder.conjunction();
        });
    }

    public static Specification<User> filterByEmail(String email) {
        return ((root, query, criteriaBuilder) -> {
            if (email != null && !email.isEmpty()) {
                return criteriaBuilder.equal(root.get("email"), email);
            }
            return criteriaBuilder.conjunction();
        });
    }

    public static Specification<User> filterByName(String name) {
        return ((root, query, criteriaBuilder) -> {
            if (name != null && !name.isEmpty()) {
                return criteriaBuilder.like(root.get("name"), "%");
            }
            return criteriaBuilder.conjunction();
        });
    }

    public static Specification<User> filterByPhone(String phone) {
        return (root, query, criteriaBuilder) -> {
            if (phone != null && !phone.isEmpty()) {
                return criteriaBuilder.equal(root.get("phone"), phone);
            }
            return criteriaBuilder.conjunction();
        };
    }
}
