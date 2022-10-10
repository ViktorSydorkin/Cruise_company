package DAO.Implementation;

import DAO.Interfaces.CompanyDAO;
import DAO.Util.ConnectionPool;
import Model.Entity.Company;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompanyImpl implements CompanyDAO {
    private ConnectionPool cp;
    private Connection c;
    private PreparedStatement pr;
    private ResultSet rs;
    private Logger logger;

    private final String ADD_COMPANY = "INSERT INTO company (company_title) VALUES (?);";
    private final String GET_ALL_COMPANIES = "SELECT * FROM company;";
    private final String UPDATE_COMPANY = "UPDATE company SET company_title = ? WHERE company_id = ?;";
    private final String DELETE_COMPANY = "DELETE FROM company WHERE company_id = ?;";

    public CompanyImpl(ConnectionPool connectionPool) {
        this.cp = connectionPool;
        logger = Logger.getLogger(CompanyImpl.class);
    }

    /**
     * Adds company to DB
     *
     * @param company - company to be added
     * @return - true if company was added
     * @see Company
     */
    @Override
    public boolean add(Company company) {
        c = cp.getConnection();
        try {
            pr = c.prepareStatement(ADD_COMPANY);
            pr.setString(1, company.getTitle());
            logger.info("Company is going to be added");
            return pr.executeUpdate() == 1;
        } catch (SQLException e) {
            logger.error("DAO caught an SQL exception while adding a company", e);
            e.printStackTrace();
            return false;
        } finally {
            cp.close(pr);
            cp.close(c);
        }
    }

    /**
     * Gets all companies from DB
     *
     * @param lang_id - not used in current implementation
     * @return - list of companies
     */
    @Override
    public List<Company> getAll(long... lang_id) {
        c = cp.getConnection();
        List<Company> companyList = new ArrayList<>();
        try {
            pr = c.prepareStatement(GET_ALL_COMPANIES);
            rs = pr.executeQuery();
            while (rs.next()) {
                companyList.add(Executor.companyCreation(rs));
            }
            logger.info("List of companies was selected");
            return companyList;
        } catch (SQLException e) {
            logger.error("DAO caught an SQL exception while selecting the companies list", e);
            e.printStackTrace();
            return null;
        } finally {
            cp.close(rs);
            cp.close(pr);
            cp.close(c);
        }
    }

    /**
     * Updates company in DB
     *
     * @param company - company to be updated
     * @return - true if company was updated
     * @see Company
     */
    @Override
    public boolean update(Company company) {
        c = cp.getConnection();
        try {
            pr = c.prepareStatement(UPDATE_COMPANY);
            pr.setString(1, company.getTitle());
            pr.setLong(2, company.getId());
            logger.info("Company is going to be updated");
            return pr.executeUpdate() == 1;
        } catch (SQLException e) {
            logger.error("DAO caught an SQL exception while updating the company", e);
            e.printStackTrace();
            return false;
        } finally {
            cp.close(pr);
            cp.close(c);
        }
    }

    /**
     * Removes company from DB
     *
     * @param id - id of company to be removed
     * @return - true if company was removed
     */
    @Override
    public boolean remove(long id) {
        return Executor.delete(DELETE_COMPANY, id);
    }
}
