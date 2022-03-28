package mx.edu.utez.ss018ajpa.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {


    @GetMapping("")
    public String redirect(){
        return "redirect:/home";
    }

    @GetMapping("home")
    public String home(Model model){
        model.addAttribute("navbar", "navbar-all");
        return "home";
    }

    @GetMapping("login")
    public String addAdmin(){
        return "login";
    }

}
