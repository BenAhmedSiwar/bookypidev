package com.example.achwek.service.impl;

import com.example.achwek.Models.Reclamation;
import com.example.achwek.Models.Role;
import com.example.achwek.Models.StatutReclamation;
import com.example.achwek.Models.User;
import com.example.achwek.Payload.request.BookDTO;
import com.example.achwek.Payload.request.CreateReclamationDto;
import com.example.achwek.Payload.request.UpdateReclamationDto;
import com.example.achwek.Payload.response.ReclamationDto;
import com.example.achwek.Repository.BookRepository;
import com.example.achwek.Repository.ReclamationRepository;
import com.example.achwek.Repository.UserRepository;
import com.example.achwek.service.ReclamationService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ReclamationServiceImpl implements ReclamationService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ReclamationRepository reclamationRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;

    @Override
    public ReclamationDto createReclamation(CreateReclamationDto reclamationDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<User> user = userRepository.findByUsername(username);
        Reclamation reclamation = new Reclamation();
        reclamation.setDescription(reclamationDto.getDescription());
        reclamation.setType(reclamationDto.getType());
        reclamation.setStatut(StatutReclamation.EN_ATTENTE);
        reclamation.setCreatedAt(LocalDateTime.now());
        reclamation.setUpdatedAt(LocalDateTime.now());
        reclamation.setUser(user.get());
        if (reclamationDto.getBookId() != null) {
            reclamation.setBook(bookRepository.findById(reclamationDto.getBookId()).get());
        }
        Reclamation savedReclamation = reclamationRepository.save(reclamation);
        return modelMapper.map(savedReclamation, ReclamationDto.class);
    }

    @Override
    public ReclamationDto getReclamationById(Long id) {
        Reclamation reclamation = reclamationRepository.findById(id)
              .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Réclamation introuvable"));
        ReclamationDto reclamationDto = modelMapper.map(reclamation, ReclamationDto.class);
        if(reclamation.getBook()!=null) {
            reclamationDto.setBook(modelMapper.map(reclamation.getBook(), BookDTO.class));
        }
        return reclamationDto;
    }

    @Override
    public ReclamationDto updateReclamation(Long id, UpdateReclamationDto reclamationDto) {
        Reclamation reclamation = reclamationRepository.findById(id)
              .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Réclamation introuvable"));
        reclamation.setDescription(reclamationDto.getDescription());
        reclamation.setType(reclamationDto.getType());
        if(reclamationDto.getStatut()!=null) {
            reclamation.setStatut(reclamationDto.getStatut());
        }
        reclamation.setUpdatedAt(LocalDateTime.now());

        if (reclamationDto.getBookId() != null) {
            reclamation.setBook(bookRepository.findById(reclamationDto.getBookId()).get());
        }
        Reclamation updatedReclamation = reclamationRepository.save(reclamation);
        return modelMapper.map(updatedReclamation, ReclamationDto.class);
    }

    @Override
    public void deleteReclamation(Long id) {
        if (!reclamationRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Réclamation introuvable");
        }
        reclamationRepository.deleteById(id);
    }

    @Override
    public List<ReclamationDto> getAllReclamations() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<User> user = userRepository.findByUsername(username);
        List<Reclamation> reclamations;
        if (isAdminORModerateur(user)) {
            reclamations = reclamationRepository.findAll();
        } else {
            reclamations = reclamationRepository.findByUserId(user.get().getId());
        }
        return reclamations.stream().map(reclamation -> {
                  ReclamationDto dto = modelMapper.map(reclamation, ReclamationDto.class);
            if(reclamation.getBook()!=null) {
                dto.setBook(modelMapper.map(reclamation.getBook(), BookDTO.class));
            }
                  return dto;
              })
              .collect(Collectors.toList());
    }

    private static boolean isAdminORModerateur(Optional<User> user) {
        return user.get().getRoles().stream()
              .anyMatch(role -> role == Role.ROLE_ADMIN || role == Role.ROLE_MODERATOR);
    }

}
