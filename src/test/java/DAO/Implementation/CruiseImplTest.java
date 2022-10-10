package DAO.Implementation;

import DAO.Util.ConnectionPool;
import Model.Entity.Cruise;
import Model.Entity.CruiseTranslation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CruiseImplTest {
    CruiseImpl cruiseImpl = new CruiseImpl(ConnectionPool.getInstance());

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
        Cruise cruise = cruiseImpl.getAll(1, 0, 1).get(0);
        cruiseImpl.add(cruise);
        assertEquals(7, cruiseImpl.getAll(1, 0, 10).size());
    }

    @Test
    void getCruiseAmount() {
        assertEquals(6, cruiseImpl.getAll(1, 0, 10).size());
    }


    @Test
    void getCruiseAmountDate() {
        assertEquals(4, cruiseImpl.getCruiseAmountDate(Timestamp.valueOf("2022-05-10 12:00:00")));
    }

    @Test
    void getCruiseByStartDate() {
        assertEquals(4, cruiseImpl.getCruiseByStartDate(1, 0, 10, Timestamp.valueOf("2022-05-10 12:00:00")).size());
    }

    @Test
    void getAll() {
        assertEquals(6, cruiseImpl.getAll(1, 0, 10).size());
    }

    @Test
    void getCruiseById() {
        Cruise cruise = cruiseImpl.getCruiseById(1, 1);
        assertNotNull(cruise);
    }

    @Test
    void getCruiseTranslation() {
        List<CruiseTranslation> cruiseTranslations = cruiseImpl.getCruiseTranslation(1);
        assertEquals(2, cruiseTranslations.size());
    }

    @Test
    void update() {
        Cruise cruise = cruiseImpl.getAll(1, 0, 1).get(0);
        cruise.setAvailable(22222);
        cruiseImpl.update(cruise);
        assertEquals(cruise.toString(), cruiseImpl.getCruiseById(1, cruise.getId()).toString());
    }

    @Test
    void remove() {
        cruiseImpl.remove(1);
        assertEquals(5, cruiseImpl.getAll(1, 0, 10).size());
    }
}