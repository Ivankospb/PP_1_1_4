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

    SessionFactory factory = Util.getSessionFactory();
    @Override
    public void createUsersTable() { // Создание таблицы для User(ов)
        try (Session session = factory.getCurrentSession()){
            session.beginTransaction(); // открытие транзакции
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS users" +
                    "(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(50) NOT NULL, " +
                    "lastName VARCHAR(50) NOT NULL, " +
                    "age TINYINT NOT NULL)").executeUpdate();
            session.getTransaction().commit(); // закрытие транзакции и запись

            System.out.println("createUsersTable!");

        } catch (HibernateException e) {
            System.out.println("Error createUsersTable!");
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() { // Удаление таблицы User(ов)
        try (Session session = factory.getCurrentSession()){
            session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS users").executeUpdate();
            session.getTransaction().commit();

            System.out.println("dropUsersTable!");

        } catch (HibernateException e) {
            System.out.println("Error dropUsersTable!");
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) { // Добавление User в таблицу
        try (Session session = factory.getCurrentSession()){
            session.beginTransaction();
            session.save(new User(name, lastName, age));
            session.getTransaction().commit();

            System.out.println("User с именем –" + name + " добавлен в базу данных");
        } catch (HibernateException e) {
            System.out.println("Error saveUser!");
            e.printStackTrace();
        }

    }

    @Override
    public void removeUserById(long id) { // Удаление User из таблицы (по id)
        try (Session session = factory.getCurrentSession()){
            session.beginTransaction();
            session.delete(session.get(User.class, id));
            session.getTransaction().commit();
            System.out.println("removeUserById!");
        } catch (HibernateException e) {
            System.out.println("Error removeUserById!");
            e.printStackTrace();
        }

    }

    @Override
    public List<User> getAllUsers() { // Получение всех User(ов) из таблицы
        List<User> usersList = new ArrayList<>();
        try (Session session = factory.getCurrentSession()){
            session.beginTransaction();
//            userList = session.createQuery(String.valueOf(User.class)).list();
            usersList = session.createQuery("from User").getResultList();
            session.getTransaction().commit();
            System.out.println("getAllUsers!");
        } catch (HibernateException e) {
            System.out.println("Error getAllUsers!");
            e.printStackTrace();
        }
        return usersList;
    }

    @Override
    public void cleanUsersTable() { // Очистка содержания таблицы
        try (Session session = factory.getCurrentSession()){
            session.beginTransaction();
            session.createQuery("delete User").executeUpdate();
            session.getTransaction().commit();
            System.out.println("cleanUsersTable!");
        } catch (HibernateException e) {
            System.out.println("Error cleanUsersTable!");
            e.printStackTrace();
        }
    }
}
