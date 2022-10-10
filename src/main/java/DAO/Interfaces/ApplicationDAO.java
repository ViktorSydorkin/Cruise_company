package DAO.Interfaces;

import Model.Entity.Application;

import java.util.List;

public interface ApplicationDAO extends DAOImpl<Application> {
    /**
     * Gets application by its id
     *
     * @param lang_id - id of language
     * @param app_id  - id of application to be selected
     * @return - application if it exists
     * @see Application
     */
    Application getAppById(long lang_id, long app_id);

    /**
     * Gets list of application for certain user
     *
     * @param lang_id - id of language
     * @param user_id - id of user
     * @return - list of user's applications
     * @see Application
     */
    List<Application> getAllAppsForUser(long lang_id, long user_id);
}
