package com.apress.springrecipes.flex.auction;

import com.apress.springrecipes.flex.auction.model.Bid;
import com.apress.springrecipes.flex.auction.model.Item;

import java.util.Set;


/**
 * provides an implementation of an auction house
 *
 * @author Josh Long
 */
public interface AuctionService {
    Item postItem(String sellerEmail, String item, String description, double minPrice, String imageUrl);

    Bid bid(Item item, double price);

    void acceptBid(Item item, Bid bid);

    Set<Item> getItemsForAuction();
}
