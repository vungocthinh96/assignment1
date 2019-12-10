package dao;

import com.mysql.cj.jdbc.MysqlDataSource;
import datasource.DatasourceConfig;
import model.User;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class UserDAOTest {

    private static UserDAOImpl userDAO;

    @BeforeClass
    public static void init() throws SQLException, ClassNotFoundException {
        MysqlDataSource dataSource = DatasourceConfig.mysqlDataSource();
        Connection connection = dataSource.getConnection();
        userDAO = new UserDAOImpl(connection);
    }
    @Test
    public void testGetUserByUsername() {
        User user = userDAO.getUserByUsername("thinhvn");
        System.out.println(user.getUsername());
    }
}
