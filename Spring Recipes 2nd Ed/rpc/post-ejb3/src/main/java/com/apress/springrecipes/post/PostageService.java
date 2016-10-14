package com.apress.springrecipes.post;

public interface PostageService {
    public double calculatePostage(String country, double weight);
}
