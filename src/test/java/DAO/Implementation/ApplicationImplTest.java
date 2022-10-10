package DAO.Implementation;

import DAO.Util.ConnectionPool;
import Model.Entity.Application;
import Model.Entity.Enums.Approved;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ApplicationImplTest {

    ApplicationImpl applicationImpl = new ApplicationImpl(ConnectionPool.getInstance());

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
        Application application = applicationImpl.getAll(1).get(0);
        applicationImpl.add(application);
        assertEquals(2,applicationImpl.getAll(1).size());
    }

    @Test
    void getAll() {
        assertEquals(1,applicationImpl.getAll(1).size());
    }

    @Test
    void getAllAppsForUser(){
        Application application = applicationImpl.getAll(1).get(0);
        assertEquals(application.toString(), applicationImpl.getAllAppsForUser(1,13).get(0).toString());
        assertEquals(1, applicationImpl.getAllAppsForUser(1,13).size());
    }
    @Test
    void getAppById() {
        Application application =applicationImpl.getAppById(1, 1);
                assertNotNull(application);
    }

    @Test
    void update() {
        Application application = applicationImpl.getAll(1).get(0);
        application.setApproved(Approved.YES);
        applicationImpl.update(application);
        assertEquals(application.toString(), applicationImpl.getAppById(1,application.getId()).toString());
    }

    @Test
    void remove() {
        applicationImpl.remove(1);
        assertEquals(0,applicationImpl.getAll(1).size());
    }
}