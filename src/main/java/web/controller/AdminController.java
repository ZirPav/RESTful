package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.model.Role;
import web.model.User;
import web.service.UserService;

import javax.validation.Valid;
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
        return "users";
    }

    @GetMapping("/admin/add")
    public String addUser(Model model) {
        model.addAttribute("addUser", new User());
        model.addAttribute("allRoles", userService.allRoles());
        return "userAdd";
    }

    @PostMapping("/admin/add")
    public String addUser(@ModelAttribute("addUser") @Valid User userForm,
                          @RequestParam(value = "addRole", required = false)  String userRole,
                          BindingResult bindingResult, Model model) {
        model.addAttribute("allRoles", userService.allRoles());
        if (bindingResult.hasErrors()) {
            return "userAdd";
        }
        if (!userForm.getPassword().equals(userForm.getConfirmPassword())){
            model.addAttribute("passwordError", "Пароли не совпадают");
            return "userAdd";
        }

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
        return "userEdit";
    }

    @PostMapping("/admin/edit/{id}")
    public String editUser(@ModelAttribute("userEdit") @Valid User user,
                           @RequestParam(value = "editRole", required = false) String editRole,
                           BindingResult bindingResult, Model model) {
        model.addAttribute("allRoles", userService.allRoles());
        if (bindingResult.hasErrors()) {
            return "userEdit";
        }
        if (!user.getPassword().equals(user.getConfirmPassword())){
            model.addAttribute("passwordError", "Пароли не совпадают");
            return "userEdit";
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
