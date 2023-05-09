package com.example.achwek.Payload.request;

import com.example.achwek.Models.StatutReclamation;
import com.example.achwek.Models.TypeReclamation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateReclamationDto {
    private String description;
    private TypeReclamation type;
    private StatutReclamation statut;
    private Long bookId;

}
