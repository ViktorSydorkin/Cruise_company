package DAO.Implementation;

import DAO.Execptions.DAOException;
import DAO.Interfaces.RolesDAO;
import DAO.Util.ConnectionPool;
import Model.Entity.Role;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleImpl implements RolesDAO {

    private final String GET_ALL_ROLES = "SELECT * FROM role;";

    private ConnectionPool cp;
    private Connection c;
    private PreparedStatement pr;
    private ResultSet rs;
    private Logger logger;

    public RoleImpl(ConnectionPool connectionPool) {
        this.cp = connectionPool;
        logger = Logger.getLogger(RoleImpl.class);
    }

    /**
     * Can't be used
     *
     * @param role
     * @return
     * @throws DAOException
     * @see Role
     */
    @Override
    public boolean add(Role role) throws DAOException {
        logger.error("The new role can't be added!");
        throw new DAOException("You can't add any other role!");
    }

    /**
     * Gets all roles from DB
     *
     * @param lang_id - not used in current implementation
     * @return - list of roles
     */
    @Override
    public List<Role> getAll(long... lang_id) {
        c = cp.getConnection();
        List<Role> roleList = new ArrayList<>();
        try {
            pr = c.prepareStatement(GET_ALL_ROLES);
            rs = pr.executeQuery();
            while (rs.next()) {
                Role role = new Role();
                role.setId(rs.getLong("role_id"));
                role.setType(rs.getString("type"));
                roleList.add(role);
            }
            logger.info("List of roles was selected");
            return roleList;
        } catch (SQLException e) {
            logger.error("DAO caught an SQL exception while selecting the roles list", e);
            e.printStackTrace();
            return null;
        } finally {
            cp.close(rs);
            cp.close(pr);
            cp.close(c);
        }
    }

    /**
     * Can't be used
     *
     * @param role
     * @return
     * @throws DAOException
     * @see Role
     */
    @Override
    public boolean update(Role role) throws DAOException {
        logger.error("Roles can't be updated!");
        throw new DAOException("You can't update any role!");
    }

    /**
     * Can't be used
     *
     * @param id
     * @return
     * @throws DAOException
     */
    @Override
    public boolean remove(long id) throws DAOException {
        logger.error("Roles can't be removed!");
        throw new DAOException("You can't delete any role!");
    }
}
