package app.controller;

import app.domain.model.Serialization.ObjectSerializer;

import java.util.List;
import java.util.Locale;

/**
 * The type Change language controller.
 */
public class ChangeLanguageController {
    private App app;

    /**
     * Instantiates a new Change language controller.
     */
    public ChangeLanguageController(){
        this.app = App.getInstance();
        ObjectSerializer obj = new ObjectSerializer();
        obj.serialize();
    }

    /**
     * Get language options list.
     *
     * @return the list
     */
    public List<Locale> getLanguageOptions(){
        return this.app.languageOptions();
    }

    /**
     * Choose language boolean.
     *
     * @param locale the locale
     * @return the boolean
     */
    public boolean chooseLanguage(Object locale){
        Locale language = (Locale) locale;
        return this.app.setLanguage(language);
    }
}
