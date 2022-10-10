package DAO.Implementation;

import Model.Entity.*;

import java.util.ArrayList;
import java.util.List;

public interface TestData {
    /*company*/
    Company company1 = new Company(1, "Water pleasure");
    Company company2 = new Company(2, "Dreams makers");
    Company company3 = new Company(3, "Ocean taxi");
    Company company4 = new Company(4, "Test");
    Company company5 = new Company(1, "Updated");
    List<Company> companyList = new ArrayList<>(List.of(company2, company3, company1));

    /*liner*/



    /*language*/
    Language english = new Language(1, "English");
    Language ukrainian = new Language(2, "Ukrainian");
    /*role*/
    Role user = new Role(1, "User");
    Role admin = new Role(2, "Admin");
    List<Role> roleList = new ArrayList<>(List.of(user, admin));
    /*user*/
    User user1 = new User(13, "Bull", "Buller", "bull@gmail.com", "cbfcb3e769c59bd462f35b2a3beb3aa541501c23", user, ukrainian);
    User user2 = new User(16, "Changed", "Byadmin", "newww@gmail.com", "071e3f5c708b7d3a5d9133de53b56b133ba6b0db", user, ukrainian);
    List<User> userList = new ArrayList<>(List.of(user1, user2));
    List<User> removeList = new ArrayList<>(List.of(user1));
    User user3 = new User(13, "Bull", "Buller1", "bull@gmail.com", "cbfcb3e769c59bd462f35b2a3beb3aa541501c23", user, ukrainian);
    User user4 = new User((long) Integer.MAX_VALUE + 10, "Test", "Test", "test@gmail.com", "123456", user, ukrainian);

    /*route*/
    Route route1 = new Route(10, 10, List.of(new RouteTranslation(english, "Test", "Test"), (new RouteTranslation(ukrainian, "Тест", "Тест"))));
    Route route2 = new Route(1, 13, List.of(new RouteTranslation(english, "Guangzhou", "Guangzhou")));

    /*cruise*/


    /*application*/


}
