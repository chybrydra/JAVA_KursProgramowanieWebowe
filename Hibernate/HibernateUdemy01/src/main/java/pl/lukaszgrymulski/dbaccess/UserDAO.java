package pl.lukaszgrymulski.dbaccess;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pl.lukaszgrymulski.entity.Users;

import java.util.ArrayList;
import java.util.List;

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

    public List<Users> retrieveAllUsers(){
        Session session = factory.getCurrentSession();
        List<Users> allUsersList = new ArrayList<Users>();
        try {
            session.beginTransaction();
            allUsersList = session.createQuery("from users").getResultList();
        } finally {
            session.close();
        }
        return allUsersList;
    }

    public List<Users> retrieveUsersByFirstLetter(char letter){
        Session session = factory.getCurrentSession();
        List<Users> allUsersList = new ArrayList<Users>();
        try {
            session.beginTransaction();
            allUsersList = session.createQuery("FROM users WHERE first_name LIKE '"+letter+"%'" ).getResultList();
        } finally {
            session.close();
        }
        return allUsersList;
    }


    public void updateUsername(int id, String newUsername){
        Session session = factory.getCurrentSession();
        Users user = new Users();
        try {
            session.beginTransaction();
            user = session.get(Users.class, id);
            user.setUsername(newUsername);
            session.getTransaction().commit();
            System.out.println("Username (id: " + id + ") changed to " + newUsername);
        } finally {
            session.close();
        }
    }

    public void removeUser(int id){
        Session session = factory.getCurrentSession();
        Users user = new Users();
        try{
            session.beginTransaction();
            user = session.get(Users.class, id);
            session.delete(user);
            session.getTransaction().commit();
            System.out.println("User (id: " + id + ") deleted!");
        } finally {
            session.close();
        }
    }


}
