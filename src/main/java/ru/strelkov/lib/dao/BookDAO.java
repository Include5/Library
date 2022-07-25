package ru.strelkov.lib.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.strelkov.lib.models.Book;
import ru.strelkov.lib.models.Person;

import java.util.List;

@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book show(int book_id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE book_id=?", new Object[]{book_id}, new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);
    }

    public Person showPersonBook(int id) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO Book(title, author, doc) VALUES(?, ?, ?)", book.getTitle(), book.getAuthor(), book.getDoc());
    }

    public void update(int id, Book updateBook) {
        jdbcTemplate.update("UPDATE Book SET title=?, author=?, doc=? WHERE book_id=?", updateBook.getTitle(), updateBook.getAuthor(), updateBook.getDoc(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Book WHERE book_id=?", id);
    }

    public void updateOwner(int id, int book_id) {
        jdbcTemplate.update("UPDATE Book SET id=? WHERE book_id=?", id, book_id);
    }

    public void deleteOwner(int book_id) {
        jdbcTemplate.update("UPDATE Book SET id=null WHERE book_id=?", book_id);
    }

    public List<Book> showPeopleBook(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE id = ?", new BeanPropertyRowMapper<>(Book.class), id);
    }
}
