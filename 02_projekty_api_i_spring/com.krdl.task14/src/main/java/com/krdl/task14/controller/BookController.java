package com.krdl.task14.controller;

import com.krdl.task14.module.Book;
import com.krdl.task14.repository.BookRepository;
import com.krdl.task14.config.AppConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

  private final BookRepository repository;

  /**
   * Get all books
   */
  @GetMapping
  public List<Book> getAllBooks() {
    return repository.findAll();
  }

  @GetMapping("/{id:\\d+}")
  public ResponseEntity<Book> getBookById(@PathVariable Long id) {
    return repository.findById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  /**
   * Create new book
   */
  @PostMapping
  public Book createBook(@RequestBody Book book) {
    return repository.save(book);
  }

  /**
   * Update book by ID
   */
  @PutMapping("/{id}")
  public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
    return repository.findById(id)
        .map(existingBook -> {
          book.setId(id);
          return ResponseEntity.ok(repository.save(book));
        })
        .orElse(ResponseEntity.notFound().build());
  }

  /**
   * Delete book by ID
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
    if (repository.existsById(id)) {
      repository.deleteById(id);
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.notFound().build();
  }

  /**
   * Search books by author (exact match)
   */
  @GetMapping("/search")
  public List<Book> getByAuthor(@RequestParam String author) {
    return repository.findByAuthor(author);
  }

  /**
   * Search books by author with pagination
   */
   @GetMapping("/search-paged")
   public Page<Book> getByAuthorPaged(
       @RequestParam String author,
       @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE) int page,
       @RequestParam(defaultValue = AppConstants.DEFAULT_SIZE) int size) {
     return repository.findByAuthor(author, PageRequest.of(page, size));
   }

  /**
   * Get total count of books
   */
  @GetMapping("/count")
  public long countBooks() {
    return repository.count();
  }

  /**
   * Count books by author
   */
  @GetMapping("/count/author")
  public long countByAuthor(@RequestParam String author) {
    return repository.countByAuthor(author);
  }

  /**
   * Create multiple books at once
   */
  @PostMapping("/batch")
  public List<Book> addBooks(@RequestBody List<Book> books) {
    return repository.saveAll(books);
  }

  /**
   * Find books by exact title
   */
  @GetMapping("/title")
  public List<Book> getByTitle(@RequestParam String title) {
    return repository.findByTitle(title);
  }

  /**
   * Find books containing title text
   */
  @GetMapping("/title/contains")
  public List<Book> getByTitleContaining(@RequestParam String titlePart) {
    return repository.findByTitleContaining(titlePart);
  }

  /**
   * Find books with author name containing text
   */
  @GetMapping("/author/contains")
  public List<Book> getByAuthorContaining(@RequestParam String authorPart) {
    return repository.findByAuthorContaining(authorPart);
  }

  /**
   * Advanced search - search by title or author
   */
  @GetMapping("/advanced-search")
  public List<Book> advancedSearch(@RequestParam String searchTerm) {
    return repository.searchBooks(searchTerm);
  }

  /**
   * Advanced search with pagination
   */
   @GetMapping("/advanced-search-paged")
   public Page<Book> advancedSearchPaged(
       @RequestParam String searchTerm,
       @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE) int page,
       @RequestParam(defaultValue = AppConstants.DEFAULT_SIZE) int size) {
     return repository.searchBooks(searchTerm, PageRequest.of(page, size));
   }

  /**
   * Check if book with exact title exists
   */
  @GetMapping("/exists/title")
  public ResponseEntity<Boolean> existsByTitle(@RequestParam String title) {
    return ResponseEntity.ok(repository.existsByTitle(title));
  }

  /**
   * Get all books sorted by title
   */
  @GetMapping("/sorted/title")
  public List<Book> getAllSortedByTitle() {
    return repository.findAllSortedByTitle();
  }

  /**
   * Get all books sorted by author
   */
  @GetMapping("/sorted/author")
  public List<Book> getAllSortedByAuthor() {
    return repository.findAllSortedByAuthor();
  }

  /**
   * Get all unique authors
   */
  @GetMapping("/authors")
  public List<String> getDistinctAuthors() {
    return repository.findDistinctAuthors();
  }

  /**
   * Get first book by author
   */
  @GetMapping("/first/author")
  public ResponseEntity<Book> getFirstByAuthor(@RequestParam String author) {
    return repository.findFirstByAuthor(author)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  /**
   * Delete all books by author
   */
   @DeleteMapping("/author")
   public ResponseEntity<String> deleteAllByAuthor(@RequestParam String author) {
     int deletedCount = repository.deleteAllByAuthor(author);
     return ResponseEntity.ok(String.format(AppConstants.DELETE_BOOKS_MESSAGE, deletedCount, author));
   }
}
