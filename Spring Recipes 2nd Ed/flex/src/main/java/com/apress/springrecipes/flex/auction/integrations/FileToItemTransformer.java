package com.apress.springrecipes.flex.auction.integrations;

import com.apress.springrecipes.flex.auction.model.Item;

import org.apache.commons.lang.StringUtils;

import org.springframework.integration.annotation.Transformer;

import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class FileToItemTransformer {
    @Transformer
    public Item transformFromFileStringToItem(String fileContent)
        throws IOException {
        if (StringUtils.isEmpty(fileContent)) {
            throw new RuntimeException("the file content is empty; can't create Item");
        }

        String[] parts = fileContent.split(",");

        if (parts.length != 4) {
            throw new RuntimeException("couldn't parse the file; can't create Item");
        }

        String seller = parts[0];
        String item = parts[1];
        String description = parts[2];
        String basePrice = parts[3];

        Item itemObj = new Item();
        itemObj.setDescription(description);
        itemObj.setItem(item);
        itemObj.setSellerEmail(seller);
        itemObj.setBasePrice(Double.parseDouble(basePrice));

        return itemObj;
    }
}
