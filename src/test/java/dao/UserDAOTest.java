package dao;

import com.mysql.cj.jdbc.MysqlDataSource;
import constants.CommonConstants;
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
    public static void init() {
        try {
            MysqlDataSource dataSource = DatasourceConfig.mysqlDataSource(
                    CommonConstants.MYSQL_DRIVER_CLASS,
                    CommonConstants.HOST,
                    CommonConstants.PORT,
                    CommonConstants.DATABASE,
                    CommonConstants.USERNAME,
                    CommonConstants.PASSWORD);
            Connection connection = dataSource.getConnection();
            userDAO = new UserDAOImpl(connection);
        } catch (SQLException e) {
            System.out.println("cannot connect to database"+e.getMessage());
        }
    }

    @Test
    public void testGetUserByUsername() {
        try {
            User user = userDAO.getUserByUsername("thinhvn");
            System.out.println(user.getUsername());
        } catch (NullPointerException e) {
            System.out.println("cannot connect to database or username is not in database");
        }
    }
}
