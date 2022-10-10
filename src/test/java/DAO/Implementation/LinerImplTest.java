package DAO.Implementation;

import DAO.Util.ConnectionPool;
import Model.Entity.Company;
import Model.Entity.Liner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class LinerImplTest {

    LinerImpl linerImpl = new LinerImpl(ConnectionPool.getInstance());

    @BeforeEach
    void createDB() {
        try {
            CreateDB.createDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Test
    void add() throws FileNotFoundException {
        linerImpl.add(new Liner(1, 1, "Test", 1,
                new FileInputStream("C:\\Users\\plwkc\\IdeaProjects\\Cruise_company\\fifth.jpg"),
                new Company(1, "Water pleasure")));
        assertEquals(7, linerImpl.getAll().size());

    }

    @Test
    void getAll() {
        assertEquals(6, linerImpl.getAll().size());
    }

    @Test
    void getLinerById() throws FileNotFoundException {
        Liner liner2 = new Liner(1, 10000, "Braw", 10,
                new FileInputStream("C:\\Users\\plwkc\\IdeaProjects\\Cruise_company\\fifth.jpg"),
                new Company(1, "Water pleasure"));
        assertEquals(liner2.toString(), linerImpl.getLinerById(1).toString());
    }

    @Test
    void update() throws FileNotFoundException {
        Liner liner2 = new Liner(1, 10001, "Braw", 10,
                new FileInputStream("C:\\Users\\plwkc\\IdeaProjects\\Cruise_company\\fifth.jpg"),
                new Company(1, "Water pleasure"));
        linerImpl.update(liner2);
        assertEquals(liner2.toString(), linerImpl.getAll().get(0).toString());
    }

    @Test
    void remove() {
        linerImpl.remove(1);
        assertEquals(5, linerImpl.getAll().size());

    }
}