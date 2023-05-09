package com.example.achwek.service;

import com.example.achwek.Payload.request.CreateReclamationDto;
import com.example.achwek.Payload.request.UpdateReclamationDto;
import com.example.achwek.Payload.response.ReclamationDto;
import java.util.List;

public interface ReclamationService {

    ReclamationDto createReclamation(CreateReclamationDto reclamationDto);

    ReclamationDto getReclamationById(Long id);

    ReclamationDto updateReclamation(Long id, UpdateReclamationDto reclamationDto);

    void deleteReclamation(Long id);

    List<ReclamationDto> getAllReclamations();
}
