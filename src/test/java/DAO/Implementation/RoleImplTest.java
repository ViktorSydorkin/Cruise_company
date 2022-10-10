package DAO.Implementation;

import DAO.Execptions.DAOException;
import DAO.Util.ConnectionPool;
import Model.Entity.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static DAO.Implementation.TestData.roleList;
import static org.junit.jupiter.api.Assertions.*;

class RoleImplTest {

    RoleImpl roleImpl = new RoleImpl(ConnectionPool.getInstance());
    @BeforeEach
    void createDB() {
        try {
            CreateDB.createDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Test
    void add() {

        Exception exception = Assertions.assertThrows(DAOException.class, ()-> {
            roleImpl.add(new Role());
        });
        assertEquals("You can't add any other role!", exception.getMessage());
    }

    @Test
    void getAll() {
        assertEquals(roleList.toString(), roleImpl.getAll().toString());
    }

    @Test
    void update() {
        Exception exception = Assertions.assertThrows(DAOException.class, ()-> {
            roleImpl.update(new Role());
        });
        assertEquals("You can't update any role!", exception.getMessage());
    }

    @Test
    void remove() {
        Exception exception = Assertions.assertThrows(DAOException.class, ()-> {
            roleImpl.remove(1);
        });
        assertEquals("You can't delete any role!", exception.getMessage());
    }
}