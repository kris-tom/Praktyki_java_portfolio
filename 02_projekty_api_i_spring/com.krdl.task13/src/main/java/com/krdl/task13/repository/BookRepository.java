package com.krdl.task13.repository;

import com.krdl.task13.module.Book;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class BookRepository {

  private static final String TABLE_BOOKS = "books";
  private static final String COLUMN_ID = "id";
  private static final String COLUMN_TITLE = "title";
  private static final String COLUMN_AUTHOR = "author";

  private static final String SQL_SELECT_ALL =
      "SELECT %s, %s, %s FROM %s ORDER BY %s";

  private static final String SQL_SELECT_BY_ID =
      "SELECT %s, %s, %s FROM %s WHERE %s = ?";

  private static final String SQL_INSERT =
      "INSERT INTO %s(%s, %s) VALUES (?, ?)";

  private static final String SQL_UPDATE_BY_ID =
      "UPDATE %s SET %s = ?, %s = ? WHERE %s = ?";

  private static final String SQL_DELETE_BY_ID =
      "DELETE FROM %s WHERE %s = ?";

  private static final String SQL_SELECT_BY_AUTHOR =
      "SELECT %s, %s, %s FROM %s WHERE LOWER(%s) = LOWER(?)";

  private static final String SQL_COUNT =
      "SELECT COUNT(*) FROM %s";

  private final JdbcTemplate jdbc;

  private final RowMapper<Book> mapper =
      (rs, rowNum) -> new Book(
          rs.getLong(COLUMN_ID),
          rs.getString(COLUMN_TITLE),
          rs.getString(COLUMN_AUTHOR)
      );

  public BookRepository(JdbcTemplate jdbc) {
    this.jdbc = jdbc;
  }

  public List<Book> getBooks() {
    String sql = String.format(SQL_SELECT_ALL,
        COLUMN_ID, COLUMN_TITLE, COLUMN_AUTHOR, TABLE_BOOKS, COLUMN_ID);
    return jdbc.query(sql, mapper);
  }

  public Optional<Book> findById(Long id) {
    String sql = String.format(SQL_SELECT_BY_ID,
        COLUMN_ID, COLUMN_TITLE, COLUMN_AUTHOR, TABLE_BOOKS, COLUMN_ID);

    try {
      return Optional.ofNullable(jdbc.queryForObject(sql, mapper, id));
    } catch (EmptyResultDataAccessException ex) {
      return Optional.empty();
    }
  }

  public Book addBook(Book book) {
    String sql = String.format(SQL_INSERT,
        TABLE_BOOKS, COLUMN_TITLE, COLUMN_AUTHOR);

    KeyHolder keyHolder = new GeneratedKeyHolder();

    jdbc.update(connection -> {
      PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      ps.setString(1, book.getTitle());
      ps.setString(2, book.getAuthor());
      return ps;
    }, keyHolder);

    Number key = keyHolder.getKey();
    if (key != null) {
      book.setId(key.longValue());
    }

    return book;
  }

  public Optional<Book> updateBook(Long id, Book updatedBook) {
    String sql = String.format(SQL_UPDATE_BY_ID,
        TABLE_BOOKS, COLUMN_TITLE, COLUMN_AUTHOR, COLUMN_ID);

    int updated = jdbc.update(sql,
        updatedBook.getTitle(),
        updatedBook.getAuthor(),
        id);

    return updated == 0 ? Optional.empty() : findById(id);
  }

  public void deleteById(Long id) {
    String sql = String.format(SQL_DELETE_BY_ID,
        TABLE_BOOKS, COLUMN_ID);
    jdbc.update(sql, id);
  }

  public List<Book> findByAuthor(String author) {
    String sql = String.format(SQL_SELECT_BY_AUTHOR,
        COLUMN_ID, COLUMN_TITLE, COLUMN_AUTHOR,
        TABLE_BOOKS, COLUMN_AUTHOR);

    return jdbc.query(sql, mapper, author);
  }

  public long countBooks() {
    String sql = String.format(SQL_COUNT, TABLE_BOOKS);
    Long cnt = jdbc.queryForObject(sql, Long.class);
    return cnt == null ? 0L : cnt;
  }
}