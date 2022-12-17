package by.carlab.dao;

import by.carlab.model.Role;
import by.carlab.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class RoleDaoImpl implements RoleDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Role findById(int id) {
        return sessionFactory.getCurrentSession().get(Role.class, id);
    }
}
