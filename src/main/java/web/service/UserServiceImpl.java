package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.RoleDao;
import web.dao.UserDao;
import web.model.Role;
import web.model.User;

import java.util.List;
import java.util.Set;


@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    private RoleDao roleDao;

    @Autowired
    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String signIn) throws UsernameNotFoundException {
        User user = userDao.findByUserForEmail(signIn);
            if (user == null){
                System.err.println("User not found");
            }
        return user;
    }

    @Transactional
    public List<User> allUsers() {
        return userDao.allUsers();
    }

    @Transactional
    public User findById(Long id) {
        return userDao.findById(id);
    }

    @Transactional
    public void deleteUser(Long id) {
        userDao.deleteUser(id);
    }

    @Transactional
    public boolean saveUser(User user) {
        User userFromDB = userDao.findByUserForEmail(user.getUsername());
        if (userFromDB != null) {
            return false;
        }
        Set<Role> roleSet = (Set<Role>) user.getAuthorities();
        user.setRoles(roleSet);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userDao.saveUser(user);
    }

    @Override
    @Transactional
    public boolean edit(User user) {
        Set<Role> roleSet = (Set<Role>) user.getAuthorities();
        user.setRoles(roleSet);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userDao.edit(user);
    }

    @Transactional
    public List<User> usergtList(Long idMin) {
        return userDao.usergtList(idMin);
    }

    /*методы для Роли*/
    @Override
    @Transactional
    public List<Role> allRoles() {
        return roleDao.allRoles();
    }

    @Override
    @Transactional
    public Role findByIdRole(Long id) {
        return roleDao.findById(id);
    }

    @Override
    @Transactional
    public void deleteRole(Long id) {
        roleDao.deleteRole(id);
    }

    @Override
    @Transactional
    public boolean saveRole(Role role) {
        return roleDao.saveRole(role);
    }

}
