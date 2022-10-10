package Model.Implementation;

import DAO.DAOFactory;
import DAO.Interfaces.CompanyDAO;
import Model.Entity.Company;
import Model.Service.CompanyService;
import org.apache.log4j.Logger;

import java.util.List;

public class CompanyServiceImpl implements CompanyService {

    private final CompanyDAO companyDAO;
    Logger logger;

    /**
     * Creates an instance of company's implementation
     */
    public CompanyServiceImpl() {
        this.companyDAO = DAOFactory.getInstance().newCompanyImplInstance();
        this.logger = Logger.getLogger(CompanyServiceImpl.class);

    }

    /**
     * Gets all companies from the DB
     *
     * @return - list of companies
     */
    @Override
    public List<Company> geAllCompanies() {
        logger.info("The company list is going to be selected");
        return companyDAO.getAll();
    }
}
