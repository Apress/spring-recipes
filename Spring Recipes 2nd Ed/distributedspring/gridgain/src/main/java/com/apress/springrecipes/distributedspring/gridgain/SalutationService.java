package com.apress.springrecipes.distributedspring.gridgain;

public interface SalutationService {
    String saluteSomeoneInForeignLanguage(String recipient);

    String[] saluteManyPeopleInRandomForeignLanguage(String[] recipients);
}
