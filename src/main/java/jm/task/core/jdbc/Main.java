package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        Util.getConnection();
        UserServiceImpl userService = new UserServiceImpl();

        userService.createUsersTable(); //Создание таблицы User(ов)

        //Добавление 4 User(ов):
        userService.saveUser("Ivan", "Ivanov", (byte) 28);
        userService.saveUser("Petr", "Petrov", (byte) 27);
        userService.saveUser("Anton", "Antonov", (byte) 26);
        userService.saveUser("Elena", "Lenina", (byte) 25);

        System.out.println(userService.getAllUsers().toString());//Получение всех User из базы и вывод в консоль

        userService.cleanUsersTable(); //Очистка таблицы User(ов)

        userService.dropUsersTable(); //Удаление таблицы
    }
}