package ru.arthur.webserver.service.interf;

import ru.arthur.webserver.model.User;

import java.util.List;


public interface UserService {
    User get(String login, String password);
    List<User> getAll();
    void add(User t);
    void update (User t);
    void delete (long id);
}
