package DAO;

import DAO.Implementation.*;
import DAO.Util.ConnectionPool;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    /**
     * A singleton method of getting a DAOFactory instance
     *
     * @return - an instance of DAOFactory
     */
    public static synchronized DAOFactory getInstance() {
        if (daoFactory == null)
            daoFactory = new DAOFactory();
        return daoFactory;
    }

    /**
     * Creates an instance of ApplicationImpl
     *
     * @return - an instance of ApplicationImpl
     * @see ApplicationImpl
     */
    public ApplicationImpl newApplicationImplInstance() {
        return new ApplicationImpl(ConnectionPool.getInstance());
    }

    /**
     * Creates an instance of CompanyImpl
     *
     * @return - an instance of CompanyImpl
     * @see CompanyImpl
     */
    public CompanyImpl newCompanyImplInstance() {
        return new CompanyImpl(ConnectionPool.getInstance());
    }

    /**
     * Creates an instance of CruiseImpl
     *
     * @return - an instance of CruiseImpl
     * @see CruiseImpl
     */
    public CruiseImpl newCruiseImplInstance() {
        return new CruiseImpl(ConnectionPool.getInstance());
    }

    /**
     * Creates an instance of LinerImpl
     *
     * @return - an instance of LinerImpl
     * @see LinerImpl
     */
    public LinerImpl newLinerImplInstance() {
        return new LinerImpl(ConnectionPool.getInstance());
    }

    /**
     * Creates an instance of RoleImpl
     *
     * @return - an instance of RoleImpl
     * @see RoleImpl
     */
    public RoleImpl newRolesImplInstance() {
        return new RoleImpl(ConnectionPool.getInstance());
    }

    /**
     * Creates an instance of RouteImpl
     *
     * @return - an instance of RouteImpl
     * @see RouteImpl
     */
    public RouteImpl newRouteImplInstance() {
        return new RouteImpl(ConnectionPool.getInstance());
    }

    /**
     * Creates an instance of StaffImpl
     *
     * @return - an instance of StaffImpl
     * @see StaffImpl
     */
    public StaffImpl newStaffImplInstance() {
        return new StaffImpl(ConnectionPool.getInstance());
    }

    /**
     * Creates an instance of UserImpl
     *
     * @return - an instance of UserImpl
     * @see UserImpl
     */
    public UserImpl newUserImplInstance() {
        return new UserImpl(ConnectionPool.getInstance());
    }
}