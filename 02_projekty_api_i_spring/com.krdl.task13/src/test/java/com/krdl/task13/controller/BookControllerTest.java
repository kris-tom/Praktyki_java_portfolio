package com.krdl.task13.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.krdl.task13.module.Book;
import com.krdl.task13.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
@AutoConfigureMockMvc(addFilters = false) // 🔥 KLUCZOWA POPRAWKA
class BookControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldGetAllBooks() throws Exception {
        when(repository.getBooks()).thenReturn(
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
        when(repository.countBooks()).thenReturn(5L);

        mvc.perform(get("/api/books/count"))
                .andExpect(status().isOk())
                .andExpect(content().string("5"));
    }
}