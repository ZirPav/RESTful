package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import javax.validation.Valid;

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
        return "userAdd";
    }

    @PostMapping("/admin/add")
    public String addUser(@ModelAttribute("addUser") @Valid User userForm, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "userAdd";
        }
        if (!userForm.getPassword().equals(userForm.getConfirmPassword())){
            model.addAttribute("passwordError", "Пароли не совпадают");
            return "userAdd";
        }
        if (!userService.saveUser(userForm)){
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "userAdd";
        }

        return "redirect:/admin";
    }

    @GetMapping("/admin/edit/{id}")
    public String editUser(@PathVariable("id") Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("userEdit", user);
        return "userEdit";
    }

    @PostMapping("/admin/edit/{id}")
    public String editUser(@ModelAttribute("userEdit") @Valid User user, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "userEdit";
        }
        if (!user.getPassword().equals(user.getConfirmPassword())){
            model.addAttribute("passwordError", "Пароли не совпадают");
            return "userEdit";
        }
        if (!userService.edit(user)){
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "userEdit";
        }
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
