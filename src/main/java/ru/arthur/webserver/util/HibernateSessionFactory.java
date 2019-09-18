package ru.arthur.webserver.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import ru.arthur.webserver.model.User;

import java.util.ResourceBundle;


public class HibernateSessionFactory {
//    private final static Logger log = Logger.getLogger(HibernateSessionFactory.class.getName());

    private static ResourceBundle rb = ResourceBundle.getBundle("application");
    private static ResourceBundle hibernate = ResourceBundle.getBundle("hibernate"); // hibernate.properties


    public static final SessionFactory sessionFactory = new HibernateSessionFactory().getConnection();

    private HibernateSessionFactory() {

    }

    private SessionFactory getConnection() {
        try {
            System.out.println("работаем");
            Configuration cfg = new Configuration()
                    .setProperty("hibernate.connection.driver_class", rb.getString("driverClassName"))
                    .setProperty("hibernate.connection.username", rb.getString("username"))
                    .setProperty("hibernate.connection.password", rb.getString("password"))
                    .setProperty("hibernate.hbm2ddl.auto", hibernate.getString("event"))
                    .setProperty("hibernate.connection.url", rb.getString("url"))
                    .setProperty("show_sql", hibernate.getString("show"))
                    .setProperty("hibernate.dialect", hibernate.getString("dialect"))
                    .addAnnotatedClass(User.class);
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().
                    applySettings(cfg.getProperties()).build();
            SessionFactory sessionFactory = cfg.buildSessionFactory(serviceRegistry);
//            log.info("База данных подсоединена (Hibernate)");
            return sessionFactory;
        } catch (Exception e) {
//            log.warning("Ошибка коннекта Hibernate");
            e.printStackTrace();
        }
        return null;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
