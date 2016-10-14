package com.apress.springrecipes.flex.auction.model;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

import java.util.Date;
import java.util.LinkedHashSet;


public class Item implements Serializable, Comparable<Item> {
    private static final long serialVersionUID = 1L;
    private String description;
    private String sellerEmail;
    private String item;
    private long id;
    private double threshold;
    private double basePrice;
    private LinkedHashSet<Bid> bids = new LinkedHashSet<Bid>();
    private Date sold;
    private String imageUrl;

    public Bid getHighestBid() {
        if (this.bids.size() == 0) {
            return null;
        }

        Bid selectedBid = null;
        double amount = 0;

        for (Bid b : this.bids) {
            if (b.getAmount() > amount) {
                selectedBid = b;
                amount = b.getAmount();
            }
        }

        return selectedBid;
    }

    /**
     * Naive implementation. If there's already a bid of higher value, then the bid is rejected, otherwise it's
     * appended.
     *
     * @param b the bid for the item
     */
    public synchronized void addBid(Bid b) {
        if (bids.contains(b)) {
            return;
        }

        final double price = b.getAmount();

        Bid bidWithHigherPrice = (Bid) CollectionUtils.find(bids,
                new Predicate() {
                    @Override
                    public boolean evaluate(Object object) {
                        Bid bid = (Bid) object;

                        return bid.getAmount() > price;
                    }
                });

        if (bidWithHigherPrice == null) {
            this.bids.add(b);
        }
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getItem() {
        return item;
    }

    public void setSellerEmail(String sellerEmail) {
        this.sellerEmail = sellerEmail;
    }

    public String getSellerEmail() {
        return sellerEmail;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj, new String[] { "bids" });
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this, new String[] { "bids" });
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setSold(Date sold) {
        this.sold = sold;
    }

    public Date getSold() {
        return sold;
    }

    @Override
    public int compareTo(Item o) {
        return Long.valueOf(o.getId()).compareTo(Long.valueOf(getId()));
    }

    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }

    public double getThreshold() {
        return threshold;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
