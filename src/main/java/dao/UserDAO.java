package dao;

import model.User;

import java.sql.SQLException;

public interface UserDAO {
    User getUserByUsername(String username);
    int addAccountToDB(User user);
    int changePassword(String username, String password);
}
