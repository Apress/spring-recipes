package com.apress.springrecipes.osgi.helloworld.service;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public class GreeterServiceImpl implements GreeterService, GreetingRecorderService {
    private Map<String, String> salutation;
    private Map<String, Integer> greetingTracker;
    private Map<String, Integer> languageTracker;

    public GreeterServiceImpl() {
        languageTracker = new HashMap<String, Integer>();
        greetingTracker = new HashMap<String, Integer>();
        salutation = new HashMap<String, String>();
        salutation.put(Locale.ENGLISH.toString(), "Hello, %s");
        salutation.put(Locale.FRENCH.toString(), "Bonjour, %s");
        salutation.put(Locale.ITALIAN.toString(), "Buongiorno, %s");
    }

    /**
     * @param language Can be any language you want, so long as that language is one of <code>Locale.ENGLISH.toString()</code>,
     *                 <code>Locale.ITALIAN.toString()</code>, or <code>Locale.FRENCH.toString()</code>. :-)
     * @param name     the name of the person you'd like to address
     *
     * @return the greeting, in the language you want, tailored to the name you specified
     */
    public String greet(String language, String name) {
        if (salutation.containsKey(language)) {
            if (!greetingTracker.containsKey(name)) {
                greetingTracker.put(name, 0);
            }

            greetingTracker.put(name, greetingTracker.get(name) + 1);

            if (!languageTracker.containsKey(language)) {
                languageTracker.put(language, 0);
            }

            languageTracker.put(language, languageTracker.get(language) + 1);

            return String.format(salutation.get(language), name);
        }

        throw new RuntimeException(String.format("The language you specified (%s) doesn't exist", language));
    }

    public int getCountOfGreetingsFor(String name) {
        return greetingTracker.get(name);
    }

    public int getCountOfGreetingsIn(String language) {
        return languageTracker.get(language);
    }
}
