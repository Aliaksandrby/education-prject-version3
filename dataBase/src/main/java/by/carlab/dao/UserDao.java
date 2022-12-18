package by.carlab.dao;

import by.carlab.model.User;

import java.util.List;

public interface UserDao {
    void create(User user);
    List<User> readAll();
    void update(User user);
    //void delete(User user);
    void delete(int id);
    User findById(int id);
    User findByUsername(String username);
}
