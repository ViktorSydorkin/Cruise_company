package DAO.Implementation;

import DAO.Util.ConnectionPool;
import Model.Entity.Company;
import Model.Entity.Liner;
import Model.Entity.Staff;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class StaffImplTest {

    StaffImpl staffImpl = new StaffImpl(ConnectionPool.getInstance());
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
        staffImpl.add(new Staff(50, "Test", "Test", "Test",  new Liner(1, 1, "Test", 1,
                new FileInputStream("C:\\Users\\plwkc\\IdeaProjects\\Cruise_company\\fifth.jpg"),
                new Company(1, "Water pleasure"))));
        assertEquals(36, staffImpl.getAll().size());
    }

    @Test
    void getAll() {
        assertEquals(35, staffImpl.getAll().size());
    }

    @Test
    void update() throws FileNotFoundException {

        Staff staff = new Staff(1, "Tomee", "Brandt", "Captain",
                new Liner(1, 10000, "Braw", 10,
                        new FileInputStream("C:\\Users\\plwkc\\IdeaProjects\\Cruise_company\\fifth.jpg"),
                        new Company(1, "Water pleasure")));
        staffImpl.update(staff);
        assertEquals(staff.toString(), staffImpl.getAll().get(0).toString());
    }

    @Test
    void getStaffById() throws FileNotFoundException {
        Staff staff = new Staff(1, "Tomi", "Brandt", "Captain",
                new Liner(1, 10000, "Braw", 10,
                        new FileInputStream("C:\\Users\\plwkc\\IdeaProjects\\Cruise_company\\fifth.jpg"),
                        new Company(1, "Water pleasure")));

        assertEquals(staffImpl.getStaffById(1).toString(), staff.toString());
    }

    @Test
    void remove() {
        staffImpl.remove(1);
        assertEquals(34, staffImpl.getAll().size());
    }
}