package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
        userDaoJDBC.createUsersTable();
        userDaoJDBC.saveUser("Almaz", "Khalitov", (byte) 22);
        userDaoJDBC.saveUser("Julia", "Menkova", (byte) 22);
        userDaoJDBC.saveUser("Julia", "Khalitova", (byte) 22);
        userDaoJDBC.saveUser("Ivan", "Budanov", (byte) 26);
        List<User> users = userDaoJDBC.getAllUsers();
        for (User user : users) {
            System.out.println(user.toString());
        }
        userDaoJDBC.cleanUsersTable();
        userDaoJDBC.dropUsersTable();
    }
}