package com.apress.springrecipes.bookshop.spring;

import java.util.List;


public interface Cashier {
    public void checkout(List<String> isbns, String username);
}
