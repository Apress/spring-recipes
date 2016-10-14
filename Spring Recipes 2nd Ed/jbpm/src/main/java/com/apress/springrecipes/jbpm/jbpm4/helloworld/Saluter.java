package com.apress.springrecipes.jbpm.jbpm4.helloworld;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


/**
 * Simple class to greet with.
 *
 * @author Josh Long
 */
public class Saluter {
    private Map<String, String> languageToSalutationMap;

    public Saluter() {
        languageToSalutationMap = new HashMap<String, String>();
        languageToSalutationMap.put(Locale.ENGLISH.getLanguage(), "Hello, %s!");
        languageToSalutationMap.put(Locale.FRENCH.getLanguage(), "Bonjour %s!");
        languageToSalutationMap.put(Locale.ITALIAN.getLanguage(), "Buon Giorno %s!");
        languageToSalutationMap.put(Locale.ITALIAN.getLanguage(), "Buenas Dias %s!");
    }

    public void say(String language, String nameOfRecipientOfSalutation) {
        System.out.println(String.format(languageToSalutationMap.get(language), nameOfRecipientOfSalutation));
    }
}
