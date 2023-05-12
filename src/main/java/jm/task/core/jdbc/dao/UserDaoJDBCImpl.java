package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private static final Connection connect = Util.getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String creat = "CREATE TABLE IF NOT EXISTS users (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, name VARCHAR(20) NOT NULL, lastname VARCHAR(20) NOT NULL, age INT NOT NULL)";
        try (PreparedStatement preparedStatement = connect.prepareStatement(creat)) {
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String drop = "DROP TABLE IF EXISTS users";
        try (PreparedStatement droUs = connect.prepareStatement(drop)) {
            droUs.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String insert = "INSERT INTO users (name,lastName,age)"
                + " values (?, ?, ?)";
        try (PreparedStatement preparedStatement = connect.prepareStatement(insert)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String delete = "DELETE FROM users WHERE id= " + id;
        try (PreparedStatement delet = connect.prepareStatement(delete)) {
            delet.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public List<User> getAllUsers() {
        List<User> us = new ArrayList<>();
        String query = "select * from users";
        try (Statement statement = Util.getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAge(resultSet.getByte("age"));
                us.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return us;
    }


    public void cleanUsersTable() {
        String clean = "DELETE FROM users";
        try (PreparedStatement clear = connect.prepareStatement(clean)) {
            clear.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

