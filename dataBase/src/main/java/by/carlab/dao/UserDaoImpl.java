package by.carlab.dao;

import by.carlab.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Repository
@Transactional
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
    public void delete(User user) {
        User loadedUser = sessionFactory.getCurrentSession().load(User.class, user.getId());
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
            if(Objects.equals(usr.getUsername(), username)) {
                user = usr;
                break;
            }
        }
        return user;
    }
}
