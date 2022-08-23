package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.createSQLQuery("CREATE TABLE IF NOT EXISTS users" +
                "(id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT," +
                "name VARCHAR(50) NOT NULL," +
                "lastName VARCHAR(50)," +
                "age TINYINT(3))").executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.createSQLQuery("DROP TABLE IF EXISTS users").executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        /*Session session = Util.getSessionFactory().openSession();
        String sql = "INSERT INTO users" + "(name, last_name, age)" + "VALUES (?, ?, ?)";
        Query query = session.createQuery(sql);
        query.setParameter(1, name);
        query.setParameter(2, lastName);
        query.setParameter(3, age);
        session.beginTransaction();
        query.executeUpdate();
        session.getTransaction().commit();*/

        User user = new User();
        user.setName(name);
        user.setLastName(lastName);
        user.setAge(age);
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(user);
        transaction.commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.createQuery("delete from users user where id" + id).executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<User> list = session.createQuery("FROM users").list();
        transaction.commit();
        session.close();
        return list;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.createSQLQuery("DELETE FROM users").executeUpdate();
        transaction.commit();
        session.close();
    }
}
