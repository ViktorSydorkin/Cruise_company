package  Model.Implementation;

import DAO.DAOFactory;
import DAO.Interfaces.UserDAO;
import Model.Entity.User;
import Model.Service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;
    Logger logger;

    /**
     * Creates an instance of user's implementation
     */
    public UserServiceImpl() {
        this.userDAO = DAOFactory.getInstance().newUserImplInstance();
        logger = Logger.getLogger(UserServiceImpl.class);
    }

    /**
     * Gets user by email from the DB
     *
     * @param email - email by which the user is going to be selected
     * @return - user with this email
     */
    @Override
    public User getUserByEmail(String email) {
        logger.info("User is going to be selected by email");
        return userDAO.getUserByEmail(email);
    }

    /**
     * Gets user by its email and password from the DB
     *
     * @param email    - email by which the user is going to be selected
     * @param password - password by which the user is going to be selected
     * @return - user with this email and password
     */
    @Override
    public User getUserByEmailAndPassword(String email, String password) {
        logger.info("User is going to be selected by email and password");
        return userDAO.getUserByEmailAndPassword(email, passEncryption(email, password));
    }

    /**
     * Gets all users from the DB
     *
     * @return - list of users
     */
    @Override
    public List<User> getAllUsers() {
        logger.info("List of users is going to be selected");
        return userDAO.getAll();
    }

    /**
     * Adds user to the DB if it doesn't already exist
     *
     * @param user - user to be added
     * @return - true if user was added
     */
    @Override
    public boolean addUser(User user) {
        if (getUserByEmail(user.getEmail()) != null) {
            logger.warn("User wasn't added: this user already exists / DAO caught an exception");
            return false;
        }
        user.setPassword(passEncryption(user.getEmail(), user.getPassword()));
        logger.info("Password was encrypted, user was added");
         return userDAO.add(user);
    }

    /**
     * Updates user in the DB if it exists. Encrypts user's password.
     *
     * @param user - user to be updated
     * @return - true if user was updated
     */
    @Override
    public synchronized boolean updateUser(User user) {
        if (getUserByEmail(user.getEmail()) == null) {
            logger.warn("User wasn't updated: this user already doesn't exist / DAO caught an exception");
            return false;
        }
        user.setPassword(passEncryption(user.getEmail(), user.getPassword()));
        logger.info("User is going to be updated");
        return userDAO.update(user);
    }


    /**
     * Encrypts user's password. Uses the SHA-1 method
     *
     * @param email    - user's email that will be used as a salt
     * @param password - user's password
     * @return - encrypted password
     */
    @Override
    public String passEncryption(String email, String password) {
        return DigestUtils.sha1Hex(DigestUtils.sha1Hex(email) + password);
    }

    /**
     * Deletes a user from the DB
     *
     * @param id - id of user to be deleted
     * @return - true if user was deleted
     */
    @Override
    public synchronized boolean deleteUser(long id) {
        logger.info("User is going to be removed");
        return userDAO.remove(id);
    }
}
