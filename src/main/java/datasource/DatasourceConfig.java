package datasource;

import com.mysql.cj.jdbc.MysqlDataSource;

public class DatasourceConfig {
    public static MysqlDataSource mysqlDataSource() {
        MysqlDataSource dataSource = new MysqlDataSource();
        try {
            String url = String.format("jdbc:mysql://localhost:3306/assignment");
            dataSource.setURL(url);
            dataSource.setUser("root");
            dataSource.setPassword("Viettel#123");
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return dataSource;
    }
}
