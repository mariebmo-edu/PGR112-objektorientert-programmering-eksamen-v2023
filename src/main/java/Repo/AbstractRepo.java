package Repo;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractRepo<T> {

    protected DataSource dataSource;
    protected String tableName;

    public AbstractRepo(String dataSourceName, String tableName) throws IOException {
        this.dataSource = DataSourceManager.getDataSource(dataSourceName);
        this.tableName = tableName;
    }

    public boolean executeSQL(String sql) {

        try (Connection con = dataSource.getConnection()) {
            Statement s = con.createStatement();
            s.execute(sql);

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public ArrayList<T> getAll(){

        ArrayList<T> list = new ArrayList<>();

        try (Connection con = dataSource.getConnection()) {
            Statement s = con.createStatement();
            ResultSet resultSet = s.executeQuery("SELECT * FROM " + tableName);

            while (resultSet.next()) {
                list.add(resultMapper(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public T getById(int id){

        T t = null;

        try (Connection con = dataSource.getConnection()) {
            Statement s = con.createStatement();
            ResultSet resultSet = s.executeQuery("SELECT * FROM " + tableName + " WHERE id = " + id);

            if (resultSet.next()) {
                t = resultMapper(resultSet);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return t;
    }

    public boolean deleteById(int id){

        try (Connection con = dataSource.getConnection()) {
            Statement s = con.createStatement();
            s.execute("DELETE FROM " + tableName + " WHERE id = " + id);

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean update(T t){

        try (Connection con = dataSource.getConnection()) {
            String query = "UPDATE " + tableName + " SET " + updateMapper(t) + " WHERE id = " + getId(t);
            PreparedStatement preparedStatement = con.prepareStatement(query);

            int parameterIndex = 1;
            for (Object o : modelValues(t).values()) {
                preparedStatement.setObject(parameterIndex++, o);
            }

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean insert(T t){

        try (Connection con = dataSource.getConnection()) {
            Statement s = con.createStatement();
            String query = "INSERT INTO " + tableName + insertMapper(t);
            s.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public String insertMapper(T t){
        StringBuilder columns = new StringBuilder();
        StringBuilder values = new StringBuilder();

        for(Map.Entry<String, Object> entry : modelValues(t).entrySet()){
            columns.append(entry.getKey()).append(", ");

            if(entry.getValue() instanceof String)
                values.append("'").append(entry.getValue()).append("', ");
            else
                values.append(entry.getValue()).append(", ");
        }

        return "(" + columns.substring(0, columns.length() - 2) + ") VALUES (" + values.substring(0, values.length() - 2) + ")";
    }

    public  String updateMapper(T t){
        StringBuilder stringBuilder = new StringBuilder();

        for(Map.Entry<String, Object> entry : modelValues(t).entrySet()){
            stringBuilder.append(entry.getKey()).append(" = ?, ");
        }

        return stringBuilder.substring(0, stringBuilder.length() - 2);
    }

    public abstract T resultMapper(ResultSet resultSet) throws SQLException;

    public abstract HashMap<String, Object> modelValues(T t);

    public abstract int getId(T t);
}
