package com.eventflow.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.eventflow.model.entity.enums.Role;
import com.eventflow.model.entity.User;
import com.eventflow.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository ;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found"));

    }

    public User loadUserById(Long id) throws UsernameNotFoundException {
        return userRepository.findById(id)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found"));
    }

    public boolean saveUser(String name, String email, String encodedPassword, String role) {
        if (userRepository.existsByEmail(email)) {
            return false;
        }
        User user = User.builder()
                .name(name)
                .email(email)
                .password(encodedPassword)
                .role(role.equalsIgnoreCase("ADMIN") ? Role.ADMIN : Role.USER)
                .build();
        try{
        userRepository.save(user);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public boolean deleteUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElse(null);

        if (user == null) {
            return false;
        }

        userRepository.delete(user);
        return true;
    }
}
