package tn.esprit.spring.spring11.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.spring.spring11.entities.BookPublication;
import tn.esprit.spring.spring11.entities.Rating;
import tn.esprit.spring.spring11.entities.User;
import tn.esprit.spring.spring11.repositories.RatingRepo;

@Service
@AllArgsConstructor
@Slf4j
public class RatingService {
    private final RatingRepo repository;
    private final UserService userService;
    private final BookPublicationService publicationService;

    public Rating createRating(int score, Long userId, Long publicationId) {
        User user = userService.getUserById(userId);
        BookPublication publication = publicationService.getPublicationById(publicationId);

        Rating rating = new Rating();
        rating.setScore(score);
        rating.setUser(user);
        rating.getPublications().add(publication);

        user.getRatings().add(rating);
        publication.getRatings().add(rating);

        repository.save(rating);

        return rating;
    }

}