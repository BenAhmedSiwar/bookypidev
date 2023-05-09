package com.example.achwek.Controllers;

import com.example.achwek.Models.StatutReclamation;
import com.example.achwek.Payload.request.CreateReclamationDto;
import com.example.achwek.Payload.request.UpdateReclamationDto;
import com.example.achwek.Payload.response.ReclamationDto;
import com.example.achwek.service.impl.ReclamationServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reclamations")
public class ReclamationController {

    @Autowired
    private ReclamationServiceImpl reclamationService;

    @PostMapping
    public ResponseEntity<ReclamationDto> createReclamation(@RequestBody CreateReclamationDto reclamationDto) {
        ReclamationDto createdReclamation = reclamationService.createReclamation(reclamationDto);
        return ResponseEntity.ok(createdReclamation);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReclamationDto> getReclamationById(@PathVariable Long id) {
        ReclamationDto reclamation = reclamationService.getReclamationById(id);
        return ResponseEntity.ok(reclamation);
    }

    @GetMapping
    public ResponseEntity<List<ReclamationDto>> getAllReclamations() {
        List<ReclamationDto> reclamations = reclamationService.getAllReclamations();
        return ResponseEntity.ok(reclamations);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReclamationDto> updateReclamation(@PathVariable Long id, @RequestBody UpdateReclamationDto reclamationDto) {
        ReclamationDto updatedReclamation = reclamationService.updateReclamation(id, reclamationDto);
        return ResponseEntity.ok(updatedReclamation);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReclamation(@PathVariable Long id) {
        reclamationService.deleteReclamation(id);
        return ResponseEntity.noContent().build();
    }
}

