package by.carlab.dao;

import by.carlab.model.Role;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl implements RoleDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Role findById(int id) {
        return sessionFactory.getCurrentSession().get(Role.class, id);
    }
}
