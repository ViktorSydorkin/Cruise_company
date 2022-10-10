package Model.Entity;

public class Language {
    /**
     * Id of language in DB
     */
    long id;
    /**
     * Language title
     */
    String language;

    public Language() {
    }

    public Language(long id, String language) {
        this.id = id;
        this.language = language;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return "Language{" +
                "language='" + language + '\'' +
                '}';
    }
}
