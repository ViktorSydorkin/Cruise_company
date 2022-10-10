package Model.Entity;

import java.util.ArrayList;
import java.util.List;

public class Route {
    /**
     * Id of the route in DB
     */
    private long id;
    /**
     * Amount of the ports along the route
     */
    private int port_amount;
    /**
     * List of route's translation
     *
     * @see RouteTranslation
     */
    private List<RouteTranslation> routeTranslationList;

    public Route() {
        this.routeTranslationList = new ArrayList<>();
    }

    public Route(long id, int port_amount, List<RouteTranslation> routeTranslationList) {
        this.id = id;
        this.port_amount = port_amount;
        this.routeTranslationList = routeTranslationList;
    }

    public List<RouteTranslation> getRouteTranslationList() {
        return routeTranslationList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getPort_amount() {
        return port_amount;
    }

    public void setPort_amount(int port_amount) {
        this.port_amount = port_amount;
    }

    @Override
    public String toString() {
        return "Route{" +
                "port_amount=" + port_amount +
                ", routeTranslationList=" + routeTranslationList +
                '}';
    }
}
