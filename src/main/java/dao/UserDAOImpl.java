package dao;

import com.mysql.cj.jdbc.MysqlDataSource;
import datasource.DatasourceConfig;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO{
    private Connection connection;

    public UserDAOImpl() {
    }

    public UserDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User getUserByUsername(String username) {
        String sqlTemplate = "select * from user where username = ?";
        User user = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = connection.prepareStatement(sqlTemplate);
            statement.setString(1, username);
            rs = statement.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setUsername(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setName(rs.getString(4));
                user.setPhone(rs.getString(5));
                user.setEmail(rs.getString(6));
                user.setAddress(rs.getString(7));
                user.setDateOfBirth(rs.getString(8));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (rs != null && !rs.isClosed()) rs.close();
                if (statement != null && !statement.isClosed()) statement.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return user;
    }

    @Override
    public int addAccountToDB(User user) {
        String sql = "INSERT INTO user\n" +
                "(username, password, name, phone, email, address, date_of_birth)\n" +
                "VALUES(?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getName());
            statement.setString(4, user.getPhone());
            statement.setString(5, user.getEmail());
            statement.setString(6, user.getAddress());
            statement.setString(7, user.getDateOfBirth());
            statement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (statement != null && !statement.isClosed())
                    statement.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return 1;
    }

    @Override
    public int changePassword(String username, String password) {
        String sql = "UPDATE user SET password= ? where username = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, password);
            statement.setString(2, username);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (statement != null && !statement.isClosed())
                    statement.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return 1;
    }
}
