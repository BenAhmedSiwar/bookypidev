package com.example.achwek.service.impl;

import com.example.achwek.Models.Book;
import com.example.achwek.Payload.request.BookDTO;
import com.example.achwek.Repository.BookRepository;
import com.example.achwek.service.BookService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookDTO createBook(BookDTO bookDTO) {
        Book book = convertToEntity(bookDTO);
        Book savedBook = bookRepository.save(book);
        return convertToDTO(savedBook);
    }

    @Override
    public BookDTO getBookById(Long id) {
        Book book = bookRepository.findById(id)
              .orElseThrow(() -> new NotFoundException("Book not found with id: " + id));
        return convertToDTO(book);
    }

    @Override
    public List<BookDTO> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return convertToDTOList(books);
    }

    @Override
    public BookDTO updateBook(Long id, BookDTO bookDTO) {
        Book book = bookRepository.findById(id)
              .orElseThrow(() -> new NotFoundException("Book not found with id: " + id));

        updateBookFields(book, bookDTO);
        Book updatedBook = bookRepository.save(book);
        return convertToDTO(updatedBook);
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    // Helper method to convert BookDTO to Book entity
    private Book convertToEntity(BookDTO bookDTO) {
        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setPublisher(bookDTO.getPublisher());
        book.setIsbn(bookDTO.getIsbn());
        // Set other fields if needed
        return book;
    }

    // Helper method to convert Book entity to BookDTO
    private BookDTO convertToDTO(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setAuthor(book.getAuthor());
        bookDTO.setPublisher(book.getPublisher());
        bookDTO.setIsbn(book.getIsbn());
        return bookDTO;
    }

    // Helper method to convert a list of Book entities to a list of BookDTOs
    private List<BookDTO> convertToDTOList(List<Book> books) {
        return books.stream()
              .map(this::convertToDTO)
              .collect(Collectors.toList());
    }

    // Helper method to update Book entity fields based on BookDTO
    private void updateBookFields(Book book, BookDTO bookDTO) {
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());

        book.setPublisher(bookDTO.getPublisher());
        book.setIsbn(bookDTO.getIsbn());
        // Update other fields if needed
    }
}
