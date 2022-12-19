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
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User createUser(User user) { //TODO: size string
        User userNew = new User();
        if(!hasUsernameIntoDB(user)) {
            userNew.setMessage("login exists");
            return userNew;
        }
        if(!hasEmailIntoDB(user)) {
            userNew.setMessage("email exists");
            return userNew;
        }
        if(!isPasswordEqualsToConfirmPassword(user)) {
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
        return user.getPassword().equals(user.getConfirmPassword());
    }

    @Override
    public boolean hasUsernameIntoDB(User user) {
        User userFound = userDao.findByUsername(user.getUsername());
        return userFound == null;
    }

    @Override
    public boolean hasEmailIntoDB(User user) {
        User userFound = userDao.findByEmail(user.getEmail());
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
    public User editUser(User user, int id, int roleId) { //TODO: size string
        User editUser = findById(id);
        if(!editUser.getUsername().equals(user.getUsername())) {
            System.out.println(editUser.getUsername().equals(user.getUsername()));
            if(!hasUsernameIntoDB(user)) {
                editUser.setMessage("username exists");
                return editUser;
            }
        }
        if(!editUser.getEmail().equals(user.getEmail())) {
            if(!hasEmailIntoDB(user)) {
                editUser.setMessage("email exists");
                return editUser;
            }
        }
        if(!isPasswordEqualsToConfirmPassword(user)) {
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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        if (user == null) throw new UsernameNotFoundException("User not found");
        return user;
    }
}
