package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.validation.Valid;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/rest")
public class RestController {

    private final UserService userService;

    @Autowired
    public RestController(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping
    public List<User> shouAllUsers() {
        List<User> allUsers = userService.getUsersList();
        return allUsers;
    }

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable long id) {
        User user = userService.getUser(id);
        return user;
    }

    @PostMapping("/user")
    public User addUser( @RequestBody User user) {

        userService.addUser(user);

        return user;
    }

    @PutMapping("/user")
    public User updateUser (@RequestBody User user) {
        userService.editUser(user);
        return user;
    }

    @DeleteMapping("/user/{id}")
    public  String deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
        return "User with ID " + id + " was deleted";
    }

}
