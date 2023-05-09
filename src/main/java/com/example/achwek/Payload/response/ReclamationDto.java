package com.example.achwek.Payload.response;

import com.example.achwek.Models.StatutReclamation;
import com.example.achwek.Models.TypeReclamation;
import com.example.achwek.Payload.request.BookDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.awt.print.Book;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReclamationDto {
    private Long id;
    private String description;
    private TypeReclamation type;
    private StatutReclamation statut;
    @JsonFormat(timezone = "GMT+1", pattern = "dd/MM/YYYY HH:mm:ss")
    private LocalDateTime createdAt;
    @JsonFormat(timezone = "GMT+1", pattern = "dd/MM/YYYY HH:mm:ss")
    private LocalDateTime updatedAt;
    private UserDto user;
    private BookDTO book;
    private Long bookId;

    public Long getBookId() {
        if(book!=null) {
            return book.getId();
        }
        return null;
    }
}
