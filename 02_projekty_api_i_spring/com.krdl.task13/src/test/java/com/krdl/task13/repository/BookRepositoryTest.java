package com.krdl.task13.repository;

import com.krdl.task13.module.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@Import(BookRepository.class)
class BookRepositoryTest {

    @Autowired
    private BookRepository repository;

    @BeforeEach
    void setup() {
        repository.addBook(new Book(null, "Test Book 1", "Author A"));
        repository.addBook(new Book(null, "Test Book 2", "Author B"));
    }


    @Test
    void shouldFindBookById() {
        Book book = repository.addBook(new Book(null, "Find Me", "Author X"));

        Optional<Book> found = repository.findById(book.getId());

        assertTrue(found.isPresent());
        assertEquals("Find Me", found.get().getTitle());
    }

    @Test
    void shouldAddBook() {
        Book book = new Book(null, "New Book", "Author Y");

        Book saved = repository.addBook(book);

        assertNotNull(saved.getId());
        assertEquals("New Book", saved.getTitle());
    }

    @Test
    void shouldUpdateBook() {
        Book book = repository.addBook(new Book(null, "Old", "Author"));

        Optional<Book> updated = repository.updateBook(
                book.getId(),
                new Book(null, "Updated", "New Author")
        );

        assertTrue(updated.isPresent());
        assertEquals("Updated", updated.get().getTitle());
    }

    @Test
    void shouldDeleteBook() {
        Book book = repository.addBook(new Book(null, "To Delete", "Author"));

        repository.deleteById(book.getId());

        Optional<Book> found = repository.findById(book.getId());

        assertTrue(found.isEmpty());
    }

    @Test
    void shouldFindByAuthor() {
        repository.addBook(new Book(null, "Book A", "Same Author"));
        repository.addBook(new Book(null, "Book B", "Same Author"));

        List<Book> books = repository.findByAuthor("Same Author");

        assertEquals(2, books.size());
    }

    @Test
    void shouldCountBooks() {
        long count = repository.countBooks();

        assertTrue(count >= 2);
    }
}