package jm.task.core.jdbc.dao;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static java.sql.DriverManager.getConnection;

public class UserDaoJDBCImpl implements UserDao { // Главное - трай с ресурсами и нет головняка с закрытиями. try (Statement statement = connection.createStatement()) {

    Connection connection = Util.createConnection();

    public UserDaoJDBCImpl() { // Конструктор пустой по умолчанию

    }

    public void createUsersTable() { // Создание таблицы для User(ов) – не должно приводить к исключению, если такая таблица уже существует
        try (Statement statement = connection.createStatement()){
            String SQL = "CREATE TABLE IF NOT EXISTS users (id BIGINT NOT NULL AUTO_INCREMENT, name VARCHAR(255), lastName VARCHAR(255), age INT, PRIMARY KEY (id))";
            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() { // Удаление таблицы User(ов) – не должно приводить к исключению, если таблицы не существует
        try (Statement statement = connection.createStatement()){
            String SQL = "DROP TABLE IF EXISTS users";
            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) { // Добавление User в таблицу
        String SQL = "INSERT INTO users (name, lastName, age) VALUES(?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL);){
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User с именем –" + name + " добавлен в базу данных");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) { // Удаление User из таблицы (по id)
        String SQL = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL);){
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() { // Получение всех User(ов) из таблицы
        List<User> userList = new ArrayList<>();
        String SQL = "SELECT * FROM users";
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL);){

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));

                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() { // Очистка содержания таблицы
        try (Statement statement = connection.createStatement()){
            String SQL = "TRUNCATE TABLE users"; //команда удаление содержимого таблицы users
            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}