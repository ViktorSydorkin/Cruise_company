package DAO.Implementation;

import DAO.Interfaces.UserDAO;
import DAO.Util.ConnectionPool;
import Model.Entity.User;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UserImpl implements UserDAO {

    private final String ADD_USER = "INSERT INTO user (name, surname, email, password, fk_language_id) VALUES (?,?,?,?,?);";
    private final String GET_ALL_USERS = "SELECT * FROM user INNER JOIN role ON (role_id =fk_role_id) INNER JOIN language ON (fk_language_id = language_id) WHERE fk_role_id = 1 ORDER BY user_id;";
    private final String GET_USER_BY_EMAIL = "SELECT * FROM user INNER JOIN role ON (role_id =fk_role_id) INNER JOIN language ON (fk_language_id = language_id) WHERE email = ?;";
    private final String GET_USER_BY_EMAIL_AND_PASSWORD = "SELECT * FROM user INNER JOIN role on (role_id =fk_role_id) INNER JOIN language ON (fk_language_id = language_id) WHERE email = ? AND password = ?;";
    private final String UPDATE_USER = "UPDATE user SET name = ?, surname = ?, password = ?, fk_language_id = ? WHERE email = ?;";
    private final String DELETE_USER = "DELETE FROM user WHERE user_id = ?;";

    private ConnectionPool cp;
    private Connection c;
    private PreparedStatement pr;
    private ResultSet rs;
    private Logger logger;


    public UserImpl(ConnectionPool connectionPool) {
        this.cp = connectionPool;
        logger = Logger.getLogger(UserImpl.class);
    }


    /**
     * Adds user to DB
     *
     * @param user - user that should be added
     * @return - true - if user was added
     * @see User
     */
    @Override
    public boolean add(User user) {
        c = cp.getConnection();
        try {
            pr = c.prepareStatement(ADD_USER);
            pr.setString(1, user.getName());
            pr.setString(2, user.getSurname());
            pr.setString(3, user.getEmail());
            pr.setString(4, user.getPassword());
            pr.setLong(5, user.getLanguage().getId());
            logger.info("User is going to be added");
            return pr.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("DAO caught an SQL exception while adding the user", e);
            return false;
        } finally {
            cp.close(pr);
            cp.close(c);
        }
    }

    /**
     * Gets the list of users from the DB
     *
     * @param lang_id - not used in current implementation
     * @return - list of users
     */
    @Override
    public List<User> getAll(long... lang_id) {
        c = cp.getConnection();
        List<User> userList = new ArrayList<>();
        try {
            pr = c.prepareStatement(GET_ALL_USERS);
            rs = pr.executeQuery();
            while (rs.next()) {
                userList.add(Executor.userCreation(rs));
            }
            logger.info("list of users was selected");
            return userList;
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("DAO caught an SQL exception while selecting the users list", e);
            return null;
        } finally {
            cp.close(rs);
            cp.close(pr);
            cp.close(c);
        }
    }

    /**
     * Gets user from DB by its email
     *
     * @param email - user's email
     * @return - user if it exists in DB
     * @see User
     */
    @Override
    public User getUserByEmail(String email) {
        c = cp.getConnection();
        try {
            pr = c.prepareStatement(GET_USER_BY_EMAIL);
            pr.setString(1, email);
            rs = pr.executeQuery();
            if (rs.next()) return Executor.userCreation(rs);
            else return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            cp.close(rs);
            cp.close(pr);
            cp.close(c);
        }
    }


    /**
     * Gets user from DB by its email and password
     *
     * @param email    - user's email
     * @param password - user's password
     * @return - user if it exists in DB
     * @see User
     */
    @Override
    public User getUserByEmailAndPassword(String email, String password) {
        c = cp.getConnection();
        try {
            pr = c.prepareStatement(GET_USER_BY_EMAIL_AND_PASSWORD);
            pr.setString(1, email);
            pr.setString(2, password);
            rs = pr.executeQuery();
            if (rs.next()) {
                logger.info("User was gotten by email and password");
                return Executor.userCreation(rs);
            } else {
                logger.warn("User wasn't selected by email and password");
                return null;
            }
        } catch (SQLException e) {
            logger.error("DAO caught an SQL exception while selecting the user by email and password", e);
            e.printStackTrace();
            return null;
        } finally {
            cp.close(rs);
            cp.close(pr);
            cp.close(c);
        }
    }


    /**
     * Updates user in DB
     *
     * @param user - the new date to be inserted
     * @return - true if user was updated
     * @see User
     */
    @Override
    public boolean update(User user) {
        c = cp.getConnection();
        try {
            pr = c.prepareStatement(UPDATE_USER);
            pr.setString(1, user.getName());
            pr.setString(2, user.getSurname());
            pr.setString(3, user.getPassword());
            pr.setLong(4, user.getLanguage().getId());
            pr.setString(5, user.getEmail());
            logger.info("User is going to be updated");
            return pr.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("DAO caught an SQL exception while updating the user", e);
            return false;
        } finally {
            cp.close(pr);
            cp.close(c);
        }
    }

    /**
     * Removes user from DB
     *
     * @param id - id of user to be removed
     * @return - true if user was removed
     */
    @Override
    public boolean remove(long id) {
        return Executor.delete(DELETE_USER, id);
    }
}
