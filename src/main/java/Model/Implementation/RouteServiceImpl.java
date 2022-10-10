package Model.Implementation;

import DAO.DAOFactory;
import DAO.Interfaces.RouteDAO;
import Model.Entity.Route;
import Model.Service.RouteService;
import org.apache.log4j.Logger;

import java.util.List;

public class RouteServiceImpl implements RouteService {
    private final RouteDAO routeDAO;
    Logger logger;

    /**
     * Creates an instance of route's implementation
     */
    public RouteServiceImpl() {
        this.routeDAO = DAOFactory.getInstance().newRouteImplInstance();
        logger = Logger.getLogger(RouteServiceImpl.class);
    }

    /**
     * Gets all routes from the DB
     *
     * @param lang_id - id of language the routes should be translated to
     * @return - list of routes
     */
    @Override
    public List<Route> getAllRoutes(long lang_id) {
        logger.info("List of routes is going to be selected");
        return routeDAO.getAll(lang_id);
    }
}
