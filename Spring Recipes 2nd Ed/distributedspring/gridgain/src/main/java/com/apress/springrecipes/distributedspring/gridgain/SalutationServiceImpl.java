package com.apress.springrecipes.distributedspring.gridgain;

import org.apache.commons.lang.StringUtils;
import org.gridgain.grid.gridify.Gridify;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;


/**
 * Admittedly trivial example of saying 'hello' in a few languages
 *
 * @author Josh Long
 */
public class SalutationServiceImpl implements SalutationService, Serializable {
    private static final long serialVersionUID = 1L;
    private Map<String, String> salutations;

    public SalutationServiceImpl() {
        salutations = new HashMap<String, String>();
        salutations.put(Locale.FRENCH.getLanguage().toLowerCase(), "bonjour %s!");
        salutations.put(Locale.ITALIAN.getLanguage().toLowerCase(), "buongiorno %s!");
        salutations.put(Locale.ENGLISH.getLanguage().toLowerCase(), "hello %s!");
    }

    @Gridify
    public String saluteSomeoneInForeignLanguage(String recipient) {
        Locale[] locales = new Locale[] { Locale.FRENCH, Locale.ENGLISH, Locale.ITALIAN };
        Locale locale = locales[(int) Math.floor(Math.random() * locales.length)];
        String language = locale.getLanguage();
        Set<String> languages = salutations.keySet();

        if (!languages.contains(language)) {
            throw new java.lang.RuntimeException(String.format("this isn't supported! You need to choose " + "from among the accepted languages: %s", StringUtils.join(languages.iterator(), ",")));
        }

        String salutation = String.format(salutations.get(language), recipient);
        System.out.println(String.format("returning: %s", salutation));

        return salutation;
    }

    @Gridify(taskClass = MultipleSalutationTask.class)
    public String[] saluteManyPeopleInRandomForeignLanguage(String[] recipients) {
        return recipients;
    }


}
