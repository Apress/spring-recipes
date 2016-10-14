package com.apress.springrecipes.shop;

import java.util.Date;

public class ProductRanking {

    private Product bestSeller;
    private Date fromDate;
    private Date toDate;

    public Product getBestSeller() {
        return bestSeller;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setBestSeller(Product bestSeller) {
        this.bestSeller = bestSeller;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }
}
