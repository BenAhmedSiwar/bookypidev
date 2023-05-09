package tn.esprit.spring.spring11.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import tn.esprit.spring.spring11.entities.BookPublication;
import tn.esprit.spring.spring11.entities.Comment;
import tn.esprit.spring.spring11.entities.User;
import tn.esprit.spring.spring11.repositories.CommentRepo;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentService {
    private final CommentRepo commentRepo;
    private final UserService userService;
    private final BookPublicationService bookPublicationService;

    public List<Comment> getCommentsByPublicationId(Long publicationId) {
        BookPublication publication = bookPublicationService.getPublicationById(publicationId);
        return publication.getComments();
    }

    public Comment createComment(Long publicationId, Comment comment, Long userId) {
        User user = userService.getUserById(userId);
        BookPublication publication = bookPublicationService.getPublicationById(publicationId);

        comment.setUser(user);
        comment.setPublication(publication);

        Comment savedComment = commentRepo.save(comment);

        Comment commentToReturn = new Comment();
        commentToReturn.setId(savedComment.getId());
        commentToReturn.setText(savedComment.getText());

        return commentToReturn;
    }



    public Comment updateComment(Long id, Comment comment) {
        Comment existingComment = commentRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Comment not found"));
        existingComment.setText(comment.getText());
        return commentRepo.save(existingComment);
    }


    public void deleteComment(Long id) {
        commentRepo.deleteById(id);
    }

}