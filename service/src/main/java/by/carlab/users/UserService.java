package by.carlab.users;

import by.carlab.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    void createUser(User user);
    boolean isPasswordEqualsToConfirmPassword(User user);
    boolean hasUsernameIntoDB(User user);
}