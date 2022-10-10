package DAO.Implementation;

import DAO.Interfaces.StaffDAO;
import DAO.Util.ConnectionPool;
import Model.Entity.Staff;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StaffImpl implements StaffDAO {

    private final String ADD_STAFF = "INSERT INTO staff (staff_name, staff_surname, post, fk_s_liner_id) VALUES (?,?,?,?)";
    private final String GET_ALL_STAFF = "SELECT * FROM staff  LEFT JOIN liner on fk_s_liner_id = liner_id LEFT JOIN company on fk_company_id = company_id ORDER BY fk_s_liner_id ";
    private final String GET_STAFF_BY_ID = "SELECT * FROM staff  LEFT JOIN liner on fk_s_liner_id = liner_id LEFT JOIN company on fk_company_id = company_id WHERE staff_id = ?";
    private final String UPDATE_STAFF = "UPDATE staff SET staff_name = ?, staff_surname = ?, post = ?, fk_s_liner_id = ? WHERE staff_id = ?";
    private final String DELETE_STAFF = "DELETE FROM staff WHERE staff_id = ?;";

    private ConnectionPool cp;
    private Connection c;
    private PreparedStatement pr;
    private ResultSet rs;
    private Logger logger;

    public StaffImpl(ConnectionPool connectionPool) {
        this.cp = connectionPool;
        logger = Logger.getLogger(StaffImpl.class);
    }

    /**
     * Adds staff to DB
     *
     * @param staff - new staff to be added
     * @return - true if new staff was added
     * @see Staff
     */
    @Override
    public boolean add(Staff staff) {
        c = cp.getConnection();
        try {
            pr = c.prepareStatement(ADD_STAFF);
            pr.setString(1, staff.getName());
            pr.setString(2, staff.getSurname());
            pr.setString(3, staff.getPost());
            pr.setLong(4, staff.getLiner().getId());
            logger.info("Staff is going to be added");
            return pr.executeUpdate() == 1;
        } catch (SQLException e) {
            logger.error("DAO caught an SQL exception while adding a staff", e);
            e.printStackTrace();
            return false;
        } finally {
            cp.close(pr);
            cp.close(c);
        }
    }

    /**
     * Gets the list of staff from DB
     *
     * @param lang_id - not used in current implementation
     * @return - list of staff
     */
    @Override
    public List<Staff> getAll(long... lang_id) {
        c = cp.getConnection();
        List<Staff> staffList = new ArrayList<>();
        try {
            pr = c.prepareStatement(GET_ALL_STAFF);
            rs = pr.executeQuery();
            while (rs.next()) {
                staffList.add(Executor.staffCreation(rs));
            }
            logger.info("List of staff was selected");
            return staffList;
        } catch (SQLException e) {
            logger.error("DAO caught an SQL exception while selecting the staff list", e);
            e.printStackTrace();
            return null;
        } finally {
            cp.close(rs);
            cp.close(pr);
            cp.close(c);
        }
    }

    /**
     * Gets staff from the DB by its id
     *
     * @param id - id of the staff to be selected
     * @return - staff if it exists
     * @see Staff
     */
    @Override
    public Staff getStaffById(long id) {
        c = cp.getConnection();
        try {
            pr = c.prepareStatement(GET_STAFF_BY_ID);
            pr.setLong(1, id);
            rs = pr.executeQuery();
            if(rs.next()){
                logger.info("Staff was selected by id");
                return Executor.staffCreation(rs);
            }else {
                logger.warn("Staff wasn't selected by id");
                return null;
            }
        } catch (SQLException e) {
            logger.error("DAO caught an SQL exception while selecting the staff by id", e);
            e.printStackTrace();
            return null;
        } finally {
            cp.close(rs);
            cp.close(pr);
            cp.close(c);
        }
    }

    /**
     * Updates staff in DB
     *
     * @param staff - staff to be updated
     * @return - true if staff was updated
     * @see Staff
     */
    @Override
    public boolean update(Staff staff) {
        c = cp.getConnection();
        try {
            pr = c.prepareStatement(UPDATE_STAFF);
            pr.setString(1, staff.getName());
            pr.setString(2, staff.getSurname());
            pr.setString(3, staff.getPost());
            pr.setLong(4, staff.getLiner().getId());
            pr.setLong(5, staff.getId());
            logger.info("Staff is going to be updated");
            return pr.executeUpdate() == 1;
        } catch (SQLException e) {
            logger.error("DAO caught an SQL exception while updating the staff", e);
            e.printStackTrace();
            return false;
        } finally {
            cp.close(pr);
            cp.close(c);
        }
    }


    /**
     * Removes staff from DB
     *
     * @param id - id of staff to be removed
     * @return - true if staff was removed
     */
    @Override
    public boolean remove(long id) {
        return Executor.delete(DELETE_STAFF, id);
    }
}
