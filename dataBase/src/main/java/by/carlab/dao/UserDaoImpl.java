package by.carlab.dao;

import by.carlab.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void create(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public List<User> readAll() {
        String query = "from User";
        return sessionFactory.getCurrentSession().createQuery(query, User.class).list();
    }

    @Override
    public void update(User user) {
        sessionFactory.getCurrentSession().update(user);
    }

    @Override
    public void delete(int id) {
        User loadedUser = sessionFactory.getCurrentSession().load(User.class, id);
        sessionFactory.getCurrentSession().delete(loadedUser);
    }

    @Override
    public User findById(int id) {
        return sessionFactory.getCurrentSession().get(User.class, id);
    }

    @Override
    public User findByUsername(String username) {
        User user = null;
        for (User usr : readAll()) {
            if(usr.getUsername().equals(username)) {
                user = usr;
                break;
            }
        }
        return user;
    }

    @Override
    public User findByEmail(String email) {
        User user = null;
        for (User usr : readAll()) {
            if(usr.getEmail().equals(email)) {
                user = usr;
                break;
            }
        }
        return user;
    }
}
