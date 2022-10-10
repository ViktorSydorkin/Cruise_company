package DAO.Implementation;

import DAO.Interfaces.CruiseDAO;
import DAO.Util.ConnectionPool;
import Model.Entity.Cruise;
import Model.Entity.CruiseTranslation;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CruiseImpl implements CruiseDAO {
    private final String ADD_CRUISE = "INSERT INTO cruise (start_date, end_date, price, available, fk_route_id, fk_liner_id) VALUES (?,?,?,?,?,?);";
    private final String ADD_LANGUAGE_CRUISE = "INSERT INTO language_cruise (cruise_title, fk_lc_cruise_id, fk_lc_language_id) VALUES (?,?,?);";
    private final String GET_CRUISE_AMOUNT = "SELECT COUNT(*) FROM cruise;";
    private final String GET_CRUISE_AMOUNT_DATE = "SELECT COUNT(*) FROM cruise WHERE start_date > ?;";
    private final String GET_ALL_CRUISE = """
            SELECT *
            FROM cruise
            LEFT JOIN route on fk_route_id = route_id
            LEFT JOIN liner on fk_liner_id = liner_id
            LEFT JOIN company on fk_company_id = company_id
            LEFT JOIN language_cruise on fk_lc_language_id = ? AND fk_lc_cruise_id = cruise_id
            LEFT JOIN language_route on fk_lr_language_id = ? AND fk_lr_route_id = route_id
            LEFT JOIN language on fk_lc_language_id = language_id
            ORDER BY cruise_id""";
    private final String GET_CRUISE_BY_START_DATE = """
            SELECT *
            FROM cruise
            LEFT JOIN route on fk_route_id = route_id
            LEFT JOIN liner on fk_liner_id = liner_id
            LEFT JOIN company on fk_company_id = company_id
            LEFT JOIN language_cruise on fk_lc_language_id = ? AND fk_lc_cruise_id = cruise_id
            LEFT JOIN language_route on fk_lr_language_id = ? AND fk_lr_route_id = route_id
            LEFT JOIN language on fk_lc_language_id = language_id
            WHERE start_date > ?
            ORDER BY cruise_id""";
    private final String GET_CRUISE_BY_ID = """
            SELECT *
            FROM cruise
            LEFT JOIN route on fk_route_id = route_id
            LEFT JOIN liner on fk_liner_id = liner_id
            LEFT JOIN company on fk_company_id = company_id
            LEFT JOIN language_cruise on fk_lc_language_id = ? AND fk_lc_cruise_id = cruise_id
            LEFT JOIN language_route on fk_lr_language_id = ? AND fk_lr_route_id = route_id
            LEFT JOIN language on fk_lc_language_id = language_id
            WHERE cruise_id = ?
            ORDER BY cruise_id""";
    private final String GET_ALL_CRUISE_TRANSLATIONS = "SELECT * FROM language_cruise INNER JOIN language on language_id=fk_lc_language_id WHERE fk_lc_cruise_id = ?;";
    private final String UPDATE_CRUISE = "UPDATE cruise SET start_date = ?, end_date = ?, price = ?, available=?, fk_route_id = ?, fk_liner_id = ? WHERE cruise_id = ?;";
    private final String UPDATE_LANGUAGE_CRUISE = "UPDATE language_cruise SET cruise_title = ? WHERE fk_lc_cruise_id = ? AND fk_lc_language_id = ?;";
    private final String DELETE_CRUISE = "DELETE FROM cruise WHERE cruise_id = ?;";

    private ConnectionPool cp;
    private Connection c;
    private PreparedStatement pr;
    private ResultSet rs;
    private Logger logger;
    private int count;

    public CruiseImpl(ConnectionPool connectionPool) {
        this.cp = connectionPool;
        logger = Logger.getLogger(CruiseImpl.class);
    }

    /**
     * Adds cruise to DB
     *
     * @param cruise - cruise to be added
     * @return - true if cruise was added
     * @see Cruise
     */
    @Override
    public boolean add(Cruise cruise) {
        c = cp.getConnection();
        try {
            count = 0;
            c.setAutoCommit(false);
            c.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            pr = c.prepareStatement(ADD_CRUISE, Statement.RETURN_GENERATED_KEYS);
            pr.setTimestamp(1, cruise.getStart());
            pr.setTimestamp(2, cruise.getEnd());
            pr.setInt(3, cruise.getPrice());
            pr.setInt(4, cruise.getAvailable());
            pr.setLong(5, cruise.getRoute().getId());
            pr.setLong(6, cruise.getLiner().getId());
            count += pr.executeUpdate();
            rs = pr.getGeneratedKeys();
            if (rs.next()) {
                cruise.setId(rs.getLong(1));
            }
            count = Executor.langCruiseSetter(c, cruise, ADD_LANGUAGE_CRUISE, count);
            if (count == cruise.getCruiseTranslationList().size() + 1) {
                c.commit();
                logger.info("Cruise was added, transaction is committed");
                return true;
            } else {
                cp.rollback(c);
                logger.warn("Cruise wasn't added, transaction is rolled back");
                return false;
            }
        } catch (SQLException e) {
            logger.error("Dao caught an SQL exception while adding a cruise", e);
            cp.rollback(c);
            e.printStackTrace();
            return false;
        } finally {
            cp.close(rs);
            cp.close(pr);
            cp.close(c);
        }
    }

    /**
     * Gets the amount of cruises in DB
     *
     * @return - amount of cruises
     */
    @Override
    public int getCruiseAmount() {
        c = cp.getConnection();
        try {
            pr = c.prepareStatement(GET_CRUISE_AMOUNT);
            rs = pr.executeQuery();
            if (rs.next()) {
                logger.info("Amount of cruises was received");
                return rs.getInt(1);
            } else {
                logger.warn("Amount of cruises wasn't received");
                return 0;
            }
        } catch (SQLException e) {
            logger.error("Dao caught an SQL exception while getting the cruises amount", e);
            e.printStackTrace();
            return 0;
        } finally {
            cp.close(rs);
            cp.close(pr);
            cp.close(c);
        }
    }

    /**
     * Gets the amount of cruises in DB which start day is bigger than inputted
     *
     * @param date - a start date
     * @return - amount of cruises
     */
    @Override
    public int getCruiseAmountDate(Timestamp date) {
        c = cp.getConnection();
        try {
            pr = c.prepareStatement(GET_CRUISE_AMOUNT_DATE);
            pr.setTimestamp(1, date);
            rs = pr.executeQuery();
            if (rs.next()) {
                logger.info("Amount of cruises by date was received");
                return rs.getInt(1);
            } else {
                logger.warn("Amount of cruises by date wasn't received");
                return 0;
            }
        } catch (SQLException e) {
            logger.error("Dao caught an SQL exception while getting the cruises amount by date", e);
            e.printStackTrace();
            return 0;
        } finally {
            cp.close(rs);
            cp.close(pr);
            cp.close(c);
        }
    }

    /**
     * Gets the limited amount of cruises in DB
     *
     * @param lang_id - id of language of cruise translation (0),
     *                - selection offset (1)
     *                - how much to select (2)
     * @return - list of cruises
     */
    @Override
    public List<Cruise> getAll(long... lang_id) {
        c = cp.getConnection();
        List<Cruise> cruiseList = new ArrayList<>();
        try {
            pr = c.prepareStatement(GET_ALL_CRUISE + " LIMIT " + lang_id[1] + "," + lang_id[2]);
            pr.setLong(1, lang_id[0]);
            pr.setLong(2, lang_id[0]);
            rs = pr.executeQuery();
            while (rs.next()) {
                cruiseList.add(Executor.cruiseCreation(rs));
            }
            logger.info("List of cruises was selected");
            return cruiseList;
        } catch (SQLException e) {
            logger.error("DAO caught an SQL exception while selecting the cruises list", e);
            e.printStackTrace();
            return null;
        } finally {
            cp.close(rs);
            cp.close(pr);
            cp.close(c);
        }
    }


    /**
     * Gets the limited amount of cruises where the start date is bigger than given
     *
     * @param lang_id    - id of cruises' translation language
     * @param start      - selection offset
     * @param amount     - amount of cruises to select
     * @param start_date - the min date
     * @return - list of cruises
     */
    @Override
    public List<Cruise> getCruiseByStartDate(long lang_id, int start, int amount, Timestamp start_date) {
        c = cp.getConnection();
        List<Cruise> cruiseList = new ArrayList<>();
        try {
            pr = c.prepareStatement(GET_CRUISE_BY_START_DATE + " LIMIT " + start + "," + amount);
            pr.setLong(1, lang_id);
            pr.setLong(2, lang_id);
            pr.setTimestamp(3, start_date);
            rs = pr.executeQuery();
            while (rs.next()) {
                cruiseList.add(Executor.cruiseCreation(rs));
            }
            logger.info("List of cruises was selected by date");
            return cruiseList;
        } catch (SQLException e) {
            logger.error("DAO caught an SQL exception while selecting the cruises list by date", e);
            e.printStackTrace();
            return null;
        } finally {
            cp.close(rs);
            cp.close(pr);
            cp.close(c);
        }
    }

    /**
     * Gets cruise by its id
     *
     * @param lang_id   - id of language of cruise translation
     * @param cruise_id - id og cruise to be selected
     * @return - cruise if it exists
     * @see Cruise
     */
    @Override
    public Cruise getCruiseById(long lang_id, long cruise_id) {
        c = cp.getConnection();
        try {
            pr = c.prepareStatement(GET_CRUISE_BY_ID);
            pr.setLong(1, lang_id);
            pr.setLong(2, lang_id);
            pr.setLong(3, cruise_id);
            rs = pr.executeQuery();
            if (rs.next()) {
                logger.info("Cruise was gotten by id");
                return Executor.cruiseCreation(rs);
            } else {
                logger.warn("Cruise wasn't gotten by id");
                return null;
            }
        } catch (SQLException e) {
            logger.error("DAO caught an SQL exception while getting the cruise by id", e);
            e.printStackTrace();
            return null;
        } finally {
            cp.close(rs);
            cp.close(pr);
            cp.close(c);
        }
    }

    /**
     * Gets translations of the particular cruise
     *
     * @param cruise_id - id of cruise to be translated
     * @return - list of cruise translations
     * @see CruiseTranslation
     */
    @Override
    public List<CruiseTranslation> getCruiseTranslation(long cruise_id) {
        c = cp.getConnection();
        List<CruiseTranslation> cruiseTranslationList = new ArrayList<>();
        try {
            pr = c.prepareStatement(GET_ALL_CRUISE_TRANSLATIONS);
            pr.setLong(1, cruise_id);
            rs = pr.executeQuery();
            while (rs.next()) {
                cruiseTranslationList.add(Executor.cruiseTranslationCreation(rs));
            }
            logger.info("List of cruises was selected");
            return cruiseTranslationList;
        } catch (SQLException e) {
            logger.error("DAO caught an SQL exception while selecting the cruise translations list", e);
            e.printStackTrace();
            return null;
        } finally {
            cp.close(rs);
            cp.close(pr);
            cp.close(c);
        }
    }

    /**
     * Updates cruise ind DB
     *
     * @param cruise - cruise to be updated
     * @return - true if cruise was updated
     * @see Cruise
     */
    @Override
    public boolean update(Cruise cruise) {
        c = cp.getConnection();
        try {
            count = 0;
            c.setAutoCommit(false);
            c.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            pr = c.prepareStatement(UPDATE_CRUISE, Statement.RETURN_GENERATED_KEYS);
            pr.setTimestamp(1, cruise.getStart());
            pr.setTimestamp(2, cruise.getEnd());
            pr.setInt(3, cruise.getPrice());
            pr.setInt(4, cruise.getAvailable());
            pr.setLong(5, cruise.getRoute().getId());
            pr.setLong(6, cruise.getLiner().getId());
            pr.setLong(7, cruise.getId());
            count += pr.executeUpdate();
            rs = pr.getGeneratedKeys();
            if (rs.next()) {
                cruise.setId(rs.getLong(1));
            }
            count = Executor.langCruiseSetter(c, cruise, UPDATE_LANGUAGE_CRUISE, count);
            if (count == cruise.getCruiseTranslationList().size() + 1) {
                c.commit();
                logger.info("Cruise was updated, transaction is committed");
                return true;
            } else {
                cp.rollback(c);
                logger.warn("Cruise wasn't updated, transaction is rolled back");
                return false;
            }
        } catch (SQLException e) {
            logger.error("DAO caught an SQL exception while updating the cruise", e);
            cp.rollback(c);
            e.printStackTrace();
            return false;
        } finally {
            cp.close(rs);
            cp.close(pr);
            cp.close(c);
        }
    }

    /**
     * Removes the cruise from DB
     *
     * @param id - id of cruise to be removed
     * @return - true if cruise was removed
     */
    @Override
    public boolean remove(long id) {
        return Executor.delete(DELETE_CRUISE, id);
    }
}
