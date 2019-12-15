package dao;

import model.User;
import org.junit.Test;

public class UserDAOTest {

    @Test
    public void testGetUserByUsername() {
        UserDAOImpl userDAO = new UserDAOImpl();
        User user = userDAO.getUserByUsername("thinhvn");
        System.out.println(user.toString());
    }

    @Test
    public void testAddAccountToDB() {
        UserDAOImpl userDAO = new UserDAOImpl();
        User user = new User("thinhvn", "thinhvn", "vu ngoc thinh",
                "0972264970", "vungocthinh@gmail.com",
                "Nam Dinh", "27/06/1996");
        userDAO.addAccountToDB(user);
    }
}
