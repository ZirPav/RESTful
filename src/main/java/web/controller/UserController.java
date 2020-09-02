package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;
import web.service.UserServiceImpl;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

	private UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}


	@GetMapping("/registration")
	public String registration(Model model) {
		model.addAttribute("userForm", new User());

		return "registration";
	}

	@PostMapping("/registration")
	public String addUser(@ModelAttribute("userForm") @Valid User userForm, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			return "registration";
		}
		if (!userForm.getPassword().equals(userForm.getConfirmPassword())){
			model.addAttribute("passwordError", "Пароли не совпадают");
			return "registration";
		}
		if (!userService.saveUser(userForm)){
			model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
			return "registration";
		}

		return "redirect:/";
	}


    /*@GetMapping("/registration")
    public String registration(Model model){
		model.addAttribute("userForm", new User());
		return "registration";
	}

		@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		List<String> messages = new ArrayList<>();
		messages.add("Hello!");
		messages.add("I'm Spring MVC-SECURITY application");
		messages.add("5.2.0 version by sep'19 ");
		model.addAttribute("messages", messages);
		return "hello";
	}

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }


	@GetMapping("/")
	public String getUsers(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("listUsers", userService.allUsers());
		return "users";
	}

	@GetMapping("/add")
	public String addUser(Model model) {
		model.addAttribute("addUser", new User());
		return "userForm";
	}

	@PostMapping("/add")
	public String addUser(@ModelAttribute("addUser") User user) {
		this.userService.saveUser(user);
		return "redirect:/";
	}

	@GetMapping("/edit/{id}")
	public String editUser(@PathVariable("id") Long id, Model model) {
		User user = userService.findById(id);
		model.addAttribute("editUser", user);
		return "userEdit";
	}

	@PostMapping("/edit/{id}")
	public String editUser(@ModelAttribute("editUser") User user) {
		userService.edit(user);
		return "redirect:/";
	}

	@GetMapping("/delete/{id}")
	public String removeUser(@PathVariable("id") Long id) {
		this.userService.deleteUser(id);
		return "redirect:/";
	}

	@GetMapping("/userdata/{id}")
	public String userData(@PathVariable("id") Long id, Model model) {
		model.addAttribute("book", userService.findById(id));
		return "userData";
	}*/
}