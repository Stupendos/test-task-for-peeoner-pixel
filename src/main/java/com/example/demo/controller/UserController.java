package com.example.demo.controller;

import com.example.demo.model.EmailData;
import com.example.demo.model.PhoneData;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/{userId}/emails")
    public ResponseEntity<EmailData> addEmail(@PathVariable Long userId, @RequestParam String email) {
        userService.updateEmail(userId, email);
        return ResponseEntity.status(HttpStatus.OK).body(new EmailData());
    }

    @DeleteMapping("/{userId}/emails/{emailId}")
    public ResponseEntity<Void> deleteEmail(@PathVariable Long userId, @PathVariable Long emailId) {
        userService.deleteEmail(userId, emailId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/{userId}/phones")
    public ResponseEntity<PhoneData> addPhone(@PathVariable Long userId, @RequestParam String phone) {
        userService.updatePhone(userId, phone);
        return ResponseEntity.status(HttpStatus.OK).body(new PhoneData());
    }

    @DeleteMapping("/{userId}/phones/{phoneId}")
    public ResponseEntity<Void> deletePhone(@PathVariable Long userId, @PathVariable String phoneNumber) {
        userService.deletePhone(userId, phoneNumber);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/search")
    public Page<User> searchUser(@RequestParam(required = false) String name,
                                 @RequestParam(required = false) String phone,
                                 @RequestParam(required = false) String email,
                                 @RequestParam(required = false) String birthday,
                                 @RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "10") int size) {
        LocalDate dayOfBirth = (birthday != null && !birthday.isBlank())
                ? LocalDate.parse(birthday)
                : null;

        PageRequest pageable = PageRequest.of(page, size);

        return userService.getUsers(name, phone, email, dayOfBirth, pageable);
    }
}
