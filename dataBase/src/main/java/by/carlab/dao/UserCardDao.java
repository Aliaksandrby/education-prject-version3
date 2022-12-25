package by.carlab.dao;

import by.carlab.model.UserCard;

public interface UserCardDao {
    void create(UserCard userCard);
    void delete(UserCard userCard);
    void update(UserCard userCard);
}
