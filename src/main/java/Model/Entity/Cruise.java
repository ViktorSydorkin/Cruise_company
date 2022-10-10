package Model.Entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Cruise {
    /**
     * Id of cruise in DB
     */
    private long id;
    /**
     * Time of the cruise's start
     */
    private Timestamp start;
    /**
     * End of the cruise's start
     */
    private Timestamp end;
    /**
     * Cruises price
     */
    private int price;
    /**
     * Amount of available places
     */
    private int available;
    /**
     * Route the cruise is following
     *
     * @see Route
     */
    private Route route;
    /**
     * The cruise's liner
     *
     * @see Liner
     */
    private Liner liner;
    /**
     * The list of cruise's translations
     */
    private List<CruiseTranslation> cruiseTranslationList;

    public Cruise(long id, Timestamp start, Timestamp end, int price, int available, Route route, Liner liner, List<CruiseTranslation> cruiseTranslationList) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.price = price;
        this.available = available;
        this.route = route;
        this.liner = liner;
        this.cruiseTranslationList = cruiseTranslationList;
    }

    public Cruise() {
        this.cruiseTranslationList = new ArrayList<>();
    }

    public List<CruiseTranslation> getCruiseTranslationList() {
        return cruiseTranslationList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Timestamp getStart() {
        return start;
    }

    public void setStart(Timestamp start) {
        this.start = start;
    }

    public Timestamp getEnd() {
        return end;
    }

    public void setEnd(Timestamp end) {
        this.end = end;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Liner getLiner() {
        return liner;
    }

    public void setLiner(Liner liner) {
        this.liner = liner;
    }

    @Override
    public String toString() {
        return "Cruise{" +
                "start=" + start +
                ", end=" + end +
                ", price=" + price +
                ", available=" + available +
                ", route=" + route +
                ", liner=" + liner +
                ", cruiseTranslationList=" + cruiseTranslationList +
                '}';
    }
}
