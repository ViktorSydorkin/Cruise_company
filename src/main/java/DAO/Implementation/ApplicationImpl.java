package DAO.Implementation;

import DAO.Interfaces.ApplicationDAO;
import DAO.Util.ConnectionPool;
import Model.Entity.Application;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ApplicationImpl implements ApplicationDAO {

    private final String ADD_APPLICATION = "INSERT INTO application (fk_user_id, fk_cruise_id, pass_photo) VALUES (?, ?, ?);";
    private final String GET_ALL = """
            SELECT *
            FROM application
                   INNER JOIN cruise on cruise_id = fk_cruise_id
                   INNER JOIN user on fk_user_id = user_id
                   INNER JOIN role on fk_role_id = role_id
                   INNER JOIN language on fk_language_id = language_id
                   INNER JOIN route on fk_route_id = route_id
                   INNER JOIN language_route on fk_lr_route_id = route_id AND ? = fk_lr_language_id
                   INNER JOIN language_cruise on fk_lc_cruise_id = cruise_id AND ? = fk_lc_language_id
                   INNER JOIN liner on fk_liner_id = liner_id
                   INNER JOIN company on fk_company_id = company_id;""";
    private final String GET_ALL_FOR_USER = """
            SELECT *
            FROM application
                   INNER JOIN cruise on cruise_id = fk_cruise_id
                   INNER JOIN user on fk_user_id = user_id
                   INNER JOIN role on fk_role_id = role_id
                   INNER JOIN language on fk_language_id = language_id
                   INNER JOIN route on fk_route_id = route_id
                   INNER JOIN language_route on fk_lr_route_id = route_id AND ? = fk_lr_language_id
                   INNER JOIN language_cruise on fk_lc_cruise_id = cruise_id AND ? = fk_lc_language_id
                   INNER JOIN liner on fk_liner_id = liner_id
                   INNER JOIN company on fk_company_id = company_id
                   WHERE fk_user_id = ?;""";
    private final String GET_APPLICATION_BY_ID = """
            SELECT *
            FROM application
                   INNER JOIN cruise on cruise_id = fk_cruise_id
                   INNER JOIN user on fk_user_id = user_id
                   INNER JOIN role on fk_role_id = role_id
                   INNER JOIN language on fk_language_id = language_id
                   INNER JOIN route on fk_route_id = route_id
                   INNER JOIN language_route on fk_lr_route_id = route_id AND ? = fk_lr_language_id
                   INNER JOIN language_cruise on fk_lc_cruise_id = cruise_id AND ? = fk_lc_language_id
                   INNER JOIN liner on fk_liner_id = liner_id
                   INNER JOIN company on fk_company_id = company_id
                   WHERE application_id =?;""";
    private final String UPDATE_APPLICATION = "UPDATE application SET pass_photo =?, approved = ?, paid = ?, closed = ?, ended = ?, fk_user_id =?, fk_cruise_id=? WHERE application_id = ?;";
    private final String DELETE_APPLICATION = "DELETE FROM application WHERE application_id = ?;";

    private ConnectionPool cp;
    private Connection c;
    private PreparedStatement pr;
    private ResultSet rs;
    private Logger logger;

    public ApplicationImpl(ConnectionPool cp) {
        this.cp = cp;
        logger = Logger.getLogger(ApplicationImpl.class);
    }

    /**
     * Adds application to DB
     *
     * @param application - application to be added
     * @return - true if application was added
     * @see Application
     */
    @Override
    public boolean add(Application application) {
        c = cp.getConnection();
        try {
            pr = c.prepareStatement(ADD_APPLICATION);
            pr.setLong(1, application.getUser().getId());
            pr.setLong(2, application.getCruise().getId());
            pr.setBlob(3, application.getPass_photo());
            logger.info("Application is going to be added");
            return pr.executeUpdate() == 1;
        } catch (SQLException e) {
            logger.error("DAO caught an SQL exception while adding an application", e);
            e.printStackTrace();
            return false;
        } finally {
            cp.close(pr);
            cp.close(c);
        }
    }

    /**
     * Gets all applications from the DB
     *
     * @param lang_id - id of language
     * @return - list of applications
     */
    @Override
    public List<Application> getAll(long... lang_id) {
        c = cp.getConnection();
        List<Application> applicationList = new ArrayList<>();
        try {
            pr = c.prepareStatement(GET_ALL);
            pr.setLong(1, lang_id[0]);
            pr.setLong(2, lang_id[0]);
            rs = pr.executeQuery();
            while (rs.next()) {
                applicationList.add(Executor.applicationCreation(rs));
            }
            logger.info("List of applications was selected");
            return applicationList;
        } catch (SQLException e) {
            logger.error("DAO caught an exception while selecting the applications list", e);
            e.printStackTrace();
            return null;
        } finally {
            cp.close(rs);
            cp.close(pr);
            cp.close(c);
        }
    }

    @Override
    public List<Application> getAllAppsForUser(long lang_id, long user_id) {
        c = cp.getConnection();
        List<Application> applicationList = new ArrayList<>();
        try {
            pr = c.prepareStatement(GET_ALL_FOR_USER);
            pr.setLong(1, lang_id);
            pr.setLong(2, lang_id);
            pr.setLong(3, user_id);
            rs = pr.executeQuery();
            while (rs.next()) {
                applicationList.add(Executor.applicationCreation(rs));
            }
            logger.info("List of applications for a certain user was selected");
            return applicationList;
        } catch (SQLException e) {
            logger.error("DAO caught an exception while selecting the applications list for a certain user", e);
            e.printStackTrace();
            return null;
        } finally {
            cp.close(rs);
            cp.close(pr);
            cp.close(c);
        }
    }

    /**
     * Gets application by its id
     *
     * @param lang_id - id of language
     * @param app_id  - id of application to be selected
     * @return - application if it exists
     * @see Application
     */
    @Override
    public Application getAppById(long lang_id, long app_id) {
        c = cp.getConnection();
        try {
            pr = c.prepareStatement(GET_APPLICATION_BY_ID);
            pr.setLong(1, lang_id);
            pr.setLong(2, lang_id);
            pr.setLong(3, app_id);
            rs = pr.executeQuery();
            if (rs.next()) {
                logger.info("Application was gotten by id");
                return Executor.applicationCreation(rs);
            } else {
                logger.warn("Application wasn't gotten by id");
                return null;
            }
        } catch (SQLException e) {
            logger.error("DAO caught an exception while getting application by id", e);
            e.printStackTrace();
            return null;
        } finally {
            cp.close(rs);
            cp.close(pr);
            cp.close(c);
        }
    }

    /**
     * Updates the application in DB
     *
     * @param application - application to be updated
     * @return - true if application was updated
     * @see Application
     */
    @Override
    public boolean update(Application application) {
        c = cp.getConnection();
        try {
            pr = c.prepareStatement(UPDATE_APPLICATION);
            pr.setBlob(1, application.getPass_photo());
            pr.setString(2, String.valueOf(application.getApproved()));
            pr.setString(3, String.valueOf(application.getPaid()));
            pr.setString(4, String.valueOf(application.getClosed()));
            pr.setString(5, String.valueOf(application.getEnded()));
            pr.setLong(6, application.getUser().getId());
            pr.setLong(7, application.getCruise().getId());
            pr.setLong(8, application.getId());
            logger.info("Application is going to be updated");
            return pr.executeUpdate() == 1;
        } catch (SQLException e) {
            logger.error("DAO caught an SQL exception while updating an application", e);
            e.printStackTrace();
            return false;
        } finally {
            cp.close(pr);
            cp.close(c);
        }
    }

    /**
     * Removes application from the DB
     *
     * @param id - id of application to be removed
     * @return - true if application was removed
     */
    @Override
    public boolean remove(long id) {
        return Executor.delete(DELETE_APPLICATION, id);
    }
}