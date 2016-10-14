package com.apress.springrecipes.flex.auction.integrations;

import org.springframework.core.io.Resource;

import org.springframework.stereotype.Component;

import java.io.File;

import javax.annotation.PostConstruct;


@Component
public class FileSystemInitializer {
    @javax.annotation.Resource(name = "itemFilesMount")
    private Resource itemFilesMount;

    @PostConstruct
    public void setup() throws Throwable {
        File file = itemFilesMount.getFile();

        if (!file.exists()) {
            file.mkdirs();
        }

        System.out.println("file system is setup at " + file.getAbsolutePath());
    }
}
