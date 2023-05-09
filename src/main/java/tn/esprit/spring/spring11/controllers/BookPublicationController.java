package tn.esprit.spring.spring11.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import tn.esprit.spring.spring11.entities.BookPublication;
import tn.esprit.spring.spring11.entities.Category;
import tn.esprit.spring.spring11.entities.Comment;
import tn.esprit.spring.spring11.entities.User;
import tn.esprit.spring.spring11.services.BookPublicationService;
import tn.esprit.spring.spring11.services.UserService;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/publications")
public class BookPublicationController {

    private final BookPublicationService service;
    private final UserService userService;

    public BookPublicationController(BookPublicationService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<BookPublication>> getAllPublications() {
        List<BookPublication> publications = service.getAllPublications();
        return ResponseEntity.ok(publications);
    }

    @GetMapping("/search")
    public ResponseEntity<List<BookPublication>> searchPublications(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String publisher,
            @RequestParam(required = false) Category category) {
        List<BookPublication> publications = service.searchPublications(title, author, publisher, category);
        return ResponseEntity.ok(publications);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookPublication> getPublicationById(@PathVariable Long id) {
        BookPublication publication = service.getPublicationById(id);
        return ResponseEntity.ok(publication);
    }

    @PostMapping
    public ResponseEntity<BookPublication> createPublication(@RequestBody BookPublication publication, @RequestParam Long userId) {
        User user = userService.getUserById(userId);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        publication.setUser(user);
        BookPublication newPublication = service.createPublication(publication, userId);
        return ResponseEntity.ok(newPublication);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookPublication> updatePublication(@PathVariable Long id, @RequestBody BookPublication publication) {
        BookPublication updatedPublication = service.updatePublication(id, publication);
        return ResponseEntity.ok(updatedPublication);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePublication(@PathVariable Long id) {
        service.deletePublication(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/publications/sorted")
    public List<BookPublication> getAllPublicationsSortedByRating() {
        return service.getPublicationsSortedByRating();
    }

    @GetMapping("/category-statistics")
    public ResponseEntity<Map<Category, Integer>> getPublicationCategoryStatistics() {
        Map<Category, Integer> statistics = service.getPublicationStatsByCategory();
        return ResponseEntity.ok(statistics);
    }

    @PostMapping("/{id}/comments/{commentId}/report")
    public ResponseEntity<String> reportComment(@PathVariable Long id, @PathVariable Long commentId) {
        service.moderateComment(commentId);
        return ResponseEntity.ok("Comment reported successfully");
    }
    @GetMapping("/{id}/comments")
    public List<Comment> getCommentsForPublication(@PathVariable Long id) {
        List<String> badWords = Arrays.asList("badword1", "badword2", "badword3");
        return service.getCommentsForPublication(id, badWords);
    }
}