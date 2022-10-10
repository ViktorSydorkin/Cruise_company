package DAO.Implementation;

import DAO.Interfaces.RouteDAO;
import DAO.Util.ConnectionPool;
import Model.Entity.Route;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RouteImpl implements RouteDAO {

    private final String ADD_ROUTE = "INSERT INTO route (ports_amount) VALUES (?);";
    private final String ADD_LANGUAGE_ROUTE = "INSERT INTO language_route (start, end, fk_lr_language_id, fk_lr_route_id) VALUES (?, ?, ?, ?);";
    private final String GET_ALL_ROUTES = """
            SELECT *
            FROM route
            INNER JOIN language_route ON fk_lr_route_id = route_id AND fk_lr_language_id = ?
            INNER JOIN language ON language_id = language_route.fk_lr_language_id;""";
    private final String UPDATE_ROUTE = "UPDATE route SET ports_amount = ? WHERE route_id = ?;";
    private final String UPDATE_LANGUAGE_ROUTE = "UPDATE language_route SET start = ?, end = ? WHERE fk_lr_route_id = ? AND fk_lr_language_id = ?;";
    private final String DELETE_ROUTE = "DELETE FROM route WHERE route_id = ?;";

    private ConnectionPool cp;
    private Connection c;
    private PreparedStatement pr;
    private ResultSet rs;
    private Logger logger;
    private int count;

    public RouteImpl(ConnectionPool connectionPool) {
        this.cp = connectionPool;
        logger = Logger.getLogger(RouteImpl.class);
    }

    /**
     * Add a new route to DB
     *
     * @param route - new route to be added
     * @return - true if route was added
     * @see Route
     */
    @Override
    public boolean add(Route route) {
        c = cp.getConnection();
        try {
            count = 0;
            c.setAutoCommit(false);
            c.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            pr = c.prepareStatement(ADD_ROUTE, Statement.RETURN_GENERATED_KEYS);
            pr.setInt(1, route.getPort_amount());
            count += pr.executeUpdate();
            rs = pr.getGeneratedKeys();
            if (rs.next()) {
                route.setId(rs.getLong(1));
            }
            count = Executor.langRouteSetter(c, route, ADD_LANGUAGE_ROUTE, count);
            if (count == route.getRouteTranslationList().size() + 1) {
                c.commit();
                logger.info("Route was added, transaction is committed");
                return true;
            } else {
                cp.rollback(c);
                logger.warn("Route wasn't added, transaction is rolled back");
                return false;
            }
        } catch (SQLException e) {
            logger.error("DAO caught an SQL exception while adding the route", e);
            cp.rollback(c);
            e.printStackTrace();
            return false;
        } finally {
            cp.close(rs);
            cp.close(pr);
            cp.close(c);
        }
    }


    /**
     * Gets the list of routes from DB
     *
     * @param lang_id - language of the route translation
     * @return - list of routes
     */
    @Override
    public List<Route> getAll(long... lang_id) {
        c = cp.getConnection();
        List<Route> routeList = new ArrayList<>();
        try {
            pr = c.prepareStatement(GET_ALL_ROUTES);
            pr.setLong(1, lang_id[0]);
            rs = pr.executeQuery();
            while (rs.next()) {
                routeList.add(Executor.routeCreation(rs));
            }
            logger.info("List of routes was selected");
            return routeList;
        } catch (SQLException e) {
            logger.error("DAO caught an SQL exception while selecting the routes list", e);
            e.printStackTrace();
            return null;
        } finally {
            cp.close(rs);
            cp.close(pr);
            cp.close(c);
        }
    }

    /**
     * Updates the route in DB
     *
     * @param route - route to be updated
     * @return - true if route was updated
     * @see Route
     */
    @Override
    public boolean update(Route route) {
        c = cp.getConnection();
        try {
            count = 0;
            c.setAutoCommit(false);
            c.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            pr = c.prepareStatement(UPDATE_ROUTE, Statement.RETURN_GENERATED_KEYS);
            pr.setInt(1, route.getPort_amount());
            pr.setLong(2, route.getId());
            count += pr.executeUpdate();
            rs = pr.getGeneratedKeys();
            if (rs.next()) {
                route.setId(rs.getLong(1));
            }
            count = Executor.langRouteSetter(c, route, UPDATE_LANGUAGE_ROUTE, count);
            if (count == route.getRouteTranslationList().size() + 1) {
                c.commit();
                logger.info("Route was updated, transaction is committed");
                return true;
            } else {
                cp.rollback(c);
                logger.warn("Route wasn't updated, transaction is rolled back");
                return false;
            }
        } catch (SQLException e) {
            logger.error("DAO caught an SQL exception while updating the route", e);
            cp.rollback(c);
            e.printStackTrace();
            return false;
        } finally {
            cp.close(pr);
            cp.close(c);
        }
    }

    /**
     * Removes the route from DB
     *
     * @param id - id of route to be deleted
     * @return true if route was deleted
     */
    @Override
    public boolean remove(long id) {
        return Executor.delete(DELETE_ROUTE, id);
    }


}
