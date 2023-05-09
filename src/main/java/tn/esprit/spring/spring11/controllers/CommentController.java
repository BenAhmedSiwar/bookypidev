package tn.esprit.spring.spring11.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import tn.esprit.spring.spring11.entities.Comment;
import tn.esprit.spring.spring11.interfaces.CommentView;
import tn.esprit.spring.spring11.repositories.CommentRepo;
import tn.esprit.spring.spring11.services.CommentService;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;



    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/{publicationId}")
    public ResponseEntity<List<Comment>> getCommentsByPublicationId(@PathVariable Long publicationId) {
        List<Comment> comments = commentService.getCommentsByPublicationId(publicationId);
        return ResponseEntity.ok(comments);
    }

    @PostMapping("/{publicationId}")
    public ResponseEntity<Comment> createComment(@PathVariable Long publicationId,
                                                 @RequestBody Comment comment,
                                                 @RequestParam Long userId) {
        Comment savedComment = commentService.createComment(publicationId, comment, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedComment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable Long id, @RequestBody Comment comment) {
        Comment updatedComment = commentService.updateComment(id, comment);
        return ResponseEntity.ok(updatedComment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }
    @Autowired
    private CommentRepo commentRepository;

    @GetMapping("/comments/{id}")
    @JsonView(CommentView.Minimal.class)
    public Comment getComment(@PathVariable Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment not found"));
    }
    }
