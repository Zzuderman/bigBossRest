package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/rest")
public class RestAdminController {

    private final UserService userService;

    @Autowired
    public RestAdminController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public ResponseEntity <List<User>> shouAllUsers() {
        List<User> allUsers = userService.getUsersList();
        if (allUsers.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity <User> getUser(@PathVariable("id") long id) {
        User user = userService.getUser(id);
        return new ResponseEntity<>(user,  HttpStatus.OK);
    }

    @PostMapping("/user")
    public ResponseEntity <User> addUser( @RequestBody User user) {

        userService.addUser(user);

        return new ResponseEntity<>(user,  HttpStatus.OK);
    }

    @PutMapping("/user")
    public ResponseEntity <User> updateUser (@RequestBody User user) {
        userService.editUser(user);
        return new ResponseEntity<>(user,  HttpStatus.OK);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity <String> deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
        return new ResponseEntity<> ("User with ID " + id + " was deleted", HttpStatus.OK);
    }

}
