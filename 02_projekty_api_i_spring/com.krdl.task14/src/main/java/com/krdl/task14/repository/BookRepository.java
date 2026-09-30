package com.krdl.task14.repository;

import com.krdl.task14.module.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {


  @Query("SELECT b FROM Book b WHERE LOWER(b.author) = LOWER(:author)")
  List<Book> findByAuthor(@Param("author") String author);


  @Query("SELECT b FROM Book b WHERE LOWER(b.author) = LOWER(:author)")
  Page<Book> findByAuthor(@Param("author") String author, Pageable pageable);


  @Query("SELECT b FROM Book b WHERE LOWER(b.title) = LOWER(:title)")
  List<Book> findByTitle(@Param("title") String title);


  @Query("SELECT b FROM Book b WHERE LOWER(b.title) LIKE LOWER(CONCAT('%', :titlePart, '%')) ORDER BY b.title ASC")
  List<Book> findByTitleContaining(@Param("titlePart") String titlePart);


  @Query("SELECT b FROM Book b WHERE LOWER(b.author) LIKE LOWER(CONCAT('%', :authorPart, '%')) ORDER BY b.author ASC")
  List<Book> findByAuthorContaining(@Param("authorPart") String authorPart);


  @Query("SELECT b FROM Book b WHERE LOWER(b.title) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
         "OR LOWER(b.author) LIKE LOWER(CONCAT('%', :searchTerm, '%')) ORDER BY b.title ASC")
  List<Book> searchBooks(@Param("searchTerm") String searchTerm);

 
  @Query("SELECT b FROM Book b WHERE LOWER(b.title) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
         "OR LOWER(b.author) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
  Page<Book> searchBooks(@Param("searchTerm") String searchTerm, Pageable pageable);


  @Query("SELECT COUNT(b) FROM Book b WHERE LOWER(b.author) = LOWER(:author)")
  long countByAuthor(@Param("author") String author);


  @Query("SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END FROM Book b WHERE LOWER(b.title) = LOWER(:title)")
  boolean existsByTitle(@Param("title") String title);


  @Query("SELECT b FROM Book b ORDER BY b.title ASC")
  List<Book> findAllSortedByTitle();


  @Query("SELECT b FROM Book b ORDER BY b.author ASC, b.title ASC")
  List<Book> findAllSortedByAuthor();


  @Query("SELECT DISTINCT b.author FROM Book b ORDER BY b.author ASC")
  List<String> findDistinctAuthors();


  @Query("SELECT b FROM Book b WHERE LOWER(b.author) = LOWER(:author) ORDER BY b.id LIMIT 1")
  Optional<Book> findFirstByAuthor(@Param("author") String author);


  @Query(value = "DELETE FROM books WHERE LOWER(author) = LOWER(:author)", nativeQuery = true)
  int deleteAllByAuthor(@Param("author") String author);
}
