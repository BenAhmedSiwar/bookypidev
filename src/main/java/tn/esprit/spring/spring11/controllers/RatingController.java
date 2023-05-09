package tn.esprit.spring.spring11.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.spring.spring11.entities.Rating;
import tn.esprit.spring.spring11.services.RatingService;

@RestController
@RequestMapping("/ratings")
@AllArgsConstructor
public class RatingController {
    private final RatingService ratingService;

    @PostMapping
    public ResponseEntity<Rating> createRating(@RequestParam int value, @RequestParam Long userId, @RequestParam Long publicationId) {
        Rating rating = ratingService.createRating(value, userId, publicationId);
        return ResponseEntity.status(HttpStatus.CREATED).body(rating);
    }
}
