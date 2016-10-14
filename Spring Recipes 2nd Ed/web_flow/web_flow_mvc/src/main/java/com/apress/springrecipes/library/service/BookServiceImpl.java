package com.apress.springrecipes.library.service;

import com.apress.springrecipes.library.domain.Book;
import com.apress.springrecipes.library.domain.BookCriteria;

import org.apache.commons.lang.StringUtils;

import java.util.*;


public class BookServiceImpl implements BookService {
    private Map<String, Book> books;

    public BookServiceImpl() {
        books = new HashMap<String, Book>();
        books.put("0001", new Book("0001", "Spring Framework", "Ray", new GregorianCalendar(2010, 0, 1).getTime()));
        books.put("0002", new Book("0002", "Spring Web MVC", "George", new GregorianCalendar(2010, 3, 1).getTime()));
        books.put("0003", new Book("0003", "Spring Web Flow", "Ray", new GregorianCalendar(2010, 6, 1).getTime()));
        books.put("0004", new Book("0004", "Spring Enterprise Recipes", "Steve", new GregorianCalendar(2010, 6, 1).getTime()));
        books.put("0005", new Book("0005", "Spring Web Recipes", "Gary", new GregorianCalendar(2010, 6, 1).getTime()));
    }

    public List<Book> search(BookCriteria criteria) {
        List<Book> results = new ArrayList<Book>();

        for (Book book : books.values()) {
            String keyword = criteria.getKeyword().trim();
            String author = criteria.getAuthor().trim();
            boolean keywordMatches = !StringUtils.isEmpty(keyword) && StringUtils.defaultString(book.getName()).toLowerCase().contains(StringUtils.defaultString(keyword).toLowerCase());
            boolean authorMatches = StringUtils.defaultString(book.getAuthor()).equalsIgnoreCase(author);

            if (keywordMatches || authorMatches) {
                results.add(book);
            }
        }

        return results;
    }

    public Book findByIsbn(String isbn) {
        return books.get(isbn);
    }
}
