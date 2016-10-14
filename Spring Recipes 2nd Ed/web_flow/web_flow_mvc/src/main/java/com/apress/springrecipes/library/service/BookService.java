package com.apress.springrecipes.library.service;

import com.apress.springrecipes.library.domain.Book;
import com.apress.springrecipes.library.domain.BookCriteria;

import java.util.List;


public interface BookService {
    public List<Book> search(BookCriteria criteria);

    public Book findByIsbn(String isbn);
}
