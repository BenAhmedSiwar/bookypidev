package com.example.achwek.Controllers;

import com.example.achwek.Models.User;
import com.example.achwek.Payload.request.CreateUserDto;
import com.example.achwek.Payload.request.UpdatePasswordDto;
import com.example.achwek.Payload.request.UpdateUserDto;
import com.example.achwek.Payload.response.UserDto;
import com.example.achwek.service.UserService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<UserDto> getAllUsers() {
        List<User> users = userService.findAll();
        return users.stream()
              .map(user -> modelMapper.map(user, UserDto.class))
              .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        User user = userService.findById(id)
              .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return modelMapper.map(user, UserDto.class);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public UserDto createUser(@RequestBody CreateUserDto createUserDto) {
        User savedUser = userService.save(createUserDto);
        return modelMapper.map(savedUser, UserDto.class);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable Long id, @RequestBody UpdateUserDto updateUserDto) {
        User user = userService.findById(id)
              .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        user.setEmail(updateUserDto.getEmail());
        user.setRoles(updateUserDto.getRoles());
        user.setNom(updateUserDto.getNom());
        user.setPrenom(updateUserDto.getPrenom());
        User savedUser = userService.updateUser(user);
        return modelMapper.map(savedUser, UserDto.class);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update-password")
    public ResponseEntity updatePassword(@RequestBody UpdatePasswordDto utilisateurDto) {
        Optional<User> utilisateurToUpdate = userService.findById(utilisateurDto.getId());
        if (utilisateurToUpdate.isPresent()) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            utilisateurToUpdate.get().setPassword(encoder.encode(utilisateurDto.getNewPassword()));
            User savedUser = userService.save(utilisateurToUpdate.get());
            UserDto savedUserDto = modelMapper.map(savedUser, UserDto.class);
            return new ResponseEntity<>(savedUserDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
