package ru.arthur.webserver.dao.imple;

import ru.arthur.webserver.dao.interf.UserDAO;
import ru.arthur.webserver.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOJDBCImpl implements UserDAO {
    private final String GET = "SELECT id, simpleName, login, password, role FROM users";
    private final String UPDATE = "UPDATE users SET simpleName = ?, login = ?, password = ? WHERE id = ?";
    private final String ADD = "INSERT INTO users(simpleName, login, password, role)" + "VALUES(?, ?, ?, ?)";
    private final String DELETE = "DELETE FROM users WHERE id = ?";


    private Connection connection;

    public UserDAOJDBCImpl(Connection connection) throws SQLException {
        this.connection = connection;
    }


    @Override
    public User get(String login, String password) {
        return null;
    }

    @Override
    public List<User> getAll() {

        try {
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<User> list = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(GET);

            while (resultSet.next()) {
                list.add(new User(resultSet.getInt("id"),
                        resultSet.getString("simpleName"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        resultSet.getString("role")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return list;
        }
    }

    @Override
    public void add(User user) {
        try (PreparedStatement statement = connection.prepareStatement(ADD)) {
            statement.setObject(1, user.getName());
            statement.setObject(2, user.getLogin());
            statement.setObject(3, user.getPassword());
            statement.setObject(4, "user");
//            // Выполняем запрос
            statement.execute();
            //подтверждаем изменения
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User user) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            statement.setObject(1, user.getName());
            statement.setObject(2, user.getLogin());
            statement.setObject(3, user.getPassword());
            statement.setLong(4, user.getId());
            // Выполняем запрос
            statement.executeUpdate();
            //подтверждаем изменения
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(long id) {
        try (PreparedStatement statement = connection.prepareStatement(DELETE)) {
            statement.setLong(1, id);
            // Выполняем запрос
            statement.executeUpdate();
            //подтверждаем изменения
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
