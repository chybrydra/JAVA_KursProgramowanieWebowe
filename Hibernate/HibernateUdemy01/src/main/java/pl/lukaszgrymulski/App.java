package pl.lukaszgrymulski;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.lukaszgrymulski.dbaccess.UserDAO;
import pl.lukaszgrymulski.entity.Users;

public class App {
    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Users.class)
                .buildSessionFactory();

        UserDAO userDAO = new UserDAO(factory);

//        Users userToAdd = new Users("mag123", "kondor@wp.pl", "Janek", "Franczewski");
//        userDAO.addUserToDB(userToAdd);

        Users userFromDB = userDAO.retrieveUserByID(8);
        System.out.println(userFromDB);

        factory.close();
    }
}
