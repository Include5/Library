package ru.strelkov.lib.models;

import jakarta.validation.constraints.NotEmpty;

public class Book {
    private int book_id;

    private Integer id;

    @NotEmpty
    private String title;


    private String author;

    @NotEmpty
    private int doc;

    public Book() {

    }

    public Book(int book_id, Integer id, String title, String author, int doc) {
        this.book_id = book_id;
        this.id = id;
        this.title = title;
        this.author = author;
        this.doc = doc;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int movie_id) {
        this.book_id = movie_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getDoc() {
        return doc;
    }

    public void setDoc(int doc) {
        this.doc = doc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Book{" +
                "book_id=" + book_id +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", doc=" + doc +
                '}';
    }
}
