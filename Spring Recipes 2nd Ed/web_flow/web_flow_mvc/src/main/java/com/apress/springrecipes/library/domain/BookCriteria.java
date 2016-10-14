package com.apress.springrecipes.library.domain;

import java.io.Serializable;


public class BookCriteria implements Serializable {
    private String keyword;
    private String author;

    public String getAuthor() {
        return author;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
