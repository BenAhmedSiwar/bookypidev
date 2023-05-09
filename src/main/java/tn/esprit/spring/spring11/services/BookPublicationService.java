package tn.esprit.spring.spring11.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import tn.esprit.spring.spring11.entities.*;
import tn.esprit.spring.spring11.repositories.BookpubRepo;
import tn.esprit.spring.spring11.repositories.CommentRepo;
import tn.esprit.spring.spring11.repositories.RatingRepo;
import tn.esprit.spring.spring11.repositories.UserRepo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
@Slf4j
public class BookPublicationService {
    private final BookpubRepo repository;
    private final UserRepo userRepository;
    private final UserService userService;
    private final RatingRepo ratingRepository;

    @Autowired
    private CommentRepo commentRepo;

    public Rating ratePublication(Long publicationId, Rating rating, Long userId) {
        User user = userService.getUserById(userId);
        BookPublication publication = getPublicationById(publicationId);

        rating.setUser(user);
        rating.getPublications().add(publication);

        return ratingRepository.save(rating);
    }
    public List<BookPublication> getAllPublications() {
        return repository.findAll();
    }

    public BookPublication getPublicationById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Publication not found"));
    }

    public BookPublication createPublication(BookPublication publication, Long userId) {
        User user = userService.getUserById(userId);

        publication.setUser(user);
        return repository.save(publication);
    }

    public List<BookPublication> searchPublications(String title, String author, String publisher, Category category) {
        Specification<BookPublication> spec = Specification.where(null);
        if (title != null) {
            spec = spec.and((root, query, cb) -> cb.like(root.get("title"), "%" + title + "%"));
        }
        if (author != null) {
            spec = spec.and((root, query, cb) -> cb.like(root.get("author"), "%" + author + "%"));
        }
        if (publisher != null) {
            spec = spec.and((root, query, cb) -> cb.like(root.get("publisher"), "%" + publisher + "%"));
        }
        if (category != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("category"), category));
        }
        return repository.findAll(spec);
    }

    public BookPublication updatePublication(Long id, BookPublication publication) {
        BookPublication existingPublication = getPublicationById(id);
        existingPublication.setTitle(publication.getTitle());
        existingPublication.setAuthor(publication.getAuthor());
        existingPublication.setPublisher(publication.getPublisher());
        existingPublication.setDescription(publication.getDescription());
        existingPublication.setRating(publication.getRating());
        existingPublication.setUser(publication.getUser());
        return repository.save(existingPublication);
    }

    public void deletePublication(Long id) {
        BookPublication existingPublication = getPublicationById(id);
        repository.delete(existingPublication);
    }
    public List<BookPublication> getPublicationsSortedByRating() {
        return repository.findAll(Sort.by(Sort.Direction.DESC, "rating"));
    }
    public Map<Category, Integer> getPublicationStatsByCategory() {
        List<BookPublication> publications = repository.findAll();
        Map<Category, Integer> stats = new HashMap<>();
        for (BookPublication publication : publications) {
            Category category = publication.getCategory();
            if (!stats.containsKey(category)) {
                stats.put(category, 1);
            } else {
                stats.put(category, stats.get(category) + 1);
            }
        }
        return stats;
    }
    public List<Comment> getUnmoderatedComments(Long publicationId) {
        return commentRepo.findByPublicationIdAndModeratedIsFalse(publicationId);
    }

    public void moderateComment(Long commentId) {
        Comment comment = commentRepo.findById(commentId)
                .orElseThrow(() -> new NotFoundException("Comment not found"));

        comment.setModerated(true);
        commentRepo.save(comment);
    }
    public List<Comment> getCommentsForPublication(Long publicationId, List<String> badWords) {
        List<Comment> comments = commentRepo.findByPublicationId(publicationId);

        for (Comment comment : comments) {
            comment.filterBadWords(badWords);
        }

        return comments;
    }


}