package com.example.achwek.service.impl;

import com.example.achwek.Models.PasswordResetToken;
import com.example.achwek.Models.User;
import com.example.achwek.Repository.PasswordResetTokenRepository;
import com.example.achwek.service.PasswordResetService;
import java.util.Date;
import java.util.UUID;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class PasswordResetServiceImpl implements PasswordResetService {

    @Autowired
    private PasswordResetTokenRepository tokenRepository;
@Autowired
private JavaMailSender emailSender;

    private PasswordResetToken createPasswordResetTokenForUser(User user) {
        String token = UUID.randomUUID().toString();
        PasswordResetToken passwordResetToken = new PasswordResetToken();
        passwordResetToken.setToken(token);
        passwordResetToken.setUser(user);
        passwordResetToken.setExpiryDate(
              new Date(System.currentTimeMillis() + 3600000)); // token expires in 1 hour
        tokenRepository.save(passwordResetToken);
        return passwordResetToken;
    }

    @Override
    public PasswordResetToken getPasswordResetToken(String token) {
        return tokenRepository.findByToken(token);
    }

    @Override
    public void deletePasswordResetToken(Long id) {
        tokenRepository.deleteById(id);
    }

    @Override
    public void sendPasswordResetEmail(User user) throws MessagingException {
        PasswordResetToken passwordResetToken =createPasswordResetTokenForUser(user);
        String resetLinkUrl ="http://localhost:9000/login/reset-password";
        String recipientAddress = user.getEmail();
        String subject = "Password Reset Request";
        String confirmationUrl = resetLinkUrl + "?token=" + passwordResetToken.getToken();
        String message = "To reset your password, click the link below:\n\n" + confirmationUrl;

        MimeMessage mimeMessage = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        helper.setFrom("your-email@example.com");
        helper.setTo(recipientAddress);
        helper.setSubject(subject);
        helper.setText(message, true);

        emailSender.send(mimeMessage);
    }
}
