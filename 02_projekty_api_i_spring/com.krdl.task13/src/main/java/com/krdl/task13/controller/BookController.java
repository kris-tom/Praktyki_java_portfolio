package com.krdl.task13.controller;

import com.krdl.task13.module.Book;
import com.krdl.task13.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

  private final BookRepository repository;

  @GetMapping
  public List<Book> getAllBooks() {
    return repository.getBooks();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Book> getBookById(@PathVariable Long id) {
    return repository.findById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public Book createBook(@RequestBody Book book) {
    return repository.addBook(book);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
    return repository.updateBook(id, book)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
    repository.deleteById(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/search")
  public List<Book> getByAuthor(@RequestParam String author) {
    return repository.findByAuthor(author);
  }

  @GetMapping("/count")
  public long countBooks() {
    return repository.countBooks();
  }

  @PostMapping("/batch")
  public List<Book> addBooks(@RequestBody List<Book> books) {
    return books.stream().map(repository::addBook).toList();
  }
}