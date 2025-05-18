package com.example.demo.service;

import com.example.demo.model.EmailData;
import com.example.demo.model.PhoneData;
import com.example.demo.model.User;
import com.example.demo.repository.EmailDataRepository;
import com.example.demo.repository.PhoneDataRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final EmailDataRepository emailDataRepository;
    private final PhoneDataRepository phoneDataRepository;

    public UserService(UserRepository userRepository, EmailDataRepository emailDataRepository, PhoneDataRepository phoneDataRepository) {
        this.userRepository = userRepository;
        this.emailDataRepository = emailDataRepository;
        this.phoneDataRepository = phoneDataRepository;
    }

    public void updateEmail(Long userId, String newEmail) {
        Optional<EmailData> existingEmail = emailDataRepository.findByEmail(newEmail);
        if (existingEmail.isPresent()) {
            throw new IllegalArgumentException("Email is already taken");
        }
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        EmailData emailData = new EmailData();
        emailData.setEmail(newEmail);
        emailData.setUser(user);
        emailDataRepository.save(emailData);
        userRepository.save(user);
    }

    public void deleteEmail(Long userId, Long emailId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));

        EmailData emailToRemove = user.getEmail().stream()
                .filter(email -> email.getId().equals(emailId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Email not found"));
        user.removeEmail(emailToRemove);
        userRepository.save(user);
    }

    public void updatePhone(Long userId, String newPhone) {
        Optional<PhoneData> existingPhone = phoneDataRepository.findByPhoneNumber(newPhone);
        if (existingPhone.isPresent()) {
            throw new IllegalArgumentException("Phone number is already taken");
        }
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        PhoneData phoneData = new PhoneData();
        phoneData.setPhone(newPhone);
        phoneData.setUser(user);
        phoneDataRepository.save(phoneData);
        userRepository.save(user);
    }

    public void deletePhone(Long userId, String phoneNumber) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        PhoneData phoneToRemove = user.getPhone().stream()
                .filter(phone -> phone.getPhone().equals(phoneNumber))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Phone number not found"));
        user.getPhone().remove(phoneToRemove);
        userRepository.save(user);
    }

    public Page<User> getUsers(String name, String phone, String email, LocalDate birthday, Pageable pageable) {
        Specification<User> spec = Specification
                .where(UserSpecification.filterByBirthday(birthday))
                .and(UserSpecification.filterByEmail(email))
                .and(UserSpecification.filterByPhone(phone))
                .and(UserSpecification.filterByName(name));

        return userRepository.findAll(spec, pageable);
    }
}
