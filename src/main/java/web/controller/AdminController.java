package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.Role;
import web.model.User;
import web.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

@Controller
public class AdminController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/logout")
    public String logout() {
        return "login";
    }

    @GetMapping("/admin")
    public String userList(Model model) {

        model.addAttribute("allUsers", userService.allUsers());
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("allRoles", userService.allRoles());
        model.addAttribute("addUser", new User());

        return "users";
    }

    @PostMapping("/admin/add")
    public String addUser(@ModelAttribute("addUser") User userForm,
                          @RequestParam(value = "addRole", required = false)  String userRole,
                          Model model) {

        model.addAttribute("allRoles", userService.allRoles());

        Set<Role> roleSet = new HashSet<>();

        if (userRole.contains("USER")){
            roleSet.add(new Role(1L, "USER"));
            userForm.setRoles(roleSet);
        }
        if (userRole.contains("ADMIN")) {
            roleSet.add(new Role(2L, "ADMIN"));
            userForm.setRoles(roleSet);
        }
        userService.saveUser(userForm);

        return "redirect:/admin";
    }

    @GetMapping("/admin/edit/{id}")
    public String editUser(@PathVariable("id") Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("userEdit", user);
        model.addAttribute("allRoles", userService.allRoles());
        return "users";
    }

    /*@ModelAttribute("userEdit") User user,*/
    @PostMapping("/admin/edit")
    public String editUser(
            @RequestParam(value = "idEdit", required = false) Long id,
                           @RequestParam(value = "firstNameEdit", required = false) String firstNameEdit,
                           @RequestParam(value = "lastNameEdit", required = false) String lastNameEdit,
                           @RequestParam(value = "ageEdit", required = false) int ageEdit,
                           @RequestParam(value = "emailEdit", required = false) String emailEdit,
                           @RequestParam(value = "passwordEdit", required = false) String passwordEdit,
                           @RequestParam(value = "editRole", required = false) String editRole,
                           Model model) {

        model.addAttribute("allRoles", userService.allRoles());

        User user = userService.findById(id);

        user.setFirstName(firstNameEdit);

        user.setLastName(lastNameEdit);

        user.setAge(ageEdit);

        user.setEmail(emailEdit);

        if (passwordEdit == null){
            user.setPassword(user.getPassword());
        } else {
            user.setPassword(passwordEdit);
        }


        Set<Role> roleSet = new HashSet<>();
        if (editRole.contains("USER")){
            roleSet.add(new Role(1L, "USER"));
            user.setRoles(roleSet);
        }
        if (editRole.contains("ADMIN")) {
            roleSet.add(new Role(2L, "ADMIN"));
            user.setRoles(roleSet);
        }
        userService.edit(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/gt/{userId}")
    public String gtUser(@PathVariable("userId") Long userId, Model model) {
        model.addAttribute("allUsers", userService.usergtList(userId));
        return "users";
    }

    @GetMapping("/admin/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id, Model model) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
