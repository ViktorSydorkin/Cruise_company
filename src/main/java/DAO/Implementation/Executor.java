package DAO.Implementation;

import DAO.Util.ConnectionPool;
import Model.Entity.*;
import Model.Entity.Enums.Approved;
import Model.Entity.Enums.Closed;
import Model.Entity.Enums.Ended;
import Model.Entity.Enums.Paid;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Executor {

    private static final Logger logger = Logger.getLogger(Executor.class);

    private Executor() {
    }

    /**
     * Removes the subject from the DB
     *
     * @param query - sql query
     * @param id    - id of the object to be removed
     * @return - true if object was removed
     */
    public static boolean delete(String query, long id) {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection c = cp.getConnection();
        PreparedStatement pr = null;

        try {
            pr = c.prepareStatement(query);
            pr.setLong(1, id);
            logger.info("Element is going to be removed");
            return pr.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            cp.close(pr);
            cp.close(c);
        }
    }

    /**
     * Creates an instance of application by the data from the DB
     *
     * @param rs - data from the DB
     * @return - an application instance
     * @throws SQLException - if there is a problem with SQL query
     * @see Application
     */
    public static Application applicationCreation(ResultSet rs) throws SQLException {
        Application application = new Application();
        application.setId(rs.getLong("application_id"));
        application.setPass_photo(rs.getBlob("pass_photo").getBinaryStream());
        application.setApproved(Approved.valueOf(rs.getString("approved")));
        application.setPaid(Paid.valueOf(rs.getString("paid")));
        application.setClosed(Closed.valueOf(rs.getString("closed")));
        application.setEnded(Ended.valueOf(rs.getString("ended")));
        application.setUser(userCreation(rs));
        application.setCruise(cruiseCreation(rs));
        logger.info("Application instance was created");
        return application;
    }

    /**
     * Creates an instance of cruise by the data from the DB
     *
     * @param rs - data from the db
     * @return - a cruise instance
     * @throws SQLException - if there is a problem with SQL query
     * @see Cruise
     */
    public static Cruise cruiseCreation(ResultSet rs) throws SQLException {
        Cruise cruise = new Cruise();
        cruise.setId(rs.getLong("cruise_id"));
        cruise.setStart(rs.getTimestamp("start_date"));
        cruise.setEnd(rs.getTimestamp("end_date"));
        cruise.setPrice(rs.getInt("price"));
        cruise.setAvailable(rs.getInt("available"));
        cruise.setRoute(routeCreation(rs));
        cruise.setLiner(linerCreation(rs));
        cruise.getCruiseTranslationList().add(cruiseTranslationCreation(rs));
        logger.info("Cruise instance was created");
        return cruise;
    }

    /**
     * Creates an instance of cruise translations by the data from the DB
     *
     * @param rs - data from the db
     * @return - a cruise translations instance
     * @throws SQLException - if there is a problem with SQL query
     * @see CruiseTranslation
     */
    public static CruiseTranslation cruiseTranslationCreation(ResultSet rs) throws SQLException {
        CruiseTranslation cruiseTranslation = new CruiseTranslation();
        cruiseTranslation.setTitle(rs.getString("cruise_title"));
        cruiseTranslation.setLanguage(languageCreation(rs));
        logger.info("CruiseTranslation instance was created");
        return cruiseTranslation;

    }

    /**
     * Creates an instance of user by the data from the DB
     *
     * @param rs - data from the db
     * @return - a user instance
     * @throws SQLException - if there is a problem with SQL query
     * @see User
     */
    public static User userCreation(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("user_id"));
        user.setName(rs.getString("name"));
        user.setSurname(rs.getString("surname"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setRole(roleCreation(rs));
        user.setLanguage(languageCreation(rs));
        logger.info("User instance was created");
        return user;
    }

    /**
     * Creates an instance of role by the data from the DB
     *
     * @param rs - data from the db
     * @return - a role instance
     * @throws SQLException - if there is a problem with SQL query
     * @see Role
     */
    public static Role roleCreation(ResultSet rs) throws SQLException {
        Role role = new Role();
        role.setId(rs.getLong("role_id"));
        role.setType(rs.getString("type"));
        logger.info("Role instance was created");
        return role;
    }

    /**
     * Creates an instance of route by the data from the DB
     *
     * @param rs - data from the db
     * @return - a route instance
     * @throws SQLException - if there is a problem with SQL query
     * @see Route
     */
    public static Route routeCreation(ResultSet rs) throws SQLException {
        Route route = new Route();
        route.setId(rs.getLong("route_id"));
        route.setPort_amount(rs.getInt("ports_amount"));
        route.getRouteTranslationList().add(routeTranslationCreation(rs));
        logger.info("Route instance was created");
        return route;
    }

    /**
     * Creates an instance of route translations by the data from the DB
     *
     * @param rs - data from the db
     * @return - a route translations instance
     * @throws SQLException - if there is a problem with SQL query
     * @see RouteTranslation
     */
    public static RouteTranslation routeTranslationCreation(ResultSet rs) throws SQLException {
        RouteTranslation routeTranslation = new RouteTranslation();
        routeTranslation.setStart(rs.getString("start"));
        routeTranslation.setEnd(rs.getString("end"));
        routeTranslation.setLanguage(languageCreation(rs));
        logger.info("RouteTranslation instance was created");
        return routeTranslation;
    }

    /**
     * Creates an instance of language by the data from the DB
     *
     * @param rs - data from the db
     * @return - a language instance
     * @throws SQLException - if there is a problem with SQL query
     * @see Language
     */
    public static Language languageCreation(ResultSet rs) throws SQLException {
        Language language = new Language();
        language.setId(rs.getLong("language_id"));
        language.setLanguage(rs.getString("language"));
        logger.info("language instance was created");
        return language;
    }

    /**
     * Creates an instance of staff by the data from the DB
     *
     * @param rs - data from the db
     * @return - a staff instance
     * @throws SQLException - if there is a problem with SQL query
     * @see Staff
     */
    public static Staff staffCreation(ResultSet rs) throws SQLException {
        Staff staff = new Staff();
        staff.setId(rs.getLong("staff_id"));
        staff.setName(rs.getString("staff_name"));
        staff.setSurname(rs.getString("staff_surname"));
        staff.setPost(rs.getString("post"));
        staff.setLiner(linerCreation(rs));
        logger.info("Staff instance was created");
        return staff;
    }

    /**
     * Creates an instance of liner by the data from the DB
     *
     * @param rs - data from the db
     * @return - a liner instance
     * @throws SQLException - if there is a problem with SQL query
     * @see Liner
     */
    public static Liner linerCreation(ResultSet rs) throws SQLException {
        Liner liner = new Liner();
        if(rs.getLong("liner_id")==0)
            return liner;
        liner.setId(rs.getLong("liner_id"));
        liner.setCapacity(rs.getInt("capacity"));
        liner.setName(rs.getString("liner_name"));
        liner.setDeck_amount(rs.getInt("deck_amount"));
            liner.setLiner_photo(rs.getBlob("liner_photo").getBinaryStream());
        liner.setCompany(companyCreation(rs));
        logger.info("Liner instance was created");
        return liner;
    }

    /**
     * Creates an instance of company by the data from the DB
     *
     * @param rs - data from the db
     * @return - a company instance
     * @throws SQLException - if there is a problem with SQL query
     * @see Company
     */
    public static Company companyCreation(ResultSet rs) throws SQLException {
        Company company = new Company();
        company.setId(rs.getLong("company_id"));
        company.setTitle(rs.getString("company_title"));
        logger.info("Company instance was created");
        return company;
    }

    /**
     * Fills a cruise translation instance
     *
     * @param c      - DB connection
     * @param cruise - cruise to be translated
     * @param sql    - sql query
     * @param count  - DAO method counter of executes
     * @return - executes number
     * @throws SQLException - if there is a problem with SQL query
     */
    public static int langCruiseSetter(Connection c, Cruise cruise, String sql, int count) throws SQLException {

        PreparedStatement pr = c.prepareStatement(sql);
        for (int i = 0; i < cruise.getCruiseTranslationList().size(); i++) {
            pr.setLong(3, cruise.getCruiseTranslationList().get(i).getLanguage().getId());
            pr.setLong(2, cruise.getId());
            pr.setString(1, cruise.getCruiseTranslationList().get(i).getTitle());
            count += pr.executeUpdate();
        }
        logger.info("Cruise translations were read");
        return count;
    }

    /**
     * Fills a route translation instance
     *
     * @param c     - DB connection
     * @param route - route to be translated
     * @param sql   - sql query
     * @param count - DAO method counter of executes
     * @return - executes number
     * @throws SQLException - if there is a problem with SQL query
     */
    public static int langRouteSetter(Connection c, Route route, String sql, int count) throws SQLException {
        PreparedStatement pr = c.prepareStatement(sql);
        for (int i = 0; i < route.getRouteTranslationList().size(); i++) {
            pr.setString(1, route.getRouteTranslationList().get(i).getStart());
            pr.setString(2, route.getRouteTranslationList().get(i).getEnd());
            pr.setLong(3, route.getRouteTranslationList().get(i).getLanguage().getId());
            pr.setLong(4, route.getId());
            count += pr.executeUpdate();
        }
        logger.info("Route translations were read");
        return count;
    }
}
