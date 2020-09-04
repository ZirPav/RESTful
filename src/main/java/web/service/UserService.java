package web.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import web.model.Role;
import web.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<User> allUsers();

    User findById(Long id);

    void deleteUser(Long id);

    boolean saveUser(User user);

    List<User> usergtList(Long idMin);

    boolean edit(User user);

    User findByUserForNickname(String nickname);

    List<Role> allRoles();

    Role findByIdRole(Long id);

    void deleteRole(Long id);

    boolean saveRole(Role role);
}
