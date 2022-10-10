package Model.Entity;

public class RouteTranslation {
    /**
     * The language of a route's translation
     *
     * @see Language
     */
    private Language language;
    /**
     * The translated start of the route
     */
    private String start;
    /**
     * The translated end of the route
     */
    private String end;

    public RouteTranslation() {

    }

    public RouteTranslation(Language language, String start, String end) {
        this.language = language;
        this.start = start;
        this.end = end;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "RouteTranslation{" +
                "language=" + language +
                ", start='" + start + '\'' +
                ", end='" + end + '\'' +
                '}';
    }
}
