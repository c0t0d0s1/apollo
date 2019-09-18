package ru.arthur.webserver.util;

import ru.arthur.webserver.dao.interf.UserDAO;

public interface ConnectorDAO {

    UserDAO getConnection() ;

}
