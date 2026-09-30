package com.krdl.task15.client;

import com.krdl.task15.model.Book;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import java.util.Collections;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class Task14RestClient {

    private final RestClient restClient;

    /**
     * FIX: base URL WITHOUT /books
     */
    @Value("${task14.api.url:http://localhost:8081/task14/api}")
    private String task14Url;

    @Value("${task14.api.key.header:X-API-KEY}")
    private String apiKeyHeader;

    @Value("${task14.api.key.value:super-secret-api-key}")
    private String apiKeyValue;

    private RestClient.RequestHeadersSpec<?> get(String path) {
        return restClient.get()
                .uri(task14Url + path)
                .header(apiKeyHeader, apiKeyValue);
    }

    private RestClient.RequestBodySpec post(String path) {
        return restClient.post()
                .uri(task14Url + path)
                .header(apiKeyHeader, apiKeyValue);
    }

    private RestClient.RequestBodySpec put(String path, Object... vars) {
        return restClient.put()
                .uri(task14Url + path, vars)
                .header(apiKeyHeader, apiKeyValue);
    }

    private RestClient.RequestHeadersSpec<?> delete(String path, Object... vars) {
        return restClient.delete()
                .uri(task14Url + path, vars)
                .header(apiKeyHeader, apiKeyValue);
    }

    public List<Book> getAllBooks() {
        try {
            log.info("GET {}/books", task14Url);

            List<Book> books = get("/books")
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {});

            return books != null ? books : Collections.emptyList();

        } catch (RestClientException e) {
            log.error("Error fetching books", e);
            return Collections.emptyList();
        }
    }

    public Book getBookById(Long id) {
        try {
            return get("/books/{id}")
                    .retrieve()
                    .body(Book.class);

        } catch (RestClientException e) {
            log.error("Error fetching book {}", id, e);
            return null;
        }
    }

    public Book createBook(Book book) {
        try {
            return post("/books")
                    .body(book)
                    .retrieve()
                    .body(Book.class);

        } catch (RestClientException e) {
            log.error("Error creating book", e);
            return null;
        }
    }

    public Book updateBook(Long id, Book book) {
        try {
            return put("/books/{id}", id)
                    .body(book)
                    .retrieve()
                    .body(Book.class);

        } catch (RestClientException e) {
            log.error("Error updating book {}", id, e);
            return null;
        }
    }

    public void deleteBook(Long id) {
        try {
            delete("/books/{id}", id)
                    .retrieve()
                    .toBodilessEntity();

        } catch (RestClientException e) {
            log.error("Error deleting book {}", id, e);
        }
    }

    public List<Book> searchByAuthor(String author) {
        try {
            return get("/books/search?author={author}")
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {});

        } catch (RestClientException e) {
            log.error("Error searching books by author {}", author, e);
            return Collections.emptyList();
        }
    }

    /**
     * SAFE fallback – no /count endpoint required
     */
    public long countBooks() {
        try {
            List<Book> books = get("/books")
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {});

            return books != null ? books.size() : 0;

        } catch (RestClientException e) {
            log.warn("Task14 unavailable during countBooks()", e);
            return 0;
        }
    }
}