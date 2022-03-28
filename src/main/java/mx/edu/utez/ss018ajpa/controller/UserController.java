package mx.edu.utez.ss018ajpa.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/")
public class UserController {
    @GetMapping("")
    @Secured("ROLE_SUPER")
    public String findAll(){
        return "home";
    }

    @GetMapping("create")
    public String create(){
        return "";
    }



}
