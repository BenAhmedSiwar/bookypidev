package com.example.achwek.Controllers;


import com.example.achwek.Models.Role;
import com.example.achwek.Models.User;
import com.example.achwek.Payload.response.ConnectedUserDto;
import com.example.achwek.service.UserService;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConnectedUser {
	@Autowired
	private UserService userService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping(value = "/api/connectedUser")
	public ResponseEntity<Object> getConnectedUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Optional<User> connectedUserOptional = userService.findByUsername(authentication.getName());
		if (connectedUserOptional.isPresent()) {
			ConnectedUserDto connectedUser = modelMapper.map(connectedUserOptional.get(),
				ConnectedUserDto.class);
			return new ResponseEntity<>(connectedUser, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
}
