package com.example.achwek.Payload.request;

import com.example.achwek.Models.TypeReclamation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateReclamationDto {
    private String description;
    private Long bookId;
    private TypeReclamation type;
}
