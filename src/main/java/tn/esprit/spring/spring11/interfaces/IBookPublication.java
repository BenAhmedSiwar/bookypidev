package tn.esprit.spring.spring11.interfaces;

import tn.esprit.spring.spring11.entities.BookPublication;
import tn.esprit.spring.spring11.entities.Category;
import tn.esprit.spring.spring11.entities.Rating;

import java.util.List;
import java.util.Map;

public interface IBookPublication {
    List<BookPublication> getAllPublications();
    BookPublication getPublicationById(Long id);
    BookPublication createPublication(BookPublication publication);
    BookPublication updatePublication(Long id, BookPublication publication);
    void deletePublication(Long id);
    Rating ratePublication(Long publicationId, Rating rating, Long userId);
    List<BookPublication> searchPublications(String title, String author, String publisher, Category category);
    Map<Category, Integer> getPublicationStatsByCategory();

}