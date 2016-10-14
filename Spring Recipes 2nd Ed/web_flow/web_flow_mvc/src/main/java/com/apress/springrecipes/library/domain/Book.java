package com.apress.springrecipes.library.domain;

import java.io.Serializable;

import java.util.Date;


public class Book implements Serializable {
    private String isbn;
    private String name;
    private String author;
    private Date publishDate;

    public Book() {
    }

    public Book(String isbn, String name, String author, Date publishDate) {
        this.isbn = isbn;
        this.name = name;
        this.author = author;
        this.publishDate = publishDate;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getName() {
        return name;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }
}
