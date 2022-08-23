package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        /*UserDaoJDBCImpl userDao = new UserDaoJDBCImpl();*/
        UserDaoHibernateImpl userDao = new UserDaoHibernateImpl();
        userDao.createUsersTable();
        userDao.saveUser("Almaz", "Khalitov", (byte) 22);
        userDao.saveUser("Julia", "Menkova", (byte) 22);
        userDao.saveUser("Julia", "Khalitova", (byte) 22);
        userDao.saveUser("Ivan", "Budanov", (byte) 26);
        List<User> users = userDao.getAllUsers();
        for (User user : users) {
            System.out.println(user.toString());
        }
        userDao.cleanUsersTable();
        userDao.dropUsersTable();
    }
}