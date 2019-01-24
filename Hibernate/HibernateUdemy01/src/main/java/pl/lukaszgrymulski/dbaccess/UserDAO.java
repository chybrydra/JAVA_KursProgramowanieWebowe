package pl.lukaszgrymulski.dbaccess;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pl.lukaszgrymulski.entity.Users;

public class UserDAO {

    SessionFactory factory;

    private UserDAO(){} //I don't want this object to be created without SessionFactory object...

    public UserDAO(SessionFactory factory) {
        this.factory = factory;
    }

    public void addUserToDB(Users user) {
        Session session = this.factory.getCurrentSession();
        try{
            session.beginTransaction(); //start transactioon
            session.save(user); //perform operation
            session.getTransaction().commit(); //commit the transaction
            System.out.println("New user added!");
        }finally {
            session.close();
        }
    }

    public Users retrieveUserByID(int userId){
        Session session = factory.getCurrentSession();
        Users user= new Users();
        try {
            session.beginTransaction();
            user = session.get(Users.class, userId);
            session.getTransaction().commit();
        } finally {
            session.close();
            return user;
        }
    }

    public void updateUsername(int id, String newUsername){
        Session session = factory.getCurrentSession();
        Users user = new Users();
        try {
            session.beginTransaction();
            user = session.get(Users.class, id);
            user.setUsername(newUsername);
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }


}
