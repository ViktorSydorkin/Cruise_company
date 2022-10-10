package Model.Implementation;

import DAO.DAOFactory;
import DAO.Interfaces.CruiseDAO;
import Model.Entity.Cruise;
import Model.Entity.CruiseTranslation;
import Model.Service.CruiseService;
import org.apache.log4j.Logger;

import java.sql.Timestamp;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class CruiseServiceImpl implements CruiseService {
    private final CruiseDAO cruiseDAO;
    Logger logger;

    /**
     * Creates an instance of cruise's implementation
     */
    public CruiseServiceImpl() {
        this.cruiseDAO = DAOFactory.getInstance().newCruiseImplInstance();
        logger = Logger.getLogger(CruiseServiceImpl.class);
    }

    /**
     * Gets a limited amount of liners from the DB
     *
     * @param lang_id - id of language the liners should be translated to
     * @param start   - offset of the limited selection
     * @param amount  - selection amount
     * @return - list of cruise
     */
    @Override
    public List<Cruise> getAllCruise(long lang_id, int start, int amount) {
        logger.info("List of user was selected");
        return cruiseDAO.getAll(lang_id, start, amount);
    }

    /**
     * Gets amount of all cruises in DB with the specific duration
     *
     * @param lang_id  - doesn't matter in current implementation
     * @param duration - duration of the cruise
     * @return - amount of cruises with the specific duration
     */
    @Override
    public int getCruiseAmountDuration(long lang_id, int duration) {
        List<Cruise> cruiseList = cruiseDAO.getAll(lang_id, 0, Integer.MAX_VALUE);
        logger.info("All cruises were selected");
        cruiseList = listFilterByDuration(duration, cruiseList);
        logger.info("The list of selected cruises was filtered by duration");
        return cruiseList.size();

    }

    /**
     * Filters the list of limited amount of cruises by the cruise duration
     *
     * @param duration   - cruise duration
     * @param cruiseList - list of cruises with the specific duration
     * @return - filtered list of cruises
     */
    @Override
    public List<Cruise> listFilterByDuration(int duration, List<Cruise> cruiseList) {
        return cruiseList.stream().filter(s -> ChronoUnit.DAYS.between(s.getStart().toLocalDateTime(), s.getEnd().toLocalDateTime()) == duration).toList();
    }

    /**
     * Gets the amount of cruises where the start date is after the given date
     *
     * @param date - start date of cruise
     * @return - amount of cruises
     */
    @Override
    public int getCruiseAmountDate(Timestamp date) {
        return cruiseDAO.getCruiseAmountDate(date);
    }

    /**
     * Gets a limited amount of cruises from the DB and filers it by duration
     *
     * @param lang_id  - id of language the liners should be translated to
     * @param start    - offset of the limited selection
     * @param amount   - selection amount
     * @param duration - duration of cruise
     * @return - filtered list of cruise
     */
    @Override
    public List<Cruise> getCruiseByDuration(long lang_id, int start, int amount, int duration) {
        List<Cruise> cruiseList = cruiseDAO.getAll(lang_id, 0, Integer.MAX_VALUE);
        logger.info("List of cruises was selected");
        cruiseList = listFilterByDuration(duration, cruiseList);
        logger.info("List was filtered by duration");
        if (cruiseList.size() > 4)
            cruiseList = cruiseList.subList(start, start + amount);
        return cruiseList;
    }

    /**
     * Gets a limited amount of cruises from DB filtered by start date
     *
     * @param lang_id   - id of language the liners should be translated to
     * @param start     - offset of the limited selection
     * @param amount    - selection amount
     * @param startDate - start date of cruise
     * @return - filtered list of cruise
     */
    @Override
    public List<Cruise> getCruiseByStartDate(long lang_id, int start, int amount, Timestamp startDate) {
        logger.info("List of cruises filtered by start dae was selected");
        return cruiseDAO.getCruiseByStartDate(lang_id, start, amount, startDate);
    }

    /**
     * Decreases the availability of the cruise after user applies for it
     *
     * @param lang_id   - language of cruise
     * @param cruise_id - cruise to be updated
     * @return true if availability was changed
     */
    @Override
    public synchronized boolean changeAvailability(long lang_id, long cruise_id) {
        Cruise cruise = cruiseDAO.getCruiseById(lang_id, cruise_id);
        if (cruise == null) {
            logger.warn("The availability of the cruise wasn't changes because the cruise doesn't exist");
            return false;
        } else {
            cruise.setAvailable(cruise.getAvailable() - 1);
            logger.info("The availability of the cruise was decreased, the cruise is going to be updated");
            return cruiseDAO.update(cruise);
        }
    }

    /**
     * Adds cruise to the DB
     *
     * @param cruise - cruise to be added
     * @return - true if cruise was added
     */
    @Override
    public boolean addCruise(Cruise cruise) {
        logger.info("The new cruise is going to be added");
        return cruiseDAO.add(cruise);
    }

    /**
     * Gets the cruise from the DB by its id
     *
     * @param lang_id   - id of cruise's language
     * @param cruise_id - id of cruise to be updated
     * @return - cruise if it exists
     */
    @Override
    public Cruise getCruiseById(long lang_id, long cruise_id) {
        logger.info("Cruise is going to be selected by id");
        return cruiseDAO.getCruiseById(lang_id, cruise_id);
    }

    /**
     * Updates cruise in DB if it exists
     *
     * @param lang_id - id of cruise's language
     * @param cruise  - cruise to be updated
     * @return - true if cruise was updated
     */
    @Override
    public synchronized boolean updateCruise(long lang_id, Cruise cruise) {
        if (cruiseDAO.getCruiseById(lang_id, cruise.getId()) == null) {
            logger.warn("Cruise wasn't updated because it doesn't exist");
            return false;
        }
        logger.info("Cruise is going to be updated");
        return cruiseDAO.update(cruise);
    }

    /**
     * Gets the amount of all cruises in DB
     *
     * @return - a number of cruise amount
     */
    @Override
    public int getCruiseAmount() {
        logger.info("The cruise amount is going to be selected");
        return cruiseDAO.getCruiseAmount();
    }

    /**
     * Gets the list of translations of the specific cruise
     *
     * @param cruise_id - id of cruise to be translated
     * @return - list of cruise's translations
     */
    @Override
    public List<CruiseTranslation> getAllCruiseTranslations(long cruise_id) {
        logger.info("All translation of he cruise are going to be selected");
        return cruiseDAO.getCruiseTranslation(cruise_id);
    }

    /**
     * Removes cruise from the DB by its id
     *
     * @param cruise_id - id of cruise to be removed
     * @return - true if cruise was removed
     */
    @Override
    public synchronized boolean deleteCruise(long cruise_id) {
        logger.info("Cruise is going to be deleted");
        return cruiseDAO.remove(cruise_id);
    }


}
