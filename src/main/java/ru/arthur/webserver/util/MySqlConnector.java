package ru.arthur.webserver.util;

import ru.arthur.webserver.dao.imple.UserDAOHibernateImpl;
import ru.arthur.webserver.dao.imple.UserDAOJDBCImpl;
import ru.arthur.webserver.dao.interf.UserDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MySqlConnector implements ConnectorDAO {
//    private final static Logger log = Logger.getLogger(MySqlConnector.class.getName());

    private static ResourceBundle config = ResourceBundle.getBundle("application"); // application.properties
    private static ResourceBundle JDBC = ResourceBundle.getBundle("JDBC"); // JDBC.properties
    private String stateORM;

    public MySqlConnector() {
        stateORM = config.getString("stateORM");
    }

    @Override
    public UserDAO getConnection() {

        if (stateORM.equals("OFF")) {

            String url = config.getString("url");
            String driver = config.getString("driverClassName");
            String user = config.getString("username");
            String password = config.getString("password");

            try {
                Class.forName(driver).newInstance();
                Connection connection = DriverManager.getConnection(url, user, password);
                connection.setAutoCommit(false);
//                log.info("База данных подсоединена (JDBC)");
                return new UserDAOJDBCImpl(connection);
            } catch (ClassNotFoundException | SQLException | IllegalAccessException | InstantiationException e) {
//                log.warning("Ошибка коннекта JDBC");
                e.printStackTrace();
            }
        } else if (stateORM.equals("ON")) {
            try {
                return new UserDAOHibernateImpl(HibernateSessionFactory.getSessionFactory());
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return null;
    }
}
