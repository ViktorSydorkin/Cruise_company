package Model.Service;

import Model.Entity.User;

import java.util.List;

public interface UserService {
    User getUserByEmail(String email);

    User getUserByEmailAndPassword(String email, String password);

    List<User> getAllUsers();

    boolean addUser(User user);

    boolean updateUser(User user);

    String passEncryption(String email, String password);

    boolean deleteUser(long id);
}
