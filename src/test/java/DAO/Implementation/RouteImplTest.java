package DAO.Implementation;

import DAO.Util.ConnectionPool;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static DAO.Implementation.TestData.route1;
import static DAO.Implementation.TestData.route2;
import static org.junit.jupiter.api.Assertions.*;

class RouteImplTest {

    RouteImpl routeImpl = new RouteImpl(ConnectionPool.getInstance());
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
        routeImpl.add(route1);
        assertEquals(8, routeImpl.getAll(1).size());
    }

    @Test
    void getAll() {
        assertEquals(7, routeImpl.getAll(1).size());
    }

    @Test
    void update() {
        routeImpl.update(route2);
        assertEquals(route2.toString(), routeImpl.getAll(1).get(0).toString());
    }

    @Test
    void remove() {
        routeImpl.remove(1);
        assertEquals(6, routeImpl.getAll(1).size());
    }
}