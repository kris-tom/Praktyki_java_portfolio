package com.krdl.task14.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.krdl.task14.module.Book;
import com.krdl.task14.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
@AutoConfigureMockMvc(addFilters = false)
class BookControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldGetAllBooks() throws Exception {
        when(repository.findAll()).thenReturn(
                List.of(new Book(1L, "Title", "Author"))
        );

        mvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Title"));
    }

    @Test
    void shouldGetBookById() throws Exception {
        when(repository.findById(1L))
                .thenReturn(Optional.of(new Book(1L, "Test", "Author")));

        mvc.perform(get("/api/books/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test"));
    }

    @Test
    void shouldReturn404WhenBookNotFound() throws Exception {
        when(repository.findById(1L))
                .thenReturn(Optional.empty());

        mvc.perform(get("/api/books/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldSearchByAuthor() throws Exception {
        when(repository.findByAuthor("Author"))
                .thenReturn(List.of(new Book(1L, "Title", "Author")));

        mvc.perform(get("/api/books/search?author=Author"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].author").value("Author"));
    }

    @Test
    void shouldCountBooks() throws Exception {
        when(repository.count()).thenReturn(5L);

        mvc.perform(get("/api/books/count"))
                .andExpect(status().isOk())
                .andExpect(content().string("5"));
    }

    @Test
    void shouldCountBooksByAuthor() throws Exception {
        when(repository.countByAuthor("Tolkien"))
                .thenReturn(2L);

        mvc.perform(get("/api/books/count/author?author=Tolkien"))
                .andExpect(status().isOk())
                .andExpect(content().string("2"));
    }

    @Test
    void shouldFindBooksByTitle() throws Exception {
        when(repository.findByTitle("Clean Code"))
                .thenReturn(List.of(new Book(1L, "Clean Code", "Robert C. Martin")));

        mvc.perform(get("/api/books/title?title=Clean Code"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Clean Code"));
    }

    @Test
    void shouldFindBooksByTitleContaining() throws Exception {
        when(repository.findByTitleContaining("Harry"))
                .thenReturn(List.of(
                        new Book(5L, "Harry Potter i Kamień Filozoficzny", "J.K. Rowling"),
                        new Book(6L, "Harry Potter i Komnata Tajemnic", "J.K. Rowling")
                ));

        mvc.perform(get("/api/books/title/contains?titlePart=Harry"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].title").value("Harry Potter i Kamień Filozoficzny"));
    }

    @Test
    void shouldFindBooksByAuthorContaining() throws Exception {
        when(repository.findByAuthorContaining("Dos"))
                .thenReturn(List.of(new Book(8L, "Zbrodnia i kara", "Fiodor Dostojewski")));

        mvc.perform(get("/api/books/author/contains?authorPart=Dos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].author").value("Fiodor Dostojewski"));
    }

    @Test
    void shouldAdvancedSearch() throws Exception {
        when(repository.searchBooks("Orwell"))
                .thenReturn(List.of(
                        new Book(11L, "Rok 1984", "George Orwell"),
                        new Book(12L, "Folwark zwierzęcy", "George Orwell")
                ));

        mvc.perform(get("/api/books/advanced-search?searchTerm=Orwell"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void shouldCheckIfTitleExists() throws Exception {
        when(repository.existsByTitle("Clean Code"))
                .thenReturn(true);

        mvc.perform(get("/api/books/exists/title?title=Clean Code"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    void shouldGetAllSortedByTitle() throws Exception {
        List<Book> books = List.of(
                new Book(1L, "Clean Code", "Robert C. Martin"),
                new Book(2L, "Effective Java", "Joshua Bloch")
        );
        when(repository.findAllSortedByTitle())
                .thenReturn(books);

        mvc.perform(get("/api/books/sorted/title"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].title").value("Clean Code"));
    }

    @Test
    void shouldGetDistinctAuthors() throws Exception {
        when(repository.findDistinctAuthors())
                .thenReturn(List.of("George Orwell", "J.K. Rowling", "Robert C. Martin"));

        mvc.perform(get("/api/books/authors"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3))
                .andExpect(jsonPath("$[0]").value("George Orwell"));
    }

    @Test
    void shouldGetFirstBookByAuthor() throws Exception {
        when(repository.findFirstByAuthor("Tolkien"))
                .thenReturn(Optional.of(new Book(4L, "Hobbit", "J.R.R. Tolkien")));

        mvc.perform(get("/api/books/first/author?author=Tolkien"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Hobbit"));
    }

    @Test
    void shouldDeleteAllByAuthor() throws Exception {
        when(repository.deleteAllByAuthor("Orwell"))
                .thenReturn(2);

        mvc.perform(delete("/api/books/author?author=Orwell"))
                .andExpect(status().isOk())
                .andExpect(content().string("Deleted 2 book(s) by author: Orwell"));
    }
}
