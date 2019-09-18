package ru.arthur.webserver.service.imple;

import ru.arthur.webserver.dao.interf.UserDAO;
import ru.arthur.webserver.model.User;
import ru.arthur.webserver.service.interf.UserService;
import ru.arthur.webserver.util.MySqlConnector;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDAO userDAO;

    public UserServiceImpl() {
        userDAO = new MySqlConnector().getConnection();
    }

    @Override
    public User get(String login, String password) {
        return userDAO.get(login, password);
    }

    @Override
    public List getAll() {
        return userDAO.getAll();
    }

    @Override
    public void add(User o) {
        userDAO.add(o);
    }

    @Override
    public void update(User user) {
        userDAO.update(user);
    }

    @Override
    public void delete(long id) {
        userDAO.delete(id);
    }
}
