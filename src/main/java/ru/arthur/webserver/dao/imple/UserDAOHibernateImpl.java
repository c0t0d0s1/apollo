package ru.arthur.webserver.dao.imple;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.arthur.webserver.dao.interf.UserDAO;
import ru.arthur.webserver.model.User;

import java.util.List;

public class UserDAOHibernateImpl implements UserDAO {

    private SessionFactory sessionFactory;

    public UserDAOHibernateImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User get(String login, String password) {
        return (User) sessionFactory.openSession()
                .createQuery("From User u WHERE u.login = ?1 AND u.password = ?2")
                .setParameter(1, login)
                .setParameter(2, password)
                .uniqueResult();
    }

    @Override
    public List<User> getAll() {
        List<User> users = (List<User>) sessionFactory.openSession().createQuery("From User").list();
        return users;
    }

    @Override
    public void add(User t) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(t);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(User t) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(t);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(new User(id));
        transaction.commit();
        session.close();
    }

}
