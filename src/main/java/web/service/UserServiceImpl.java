package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.RoleDao;
import web.dao.UserDao;
import web.model.User;

import java.util.List;


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

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String nickname) throws UsernameNotFoundException {
        User user = userDao.findByUserForNickname(nickname);
        if (user == null) {
            System.err.println("User not found");
        }
        return user;
    }

    @Transactional
    public List<User> allUsers(){
        return userDao.allUsers();
    }

    @Transactional
    public User findById(Long id){
        return userDao.findById(id);
    }

    @Transactional
    public void deleteUser(Long id){
        userDao.deleteUser(id);
    }

    @Transactional
    public boolean saveUser(User user) {
        return userDao.saveUser(user);
    }

    @Transactional
    public List<User> usergtList(Long idMin) {
        return userDao.usergtList(idMin);
    }

    @Override
    @Transactional
    public void edit(User user) {
        userDao.edit(user);
    }

    @Override
    @Transactional
    public User findByUserForNickname(String nickname) {
        return userDao.findByUserForNickname(nickname);
    }

}
