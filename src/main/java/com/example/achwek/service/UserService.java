package com.example.achwek.service;

import com.example.achwek.Models.User;
import com.example.achwek.Payload.request.CreateUserDto;
import java.util.List;
import java.util.Optional;

public interface UserService {

    User findByEmail(String email);

    User saveUser(User user);

    void resetPassword(String email, String newPassword);

    List<User> findAll();

    User save(CreateUserDto user);

    User save(User user);

    Optional<User> findById(Long id);

    void deleteById(Long id);

    User updateUser(User user);

    Optional<User> findByUsername(String name);
}
