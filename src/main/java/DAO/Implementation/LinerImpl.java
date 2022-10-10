package DAO.Implementation;

import DAO.Interfaces.LinerDAO;
import DAO.Util.ConnectionPool;
import Model.Entity.Liner;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LinerImpl implements LinerDAO {

    private final String ADD_LINER = "INSERT INTO liner (capacity, liner_name, deck_amount, liner_photo, fk_company_id) VALUES (?,?,?,?,?);";
    private final String GET_ALL_LINERS = "SELECT * FROM liner INNER JOIN company on fk_company_id = company.company_id ORDER BY liner_id;";
    private final String GET_LINER_BY_ID = "SELECT * FROM liner INNER JOIN company on fk_company_id = company.company_id WHERE liner_id = ?;";
    private final String UPDATE_LINER = "UPDATE liner SET capacity =?, liner_name = ?, deck_amount = ?, liner_photo = ?, fk_company_id =? WHERE liner_id = ?;";
    private final String DELETE_LINER = "DELETE FROM liner WHERE liner_id =?;";

    private ConnectionPool cp;
    private Connection c;
    private PreparedStatement pr;
    private ResultSet rs;
    private Logger logger;

    public LinerImpl(ConnectionPool connectionPool) {
        this.cp = connectionPool;
        logger = Logger.getLogger(LinerImpl.class);
    }

    /**
     * Adds a new liner to DB
     *
     * @param liner - liner to be added
     * @return - true if liner was added
     * @see Liner
     */
    @Override
    public boolean add(Liner liner) {
        c = cp.getConnection();
        try {
            pr = c.prepareStatement(ADD_LINER);
            pr.setInt(1, liner.getCapacity());
            pr.setString(2, liner.getName());
            pr.setInt(3, liner.getDeck_amount());
            pr.setBlob(4, liner.getLiner_photo());
            pr.setLong(5, liner.getCompany().getId());
            logger.info("Liner is going to be added");
            return pr.executeUpdate() == 1;
        } catch (SQLException e) {
            logger.error("DAO caught an SQL exception while adding a liner", e);
            e.printStackTrace();
            return false;
        } finally {
            cp.close(pr);
            cp.close(c);
        }
    }

    /**
     * Gets all liners from DB
     *
     * @param lang_id - not used in current implementation
     * @return - list of liners
     */
    @Override
    public List<Liner> getAll(long... lang_id) {
        c = cp.getConnection();
        List<Liner> linerList = new ArrayList<>();
        try {
            pr = c.prepareStatement(GET_ALL_LINERS);
            rs = pr.executeQuery();
            while (rs.next()) {
                linerList.add(Executor.linerCreation(rs));
            }
            logger.info("List of liners was selected");
            return linerList;
        } catch (SQLException e) {
            logger.error("DAO caught an SQL exception while selecting the liners list", e);
            e.printStackTrace();
            return null;
        } finally {
            cp.close(rs);
            cp.close(pr);
            cp.close(c);
        }
    }

    /**
     * Gets liner from DB by its id
     *
     * @param liner_id - id of liner to be selected
     * @return - liner if it exists
     * @see Liner
     */
    @Override
    public Liner getLinerById(long liner_id) {
        c = cp.getConnection();
        try {
            pr = c.prepareStatement(GET_LINER_BY_ID);
            pr.setLong(1, liner_id);
            rs = pr.executeQuery();
            if (rs.next()) {
                logger.info("Liner was gotten by id");
                return Executor.linerCreation(rs);
            } else {
                logger.warn("Liner wasn't gotten by id");
                return null;
            }
        } catch (SQLException e) {
            logger.error("DAO caught an SQL exception while getting liner by id", e);
            e.printStackTrace();
            return null;
        } finally {
            cp.close(rs);
            cp.close(pr);
            cp.close(c);
        }
    }

    /**
     * Updates liner ind DB
     *
     * @param liner - liner to be updated
     * @return - true if liner was updated
     * @see Liner
     */
    @Override
    public boolean update(Liner liner) {
        c = cp.getConnection();
        try {
            pr = c.prepareStatement(UPDATE_LINER);
            pr.setInt(1, liner.getCapacity());
            pr.setString(2, liner.getName());
            pr.setInt(3, liner.getDeck_amount());
            pr.setBlob(4, liner.getLiner_photo());
            pr.setLong(5, liner.getCompany().getId());
            pr.setLong(6, liner.getId());
            logger.info("Liner is going to be updated");
            return pr.executeUpdate() == 1;
        } catch (SQLException e) {
            logger.error("DAO caught an SQL exception while updating the liner", e);
            e.printStackTrace();
            return false;
        } finally {
            cp.close(pr);
            cp.close(c);
        }
    }

    /**
     * Removes liner from DB
     *
     * @param id - id of liner to be deleted
     * @return - true if liner was removed
     */
    @Override
    public boolean remove(long id) {
        return Executor.delete(DELETE_LINER, id);
    }
}
