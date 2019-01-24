package pl.lukaszgrymulski;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.lukaszgrymulski.dbaccess.UserDAO;
import pl.lukaszgrymulski.entity.Users;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Users.class)
                .buildSessionFactory();

        UserDAO userDAO = new UserDAO(factory);

//        /** add new user */
//        Users userToAdd = new Users("kondor", "wrubel@oiseau.fr", "John", "Kole");
//        userDAO.addUserToDB(userToAdd);

//        /** retrieve user by ID */
//        Users userFromDB = userDAO.retrieveUserByID(8);
//        System.out.println(userFromDB);

//        /** update a user by id */
//        userDAO.updateUsername(1, "firstInRaw");

//        /** delete user */
//        userDAO.removeUser(7);

//        /** get all users list using HQL */
//        List<Users> allUsers = new ArrayList<Users>();
//        allUsers = userDAO.retrieveAllUsers();
//        for (Users user : allUsers) {
//            System.out.println(user);
//        }

//        /** get all users list using HQL */
//        List<Users> allUsersByLetter = new ArrayList<Users>();
//        allUsersByLetter = userDAO.retrieveUsersByFirstLetter('a');
//        for (Users user : allUsersByLetter) {
//            System.out.println(user);
//        }

//        userDAO.updateUserPassword(6, "laduradura");

        userDAO.deleteUserByUsername("kondor");

        factory.close();
    }
}
