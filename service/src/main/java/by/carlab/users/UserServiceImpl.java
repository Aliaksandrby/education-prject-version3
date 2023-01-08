package by.carlab.users;

import by.carlab.dao.OrderDao;
import by.carlab.dao.RoleDao;
import by.carlab.dao.UserDao;
import by.carlab.model.Order;
import by.carlab.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final static String usernameError = "username exists";
    private final static String emailError = "email exists";
    private final static String confirmError = "wrong confirm password";

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User createUser(User user) {
        User userNew = new User();
        if(hasUsernameIntoDB(user)) {
            userNew.setMessage(usernameError);
            return userNew;
        }
        if(hasEmailIntoDB(user)) {
            userNew.setMessage(emailError);
            return userNew;
        }
        if(isPasswordEqualsToConfirmPassword(user)) {
            userNew.setMessage(confirmError);
            return userNew;
        }
        userNew.setUsername(user.getUsername());
        userNew.setEmail(user.getEmail());
        userNew.setPassword(passwordEncoder.encode(user.getPassword()));
        userNew.setRoleSet(Set.of(roleDao.findById(1)));
        userDao.create(userNew);
        return userNew;
    }

    @Override
    public boolean isPasswordEqualsToConfirmPassword(User user) {
        return !user.getPassword().equals(user.getConfirmPassword());
    }

    @Override
    public boolean hasUsernameIntoDB(User user) {
        return !(userDao.findByUsername(user.getUsername()) == null);
    }

    @Override
    public boolean hasEmailIntoDB(User user) {
        return !(userDao.findByEmail(user.getEmail()) == null);
    }

    @Override
    public List<User> readAll() {
        return userDao.readAll();
    }

    @Override
    public User findById(int id) {
        return userDao.findById(id);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public void deleteUser(int id) {
        User user = userDao.findById(id);
        List<Order> orderList = orderDao.readAll();
        for (Order order : orderList) {
            if(Objects.equals(order.getUser().getId(), user.getId())) {
                order.setUser(null);
                orderDao.update(order);
            }
        }
        userDao.delete(id);
    }

    @Override
    public User editUser(User user, int id, int roleId) {
        User editUser = userDao.findById(id);
        if(!editUser.getUsername().equals(user.getUsername())) {
            if(hasUsernameIntoDB(user)) {
                editUser.setMessage(usernameError);
                return editUser;
            }
        }
        if(!editUser.getEmail().equals(user.getEmail())) {
            if(hasEmailIntoDB(user)) {
                editUser.setMessage(emailError);
                return editUser;
            }
        }
        if(isPasswordEqualsToConfirmPassword(user)) {
            editUser.setMessage(confirmError);
            return editUser;
        }
        editUser.setUsername(user.getUsername());
        editUser.setEmail(user.getEmail());
        editUser.setPassword(passwordEncoder.encode(user.getPassword()));
        editUser.setRoleSet(Set.of(roleDao.findById(roleId)));
        userDao.update(editUser);
        return editUser;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userDao.findByUsername(username);
    }
}
