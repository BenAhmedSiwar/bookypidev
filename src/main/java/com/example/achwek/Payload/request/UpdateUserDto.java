package com.example.achwek.Payload.request;

import com.example.achwek.Models.Role;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserDto {
        private String prenom;
        private String nom;
        private String email;
        private List<Role> roles;
}
