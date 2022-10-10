package DAO.Interfaces;

import Model.Entity.User;


public interface UserDAO extends DAOImpl<User> {
    /**
     * Gets user from DB by its email
     *
     * @param email - user's email
     * @return - user if it exists in DB
     * @see User
     */
    User getUserByEmail(String email);

    /**
     * Gets user from DB by its email and password
     *
     * @param email    - user's email
     * @param password - user's password
     * @return - user if it exists in DB
     * @see User
     */
    User getUserByEmailAndPassword(String email, String password);
}
