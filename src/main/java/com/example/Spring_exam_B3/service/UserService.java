package com.example.Spring_exam_B3.service;

import com.example.Spring_exam_B3.entity.User;
import com.example.Spring_exam_B3.entity.Role;
import com.example.Spring_exam_B3.repo.RoleRepository;
import com.example.Spring_exam_B3.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleRepository = roleRepository;
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Transactional
    public User registerNewUserAccount(User user) {
        // Hacher le mot de passe avant de l'enregistrer
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        // Récupérer le rôle par défaut "ROLE_API_CALL" depuis la base
        Role defaultRole = roleRepository.findByName("ROLE_API_CALL")
                .orElseThrow(() -> new RuntimeException("Role not found: ROLE_API_CALL"));

        // Assigner ce rôle à l'utilisateur
        user.setRoles(Collections.singletonList(defaultRole));

        // Sauvegarde de l'utilisateur
        return userRepository.save(user);
    }

    public User getUserByToken(String token) {
        User user = userRepository.findByClaimToken(token);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        } else if (user.getTokenExpiration().isBefore(LocalDateTime.now())) {
            throw new UsernameNotFoundException("Token expired");
        } else if (user.isEnabled()) {
            throw new UsernameNotFoundException("User already enabled");
        }

        return user;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public List<String> getUserRoles(String username) {
        User user = getUser(username);
        return getUserRoles(user);
    }

    public List<String> getUserRoles(User user) {
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user.getRoles().stream().map(Role::getName).collect(Collectors.toList());
    }

    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }


}