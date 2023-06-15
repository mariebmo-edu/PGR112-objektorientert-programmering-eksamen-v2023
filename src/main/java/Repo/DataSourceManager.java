package Repo;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class DataSourceManager {
    private static final Map<String, DataSource> dataSources = new HashMap<>();

    public static DataSource getDataSource(String name) throws IOException {
        DataSource dataSource = dataSources.get(name);
        if (dataSource == null) {
            dataSource = createDataSource(name);
            dataSources.put(name, dataSource);
        }

        return dataSource;
    }

    private static DataSource createDataSource(String name) throws IOException {
        Properties properties = new Properties();
        FileReader fileReader = new FileReader("src/main/resources/environment.properties");
        properties.load(fileReader);

        MysqlDataSource mysqlDataSource = new MysqlDataSource();

        mysqlDataSource.setURL(properties.getProperty(name + "_URL"));
        mysqlDataSource.setUser(properties.getProperty(name + "_USER"));
        mysqlDataSource.setPassword(properties.getProperty(name + "_PASSWORD"));

        return mysqlDataSource;
    }
}
