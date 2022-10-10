package Model.Entity;

public class CruiseTranslation {
    /**
     * The language of cruise's translations
     *
     * @see Language
     */
    Language language;
    /**
     * The translated cruise's title
     */
    String title;

    public CruiseTranslation() {
    }

    public CruiseTranslation(Language language, String title) {
        this.language = language;
        this.title = title;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }


}
