package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import web.model.Role;
import web.model.User;
import web.service.UserService;
import java.util.List;


@RestController
public class AllRestController {

    private UserService userService;

    @Autowired
    public AllRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getUser")
    public User userInfo() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @GetMapping("/adminAllUsers")
    public List<User> usersList() {
        return userService.allUsers();
    }

    @GetMapping("/adminAllRoles")
    public List<Role> rolesList() {
        return userService.allRoles();
    }

    @GetMapping("/newUser")
    public User createNewUser() {
        return new User();
    }


    @PostMapping("/addUser")
    public ResponseEntity<?> addUser(@RequestBody User user) {
        userService.saveUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/userEdit")
    public ResponseEntity<?> editUser(@RequestBody User user) {


        userService.edit(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @DeleteMapping("/userDelete")
    public ResponseEntity<?> deleteUser(@RequestBody User user) {
        userService.deleteUser(user.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getUser/{id}")
    public User findUserById(@PathVariable(name = "id") Long id){
        return userService.findById(id);
    }
}
