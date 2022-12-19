package by.carlab.users;

import by.carlab.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    void createUser(User user);
    boolean isPasswordEqualsToConfirmPassword(User user);
    boolean hasUsernameIntoDB(User user);
    boolean hasEmailIntoDB(User user);
    List<User> readAll();
    User findById(int id);
    void deleteUser(int id);
    User editUser(User user,int id, int roleId);
}
