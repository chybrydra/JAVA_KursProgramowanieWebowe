package pl.lukaszgrymulski;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.lukaszgrymulski.entity.Users;

public class App {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Users.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try{
            //1. create object of entity class type
            Users user = new Users("Arni50", "damnSon", "Arnold", "Weiseneger");
            //2. start transaction
            session.beginTransaction();
            //3. perform operation
            session.save(user);
            //4. commit the transaction
            session.getTransaction().commit();
            System.out.println("New user added!");
        }finally {
            session.close();
            factory.close();
        }
    }
}
