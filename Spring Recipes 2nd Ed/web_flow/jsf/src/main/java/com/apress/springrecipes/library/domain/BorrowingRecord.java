package com.apress.springrecipes.library.domain;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class BorrowingRecord implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String isbn;
    private Date borrowDate;
    private Date returnDate;
    private String reader;

    public Date getBorrowDate() {
        return borrowDate;
    }

    public Long getId() {
        return id;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getReader() {
        return reader;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setReader(String reader) {
        this.reader = reader;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}
