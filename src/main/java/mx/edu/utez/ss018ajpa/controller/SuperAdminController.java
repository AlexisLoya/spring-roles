package mx.edu.utez.ss018ajpa.controller;

import mx.edu.utez.ss018ajpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin-super/")
public class SuperAdminController {
    @Autowired
    private UserService userService;
    @GetMapping("")
    @Secured("ROLE_SUPER_ADMINISTRATOR")
    public String findAll(Model model){
        model.addAttribute("navbar", "navbar-all");
        model.addAttribute("users", userService.findAllSuperAdmins());
        return "super/home";
    }
}
