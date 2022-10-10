package Model.Implementation;

import DAO.DAOFactory;
import DAO.Interfaces.LinerDAO;
import Model.Entity.Liner;
import Model.Service.LinerService;
import org.apache.log4j.Logger;

import java.util.List;

public class LinerServiceImpl implements LinerService {
    private final LinerDAO linerDAO;
    Logger logger;

    /**
     * Creates an instance of liner's implementation
     */
    public LinerServiceImpl() {
        this.linerDAO = DAOFactory.getInstance().newLinerImplInstance();
        logger = Logger.getLogger(LinerServiceImpl.class);
    }

    /**
     * Adds liner to the DB
     *
     * @param liner - liner to be added
     * @return - true if liner was added
     * @see Liner
     */
    @Override
    public boolean addLiner(Liner liner) {
        logger.info("Liner is going to be added");
        return linerDAO.add(liner);
    }

    /**
     * Gets liner by its id from the DB
     *
     * @param liner_id - id of liner to be selected
     * @return - liner if it exists
     */
    @Override
    public Liner getLinerById(long liner_id) {
        logger.info("Liner is going to be selected by id");
        return linerDAO.getLinerById(liner_id);
    }

    /**
     * Gets all liners from the DB
     *
     * @param lang_id - id of language the liners should be translated to
     * @return - list of liners
     */
    @Override
    public List<Liner> getAllLiners(long lang_id) {
        logger.info("List of liners is going to be selected");
        return linerDAO.getAll(lang_id);
    }

    /**
     * Updates liner in DB if it exists
     *
     * @param liner - liner to be updated
     * @return - true if liner was updated
     */
    @Override
    public synchronized boolean updateLiner(Liner liner) {
        Liner old = linerDAO.getLinerById(liner.getId());
        if (old == null) {
            logger.warn("The liner wasn't updated: this liner doesn't exist / DAO caught an exception");
            return false;
        }
        liner.setLiner_photo(old.getLiner_photo());
        logger.info("The old liner photo was used, liner is going to be updated");
        return linerDAO.update(liner);
    }

    /**
     * Removes liner from the DB
     *
     * @param liner_id - id of liner to be removed
     * @return - true if liner was removed
     */
    @Override
    public synchronized boolean deleteLiner(long liner_id) {
        logger.info("Liner is going to be removed");
        return linerDAO.remove(liner_id);
    }
}
