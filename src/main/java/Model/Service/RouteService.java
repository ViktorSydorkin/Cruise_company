package Model.Service;

import Model.Entity.Route;

import java.util.List;

public interface RouteService {
    List<Route> getAllRoutes(long lang_id);
}
