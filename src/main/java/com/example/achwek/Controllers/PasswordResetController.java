package com.example.achwek.Controllers;

import com.example.achwek.Models.PasswordResetToken;
import com.example.achwek.Models.User;
import com.example.achwek.Payload.request.ResetPasswordDto;
import com.example.achwek.Payload.response.MessageResponse;
import com.example.achwek.service.PasswordResetService;
import com.example.achwek.service.UserService;
import java.util.HashMap;
import java.util.Map;
import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PasswordResetController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordResetService passwordResetService;

    @PostMapping("/api/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestParam("email") String email)
          throws MessagingException {
        User user = userService.findByEmail(email);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("User not found."));
        }

        passwordResetService.sendPasswordResetEmail(user);

        return ResponseEntity.ok(new MessageResponse("Reset password link has been sent to your email."));
    }

    @PostMapping("/api/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordDto resetPasswordDto) {
        PasswordResetToken passwordResetToken = passwordResetService.getPasswordResetToken(resetPasswordDto.getToken());

        if (passwordResetToken == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                  .body(new MessageResponse("Password reset token is invalid."));
        }

        if (passwordResetToken.getExpiryDate().getTime() - System.currentTimeMillis() <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                  .body(new MessageResponse("Password reset token has expired."));
        }

        userService.resetPassword(passwordResetToken.getUser().getEmail(), resetPasswordDto.getPassword());
        passwordResetService.deletePasswordResetToken(passwordResetToken.getId());
        return ResponseEntity.ok(new MessageResponse("Password has been reset successfully."));
    }

}
