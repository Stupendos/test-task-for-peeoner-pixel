package com.example.demo.service;

import com.example.demo.model.EmailData;
import com.example.demo.model.PhoneData;
import com.example.demo.model.User;
import com.example.demo.repository.EmailDataRepository;
import com.example.demo.repository.PhoneDataRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {
    private final EmailDataRepository emailDataRepository;
    private final PhoneDataRepository phoneDataRepository;

    public MyUserDetailService(EmailDataRepository emailDataRepository, PhoneDataRepository phoneDataRepository) {
        this.emailDataRepository = emailDataRepository;
        this.phoneDataRepository = phoneDataRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        if (isEmail(login)) {
            return emailDataRepository.findByEmail(login).map(EmailData::getUser)
                    .map(this::buildUserDetails).orElseThrow(() -> new UsernameNotFoundException(login));
        } else if (isPhone(login)) {
            return phoneDataRepository.findByPhoneNumber(login)
                    .map(PhoneData::getUser)
                    .map(this::buildUserDetails).orElseThrow(() -> new UsernameNotFoundException(login));
        } else {
            throw new UsernameNotFoundException(login);
        }
    }

    private UserDetails buildUserDetails(User user) {
        return org.springframework.security.core.userdetails.User.withUsername(user.getName())
                .password(user.getPassword())
                .authorities("USER")
                .build();
    }

    private boolean isEmail(String login) {
        return login.contains("@");
    }

    private boolean isPhone(String login) {
        return login.matches("\\d{10,13}");
    }
}
