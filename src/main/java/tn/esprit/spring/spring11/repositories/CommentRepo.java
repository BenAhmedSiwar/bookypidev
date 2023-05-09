package tn.esprit.spring.spring11.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.spring11.entities.Comment;

import java.util.List;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Long> {
    List<Comment> findByPublicationId(Long publicationId);
    List<Comment> findByPublicationIdAndModeratedIsFalse(Long publicationId);

}
