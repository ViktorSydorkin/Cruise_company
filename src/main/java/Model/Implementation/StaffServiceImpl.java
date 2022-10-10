package Model.Implementation;

import DAO.DAOFactory;
import DAO.Interfaces.StaffDAO;
import Model.Entity.Staff;
import Model.Service.StaffService;
import org.apache.log4j.Logger;

import java.util.List;

public class StaffServiceImpl implements StaffService {
    private final StaffDAO staffDAO;
    Logger logger;

    /**
     * Creates an instance of staff implementation
     */
    public StaffServiceImpl() {
        this.staffDAO = DAOFactory.getInstance().newStaffImplInstance();
        logger = Logger.getLogger(StaffServiceImpl.class);
    }

    /**
     * Gets all staff from the DB
     *
     * @return - list of staff
     */
    @Override
    public List<Staff> getAllStaff() {
        logger.info("The staff list is going to be selected");
        return staffDAO.getAll();
    }

    /**
     * Adds staff to the DB
     *
     * @param staff - staff to be added
     * @see Staff
     */
    @Override
    public boolean addStaff(Staff staff) {
        logger.info("The staff is going to be selected");
        return staffDAO.add(staff);
    }

    /**
     * Updates staff in the DB
     *
     * @param staff - staff to be updated
     * @see Staff
     */
    @Override
    public synchronized boolean updateStaff(Staff staff) {
        if (staffDAO.getStaffById(staff.getId()) == null) {
            logger.warn("Staff wasn't selected by id");
            return false;
        }
        return staffDAO.update(staff);
    }

    /**
     * Removes staff from the DB
     *
     * @param id - id of the staff to be removed
     */
    @Override
    public synchronized boolean deleteStaff(long id) {
        logger.warn("Staff is going to be removed");
        return staffDAO.remove(id);
    }
}
