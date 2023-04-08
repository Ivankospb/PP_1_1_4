package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() { // Создание таблицы для User(ов)

    }

    @Override
    public void dropUsersTable() { // Удаление таблицы User(ов)

    }

    @Override
    public void saveUser(String name, String lastName, byte age) { // Добавление User в таблицу

    }

    @Override
    public void removeUserById(long id) { // Удаление User из таблицы (по id)

    }

    @Override
    public List<User> getAllUsers() { // Получение всех User(ов) из таблицы

        return null;
    }

    @Override
    public void cleanUsersTable() { // Очистка содержания таблицы


    }
}