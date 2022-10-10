package DAO.Implementation;

import DAO.Util.ConnectionPool;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static DAO.Implementation.TestData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UserImplTest {

    UserImpl userImpl = new UserImpl(ConnectionPool.getInstance());

    @BeforeEach
    void createDB() {
        try {
            CreateDB.createDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getAll() {

        String expected = userList.toString();
        String actual = userImpl.getAll().toString();

        assertEquals(expected, actual);
    }

    @Test
    void getUserByEmail() {
        String expected = user1.toString();
        String actual = userImpl.getUserByEmail("bull@gmail.com").toString();

        assertEquals(expected, actual);
    }

    @Test
    void getUserByEmailAndPassword() {
        String expected = user1.toString();
        String actual = userImpl.getUserByEmailAndPassword("bull@gmail.com", "cbfcb3e769c59bd462f35b2a3beb3aa541501c23").toString();

        assertEquals(expected, actual);

    }

    @Test
    void update() {
        userImpl.update(user3);

        String expected = user3.toString();
        String actual =  userImpl.getUserByEmail("bull@gmail.com").toString();

        assertEquals(expected, actual);
    }

    @Test
    void add() {
        userImpl.add(user4);

        String expected = user4.toString();
        String actual = userImpl.getUserByEmail("test@gmail.com").toString();

        assertEquals(expected, actual);
    }

    @Test
    void remove() {
        userImpl.remove(16);

        String expected = removeList.toString();
        String actual = userImpl.getAll().toString();

        assertEquals(expected, actual);
    }

}