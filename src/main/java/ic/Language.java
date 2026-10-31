package ic;

import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

public class Language {
    private Locale locale;
    private ResourceBundle rb;
    private ArrayList<Locale> localeList = new ArrayList<>();
    
    
    public Language() {
        localeList.add(new Locale("en", "US"));
        localeList.add(new Locale("fi", "FI"));
        localeList.add(new Locale("sv", "SE"));
        localeList.add(new Locale("ja", "JP"));
        localeList.add(new Locale("ar", "EG"));
        
        this.locale = localeList.get(0); //default localization
        this.rb = ResourceBundle.getBundle("Messages_bundle", locale);
    }

    public String getResource(String resource) {
        return rb.getString(resource);
    }

    public void setLocaleById(int localeIndex) {
        locale = localeList.get(localeIndex);
        rb = ResourceBundle.getBundle("Messages_bundle", locale);
        System.out.println(locale.getDisplayLanguage());
    }

    public void setLocaleByName(String localeName) {
        for (int i = 0; i < localeList.toArray().length; i++) {
            if (localeList.get(i).getDisplayLanguage().contains(localeName)) {
                this.locale = localeList.get(i);
            }
        }
        rb = ResourceBundle.getBundle("Messages_bundle", locale);
    }

    public ArrayList<String> getLocaleStrings() {
        ArrayList<String> localeStrings = new ArrayList<>();
        for (Locale locale : localeList) {
            localeStrings.add(locale.getDisplayLanguage());
        }
        return localeStrings;
    }
}
