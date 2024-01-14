package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.service.UserServiceImp;

import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserServiceImp userService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    public AdminController(UserServiceImp userService) {
        this.userService = userService;
    }


    @GetMapping()
    public String showUsersTable(@AuthenticationPrincipal UserDetails userDetails, Model model){
        model.addAttribute("users", userService.getUsersList());
        User user = userService.findByEmail(userDetails.getUsername());
        model.addAttribute("info_user", user);
        return "users";
    }


    @GetMapping("/new")
    public String createNewUser(@ModelAttribute("user") User user, @RequestParam(name = "role", defaultValue = "0") Long[] id) {
        return "new_user";
    }


    @PostMapping("/")
    public String addUser(@ModelAttribute("user")  User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "new_user";
        }
        userService.addUser(user);

        return "redirect:/admin";
    }

    @GetMapping("/{id}/edit")
    public String editUser(@PathVariable(name = "id") Long id, Model model) {
        User user = userService.getUser(id);

        model.addAttribute("users", userService.getUsersList());
        model.addAttribute("user", user);
        model.addAttribute("allRoles", roleRepository.findAll());

        return "edit_user";

    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit_user";
        }

        userService.editUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

}
