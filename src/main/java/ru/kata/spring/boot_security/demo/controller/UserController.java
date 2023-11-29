package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserServiceImp;

import java.security.Principal;

@Controller
public class UserController {
    private UserServiceImp userServiceImp;

    @Autowired
    public void setUserServiceImp(UserServiceImp userServiceImp) {
        this.userServiceImp = userServiceImp;
    }

//    @GetMapping("/user")
//    @ResponseBody
//    public String userPage(Principal principal) {
//        User user = userServiceImp.findByUsername(principal.getName());
//        return "User info: " + user.getUsername()+", " + user.getEmail();
//    }

//    @GetMapping("/user")
//    public String showUsersTable(Model model, Principal principal) {
//        User user = userServiceImp.findByEmail(principal.getName());
//        model.addAttribute("one_user", user);
//        return "user";
//    }

    @GetMapping("/user")
    public String getUser(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        User user = userServiceImp.findByEmail(userDetails.getUsername());
        model.addAttribute("one_user", user);
        return "user";
    }



//    public String getDetails(Authentication authentication, Principal principal) {
//        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
//        List<String> roles = userDetails.getAuthorities().stream()
//                .map(item -> item.getAuthority())
//                .collect(Collectors.toList());
//
//        return userDetails.getEmail();
//    }


}
