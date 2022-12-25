package by.carlab.dao;

import by.carlab.model.UserCard;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserCardDaoImpl implements UserCardDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void create(UserCard userCard) {
        sessionFactory.getCurrentSession().save(userCard);
    }

    @Override
    public void delete(UserCard userCard) {
        UserCard loadedUserCard = sessionFactory.getCurrentSession().load(UserCard.class, userCard.getId());
        sessionFactory.getCurrentSession().delete(loadedUserCard);
    }

    @Override
    public void update(UserCard userCard) {
        sessionFactory.getCurrentSession().update(userCard);
    }
}
