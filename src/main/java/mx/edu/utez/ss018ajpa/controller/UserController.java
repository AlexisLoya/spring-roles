package mx.edu.utez.ss018ajpa.controller;

import mx.edu.utez.ss018ajpa.entity.User;
import mx.edu.utez.ss018ajpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    @Secured("ROLE_USER")
    public String findAll(Model model){
        model.addAttribute("navbar", "navbar-all");
        model.addAttribute("users", userService.findAllUsers());
        return "user/home";
    }
    @GetMapping("create")
    @Secured("ROLE_USER")
    public String create(Model model, User user){
        model.addAttribute("navbar", "navbar-all");
        model.addAttribute("users", userService.findAllUsers());
        return "user/create";
    }



}
