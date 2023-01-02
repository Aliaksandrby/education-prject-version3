package by.carlab.users;

import by.carlab.dao.OrderDao;
import by.carlab.dao.RoleDao;
import by.carlab.dao.UserDao;
import by.carlab.model.Order;
import by.carlab.model.User;
import by.carlab.orders.OrderService;
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

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDao orderDao;

    @Override
    public User createUser(User user) {
        User userNew = new User();
        if(hasUsernameIntoDB(user)) {
            userNew.setMessage("username exists");
            return userNew;
        }
        if(hasEmailIntoDB(user)) {
            userNew.setMessage("email exists");
            return userNew;
        }
        if(isPasswordEqualsToConfirmPassword(user)) {
            userNew.setMessage("wrong confirm password");
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
        List<Order> orderList = orderService.showOrderList();
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
                editUser.setMessage("username exists");
                return editUser;
            }
        }
        if(!editUser.getEmail().equals(user.getEmail())) {
            if(hasEmailIntoDB(user)) {
                editUser.setMessage("email exists");
                return editUser;
            }
        }
        if(isPasswordEqualsToConfirmPassword(user)) {
            editUser.setMessage("wrong confirm password");
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
