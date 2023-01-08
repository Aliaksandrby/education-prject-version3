package by.carlab.users;

import by.carlab.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    String loginPassError = "wrong login or password";
    User createUser(User user);
    boolean isPasswordEqualsToConfirmPassword(User user);
    boolean hasUsernameIntoDB(User user);
    boolean hasEmailIntoDB(User user);
    List<User> readAll();
    User findById(int id);
    User findByUsername(String username);
    void deleteUser(int id);
    User editUser(User user,int id, int roleId);
}
