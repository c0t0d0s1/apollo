package ru.arthur.webserver.dao.interf;

import ru.arthur.webserver.model.User;

import java.util.List;

public interface UserDAO {

    User get(String login, String password);
    List<User> getAll();
    void add(User t);
    void update (User t);
    void delete (long id);
}
