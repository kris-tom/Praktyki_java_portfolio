package com.krdl.task12.repository;

import com.krdl.task12.model.Book;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Getter
public class BookRepository {

  private final List<Book> books = new ArrayList<>();
  private Long nextId = 1L;

  public Optional<Book> findById(Long id) {
    return books.stream().filter(book -> book.getId().equals(id)).findFirst();
  }

  public Book addBook(Book book) {
    book.setId(nextId++);
    books.add(book);
    return book;
  }

  public Optional<Book> updateBook(Long id, Book updatedBook) {
    Optional<Book> existing = findById(id);
    existing.ifPresent(book -> {
      book.setTitle(updatedBook.getTitle());
      book.setAuthor(updatedBook.getAuthor());
    });
    return existing;
  }

  public void deleteById(Long id) {
    books.removeIf(book -> book.getId().equals(id));
  }

  public List<Book> findByAuthor(String author) {
    return books.stream().filter(book -> book.getAuthor().equalsIgnoreCase(author)).toList();
  }

  public long countBooks() {
    return books.size();
  }

}