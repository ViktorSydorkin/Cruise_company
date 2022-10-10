package DAO.Implementation;

import DAO.Util.ConnectionPool;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static DAO.Implementation.TestData.*;
import static org.junit.jupiter.api.Assertions.*;

class CompanyImplTest {

    CompanyImpl companyImpl = new CompanyImpl(ConnectionPool.getInstance());

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

        companyImpl.add(company4);
        assertEquals(4, companyImpl.getAll().size());
    }

    @Test
    void getAll() {
        assertEquals(companyList.toString(), companyImpl.getAll().toString());
    }

    @Test
    void update() {
        companyImpl.update(company5);
        assertEquals(company5.toString(), companyImpl.getAll().get(2).toString());
    }

    @Test
    void remove() {
        companyImpl.remove(1);
        assertEquals(2, companyImpl.getAll().size());
    }
}