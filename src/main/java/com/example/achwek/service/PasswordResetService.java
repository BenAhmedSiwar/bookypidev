package com.example.achwek.service;

import com.example.achwek.Models.PasswordResetToken;
import com.example.achwek.Models.User;
import javax.mail.MessagingException;

public interface PasswordResetService {

    PasswordResetToken getPasswordResetToken(String token);
    void deletePasswordResetToken(Long id);

    void sendPasswordResetEmail(User user) throws MessagingException;
}