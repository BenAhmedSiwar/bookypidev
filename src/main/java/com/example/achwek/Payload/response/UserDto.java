package com.example.achwek.Payload.response;

import com.example.achwek.Models.Role;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String prenom;
    private String nom;
    private String username;
    private String email;
    private List<Role> roles;
}
