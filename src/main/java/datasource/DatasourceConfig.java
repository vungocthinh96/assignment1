package datasource;

import com.mysql.cj.jdbc.MysqlDataSource;

public class DatasourceConfig {
    public static MysqlDataSource mysqlDataSource(String mysqlDriverClass, String host, String port, String database, String username, String password) {
        MysqlDataSource dataSource = new MysqlDataSource();
        try {
            String url = String.format("jdbc:mysql://%s:%s/%s",host, port, database);
            dataSource.setURL(url);
            dataSource.setUser(username);
            dataSource.setPassword(password);
            Class.forName(mysqlDriverClass);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return dataSource;
    }
}
