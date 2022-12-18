package by.carlab.users;

import by.carlab.dao.RoleDao;
import by.carlab.dao.UserDao;
import by.carlab.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    @Override
    public void createUser(User user) {
        User userNew = new User();
        if(isPasswordEqualsToConfirmPassword(user) && hasUsernameIntoDB(user)) {
            userNew.setUsername(user.getUsername());
            userNew.setEmail(user.getEmail());
            userNew.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userNew.setRoleSet(Set.of(roleDao.findById(1)));
            userDao.create(userNew);
        } else {
            System.out.println("password is bad"); //TODO: message to page
            System.out.println("login is bad"); //TODO: message to page
        }
    }

    @Override
    public boolean isPasswordEqualsToConfirmPassword(User user) {
        return user.getPassword().equals(user.getConfirmPassword());
    }

    @Override
    public boolean hasUsernameIntoDB(User user) {
        User userFound = userDao.findByUsername(user.getUsername());
        return userFound == null;
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
    public void deleteUser(int id) {
        userDao.delete(id);
    }

    @Override
    public User editUser(User user, int id, int roleId) { // TODO: email and checking everything
        User editUser = findById(id);
        editUser.setUsername(user.getUsername());
        editUser.setEmail(user.getEmail());
        editUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        editUser.setRoleSet(Set.of(roleDao.findById(roleId)));
        userDao.update(editUser);
        return editUser;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        if (user == null) throw new UsernameNotFoundException("User not found");
        return user;
    }
}
