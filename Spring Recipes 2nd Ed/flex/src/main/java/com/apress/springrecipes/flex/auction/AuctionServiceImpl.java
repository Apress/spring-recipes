package com.apress.springrecipes.flex.auction;

import com.apress.springrecipes.flex.auction.model.Bid;
import com.apress.springrecipes.flex.auction.model.Item;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import org.springframework.stereotype.Service;

import org.springframework.util.Assert;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import javax.jms.*;


@Service("auctionService")
public class AuctionServiceImpl implements AuctionService {
    static private Logger logger = Logger.getLogger(AuctionServiceImpl.class.getName());
    private ConcurrentSkipListSet<Item> items = new ConcurrentSkipListSet<Item>();
    private AtomicInteger uuidForBids = new AtomicInteger(0);
    private AtomicInteger uuidForItems = new AtomicInteger(0);
    private String[] images = "boat,car,carpet,motorbike".split(",");
    @Resource(name = "itemPosted")
    private Topic itemPostedDestination;
    @Resource(name = "bidPosted")
    private Topic bidPostedTopic;
    @Resource(name = "bidAccepted")
    private Topic bidAcceptedTopic;
    @Autowired
    private JmsTemplate jmsTemplate;

    @PostConstruct
    public void setupFakeItems() {
        Assert.isTrue(jmsTemplate != null);

        String[] items = "boat,car,carpet,motorbike".split(",");
        String[] sellerEmails = "gary@gary.com,daniel@daniel.com,josh@josh.com,geoerge@george.com,srinvas@srinvas.com,manuel@manuel.com".split(",");

        for (String item : items) {
            String sellerEmail = sellerEmails[(int) Math.floor(Math.random() * sellerEmails.length)];
            String description = String.format("A lovely %s", item);
            double basePrice = Math.random() * 100;
            String imageUrl = String.format("/images/%s.jpg", item);
            postItem(sellerEmail, item, description, basePrice, imageUrl);
        }

        logger.info(String.format("setupFakeItems(): there are %s items", "" + this.items.size()));
    }

    private Message mapFromBid(javax.jms.Session session, Bid b)
        throws JMSException {
        MapMessage mm = session.createMapMessage();
        mm.setLong("itemId", b.getItem().getId());
        mm.setLong("bidId", b.getId());
        mm.setLong("acceptedTimestamp", (b.getAccepted() == null) ? 0 : b.getAccepted().getTime());
        mm.setDouble("amount", b.getAmount());

        return mm;
    }

    private Message mapFromItem(javax.jms.Session session, Item item)
        throws JMSException {
        MapMessage mapMessage = session.createMapMessage();
        mapMessage.setLong("itemId", item.getId());
        mapMessage.setDouble("threshold", item.getThreshold());
        mapMessage.setDouble("basePrice", item.getBasePrice());
        mapMessage.setString("sellerEmail", item.getSellerEmail());
        mapMessage.setString("description", item.getDescription());

        return mapMessage;
    }

    @Override
    public synchronized void acceptBid(Item item, final Bid bid) {
        Date accepted = new Date();
        item.setSold(accepted);
        bid.setAccepted(accepted);
        jmsTemplate.send(bidAcceptedTopic,
            new MessageCreator() {
                @Override
                public Message createMessage(Session session)
                    throws JMSException {
                    return mapFromBid(session, bid);
                }
            });
    }

    @Override
    public synchronized Bid bid(Item item, double price) {
        final Bid bid = new Bid();
        bid.setAmount(price);
        bid.setItem(item);
        bid.setId(uuidForBids.getAndIncrement());
        item.addBid(bid);
        jmsTemplate.send(bidPostedTopic,
            new MessageCreator() {
                @Override
                public Message createMessage(Session session)
                    throws JMSException {
                    return mapFromBid(session, bid);
                }
            });

        if (item.getThreshold() <= bid.getAmount()) {
            acceptBid(item, bid);
        }

        return bid;
    }

    private String randomImage() {
        int indexOfImage = (int) (Math.random() * (this.images.length - 1));

        return this.images[indexOfImage];
    }

    @Override
    public Item postItem(String sellerEmail, String itemTitle, String description, double basePrice, String imageUrlParam) {
        String imageUrl = imageUrlParam;

        if (StringUtils.isEmpty(imageUrl)) {
            imageUrl = String.format("/images/%s.jpg", randomImage());
            ;
        }

        final Item item = new Item();
        item.setItem(itemTitle);
        item.setBasePrice(basePrice);
        item.setId(uuidForItems.getAndIncrement());
        item.setImageUrl(imageUrl);
        item.setThreshold(10 * 1000);
        item.setDescription(description);
        item.setSellerEmail(sellerEmail);

        System.out.println("adding " + ToStringBuilder.reflectionToString(item));

        items.add(item);
        jmsTemplate.send(itemPostedDestination,
            new MessageCreator() {
                @Override
                public Message createMessage(Session session)
                    throws JMSException {
                    return mapFromItem(session, item);
                }
            });

        return item;
    }

    @Override
    public Set<Item> getItemsForAuction() {
        Set<Item> uniqueItemsForAuction = new HashSet<Item>();
        uniqueItemsForAuction.addAll(this.items);
        CollectionUtils.filter(uniqueItemsForAuction,
            new Predicate() {
                @Override
                public boolean evaluate(Object object) {
                    Item itm = (Item) object;

                    return itm.getSold() == null;
                }
            });

        return uniqueItemsForAuction;
    }
}
