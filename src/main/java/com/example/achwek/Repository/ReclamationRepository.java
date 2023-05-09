package com.example.achwek.Repository;

import com.example.achwek.Models.Reclamation;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReclamationRepository  extends JpaRepository<Reclamation, Long> {

    List<Reclamation> findByUserId(Long userId);
}
