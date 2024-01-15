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

    private final UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public String showUsersTable(@AuthenticationPrincipal UserDetails userDetails, Model model){
        model.addAttribute("users", userService.getUsersList());
        model.addAttribute("info_user", userService.findByEmail(userDetails.getUsername()));
        return "users";
    }


    @GetMapping("/new")
    public String createNewUser(@AuthenticationPrincipal UserDetails userDetails,Model model,@ModelAttribute("user") User user, @RequestParam(name = "role", defaultValue = "0") Long[] id) {
        model.addAttribute("allRoles", roleRepository.findAll());
        model.addAttribute("info_user", userService.findByEmail(userDetails.getUsername()));
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
    public String editUser(@PathVariable(name = "id") Long id, @AuthenticationPrincipal UserDetails userDetails, Model model) {
        User user = userService.getUser(id);

        model.addAttribute("users", userService.getUsersList());
        model.addAttribute("user", user);
        model.addAttribute("allRoles", roleRepository.findAll());
        User userInfo = userService.findByEmail(userDetails.getUsername());
        model.addAttribute("info_user", userInfo);

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
