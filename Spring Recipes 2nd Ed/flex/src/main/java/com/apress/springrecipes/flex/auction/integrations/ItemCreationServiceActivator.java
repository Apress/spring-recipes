package com.apress.springrecipes.flex.auction.integrations;

import com.apress.springrecipes.flex.auction.AuctionService;
import com.apress.springrecipes.flex.auction.model.Item;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.integration.annotation.ServiceActivator;

import org.springframework.stereotype.Component;


@Component
public class ItemCreationServiceActivator {
    @Autowired
    private AuctionService auctionService;

    @ServiceActivator
    public void postItem(Item item) throws RuntimeException {
        auctionService.postItem(item.getSellerEmail(), item.getItem(), item.getDescription(), item.getBasePrice(), null);

        System.out.println("posting item!");
    }
}
