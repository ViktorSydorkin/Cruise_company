package Model.Implementation;

import DAO.DAOFactory;
import DAO.Interfaces.ApplicationDAO;
import Model.Entity.Application;
import Model.Entity.Cruise;
import Model.Entity.Enums.Approved;
import Model.Entity.Enums.Closed;
import Model.Entity.Enums.Ended;
import Model.Entity.Enums.Paid;
import Model.Service.ApplicationService;
import org.apache.log4j.Logger;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ApplicationServiceImpl implements ApplicationService {
    private final ApplicationDAO applicationDAO;
    Logger logger;


    /**
     * Creates an instance of application's implementation
     */
    public ApplicationServiceImpl() {
        this.applicationDAO = DAOFactory.getInstance().newApplicationImplInstance();
        this.logger = Logger.getLogger(ApplicationServiceImpl.class);

    }

    /**
     * Add application to the DB
     *
     * @param application - application to be added
     * @return - true if application was added
     */
    @Override
    public boolean addApplication(Application application) {
        logger.info("Application is going to be added");
        return applicationDAO.add(application);
    }

    /**
     * Gets all applications from the DB
     *
     * @param lang_id - id of language
     * @return - list of applications
     */
    @Override
    public List<Application> getAllApplications(long lang_id) {
        logger.info("List of applications is going to be selected");
        return applicationDAO.getAll(lang_id);
    }

    /**
     * Gets all applications for specific user
     *
     * @param lang_id - id of language
     * @param user_id - id of the user
     * @return - list of application
     */
    @Override
    public List<Application> getApplicationsForUser(long lang_id, long user_id) {
        logger.info("List of applications for a certain user is going to be selected");
        return applicationDAO.getAllAppsForUser(lang_id, user_id);

    }

    /**
     * Gets application by its id
     *
     * @param lang_id - id of language
     * @param app_id  - id if application to be selected
     * @return - application if it exists
     */
    @Override
    public Application getApplicationById(long lang_id, long app_id) {
        logger.info("Application is going to e selected by id");
        return applicationDAO.getAppById(lang_id, app_id);

    }

    /**
     * Changes application's status to 'Approved'
     *
     * @param lang_id - id of language
     * @param app_id  - id of application to be updated
     * @return - true if application was updated
     */
    @Override
    public synchronized boolean approveApplication(long lang_id, long app_id) {
        Application application = applicationDAO.getAppById(lang_id, app_id);
        if (application == null) {
            logger.warn("Application wasn't approved because this application doesn't exist");
            return false;
        } else {
            application.setApproved(Approved.YES);
            logger.info("Application was approved");
            return applicationDAO.update(application);
        }

    }

    /**
     * Changes application's status to 'Opened'.
     * If it was opened successfully, it starts the timer before
     * automated closing of the application
     *
     * @param lang_id - id of language
     * @param app_id  - id of application to be updated
     * @return - true if application was updated
     * @see Application
     */
    @Override
    public synchronized boolean openApplication(long lang_id, long app_id) {
        Application application = applicationDAO.getAppById(lang_id, app_id);
        if (application == null) {
            logger.warn("Application wasn't opened because this application doesn't exist");
            return false;
        } else {
            application.setClosed(Closed.OPENED);
            scheduledCloser(application.getCruise().getId(), application.getId());
            logger.info("Application was opened");
            return applicationDAO.update(application);
        }

    }

    /**
     * Changes application's status to 'Ended'
     *
     * @param lang_id - id of language
     * @param app_id  - id of application to be updated
     * @return - true if application was updated
     */
    @Override
    public synchronized boolean endApplication(long lang_id, long app_id) {
        Application application = applicationDAO.getAppById(lang_id, app_id);
        if (application == null) {
            logger.warn("Application wasn't ended because this application doesn't exist");
            return false;
        } else {
            application.setEnded(Ended.YES);
            logger.info("Application was ended");
            return applicationDAO.update(application);
        }

    }

    /**
     * Changes application's status to 'Paid'
     *
     * @param lang_id - id of language
     * @param app_id  - id of application to be updated
     * @return - true if application was updated
     */
    @Override
    public boolean payApplication(long lang_id, long app_id) {
        Application application = applicationDAO.getAppById(lang_id, app_id);
        if (application == null) {
            logger.warn("Application wasn't approved because this application doesn't exist");
            return false;
        } else {
            application.setPaid(Paid.PAID);
            logger.info("Application was paid");
            return applicationDAO.update(application);
        }

    }

    /**
     * Removes application from the DB
     *
     * @param app_id - id of application to be removed
     * @return - true if application was removed
     */
    @Override
    public synchronized boolean deleteApplication(long app_id) {
        logger.info("Application is going to be removed");
        return applicationDAO.remove(app_id);
    }

    /**
     * Ends the application when the cruise became ended
     *
     * @param cruise_id - id of cruise the was ended
     * @param app_id    - id of application to be ended
     */
    public void scheduledCloser(long cruise_id, long app_id) {
        CruiseServiceImpl cruiseServiceImpl = new CruiseServiceImpl();
        Cruise cruise = cruiseServiceImpl.getCruiseById(1, cruise_id);
        LocalDateTime end = cruise.getEnd().toLocalDateTime();
        LocalDateTime now = LocalDateTime.now().withSecond(0).withNano(0);
        long minutes = ChronoUnit.MINUTES.between(now, end);

        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.schedule(new CloserTask(app_id), minutes, TimeUnit.MINUTES);
    }

    /*
     * Starts a thread the counts the time before ending the application
     */
    private static class CloserTask implements Runnable {
        long id;

        public CloserTask(long id) {
            this.id = id;
        }

        @Override
        public void run() {
            ApplicationServiceImpl applicationServiceImpl = new ApplicationServiceImpl();
            applicationServiceImpl.endApplication(1, id);
        }
    }
}
