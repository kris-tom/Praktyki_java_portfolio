package com.krdl.task15.config;

import com.krdl.task15.client.Task14RestClient;
import com.krdl.task15.model.Book;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class Task14ClientInitializer implements CommandLineRunner {

    private final Task14RestClient task14Client;

    @Override
    public void run(String... args) {

        log.info("========================================");
        log.info("Testing connection to Task14 API");
        log.info("========================================");

        try {
            List<Book> books = task14Client.getAllBooks();

            long count = books.size();

            log.info("✓ Connected to Task14 API - Books: {}", count);
            log.info("✓ Verified countBooks(): {}", task14Client.countBooks());

            log.info("========================================");
            log.info("Task14 API is ready to use!");
            log.info("========================================");

        } catch (Exception e) {
            log.warn("⚠ Task14 API not available at startup", e);
            log.warn("Task15 will continue running without Task14 dependency");
        }
    }
}