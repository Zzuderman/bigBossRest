package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
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
    public String showUsersTable(Model model){
        model.addAttribute("users", userService.getUsersList());
        return "users";
    }

//    @GetMapping("/new")
//    public String createNewUser(@ModelAttribute("user") User user) {
//        return "new_user";
//    }

    @GetMapping("/new")
    public ModelAndView newUser() {
        User user = new User();
        ModelAndView mav = new ModelAndView("new_user");
        mav.addObject("user", user);

        List<Role> roles = (List<Role>) roleRepository.findAll();
        mav.addObject("allRoles", roles);

        return mav;
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
//    public String editUser(Model model, @PathVariable("id") Long id) {
////        model.addAttribute("user", userService.getUser(id));
//        User user = userService.getUser(id);
//        List<Role> listRoles = userService.listRoles();
//        model.addAttribute("user", user);
//        model.addAttribute("listRoles", listRoles);
//        return "edit_user";
    public ModelAndView editUser(@PathVariable(name = "id") Long id) {
        User user = userService.getUser(id);
        ModelAndView mav = new ModelAndView("edit_user");
        mav.addObject("user", user);

        List<Role> roles = (List<Role>) roleRepository.findAll();

        mav.addObject("allRoles", roles);

        return mav;

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
