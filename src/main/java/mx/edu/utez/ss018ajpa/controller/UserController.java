package mx.edu.utez.ss018ajpa.controller;

import mx.edu.utez.ss018ajpa.entity.User;
import mx.edu.utez.ss018ajpa.service.RoleService;
import mx.edu.utez.ss018ajpa.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;

@Controller
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("")
    @Secured("ROLE_USER")
    public String findAll(Model model){
        model.addAttribute("navbar", "navbar-all");
        model.addAttribute("users", userService.findAllUsers());
        // Logger levels DESC
        logger.trace("TRACE level");
        logger.debug("DEBUG level");
        logger.info("INFO level");
        logger.warn("WARN level");
        logger.error("ERROR level");

        return "user/home";
    }

    @GetMapping("create")
    @Secured("ROLE_USER")
    public String create(Model model, User user){
        model.addAttribute("navbar", "navbar-all");
        model.addAttribute("users", userService.findAllUsers());
        model.addAttribute("roles", roleService.findAll());
        return "user/create";
    }

    @PostMapping("create")
    @Secured("ROLE_USER")
    public String save(Model model,User user, BindingResult bindingResult,
                       RedirectAttributes redirectAttributes,
                       @ModelAttribute("newUser") String newUser) {
        try {
            if (bindingResult.hasErrors()) {
                model.addAttribute("navbar", "navbar-all");
                return "user/create";
            }
            if(userService.findOne(user.getUsername()).isEmpty()){
                user.setEnabled(true);
                userService.save(user);
                redirectAttributes.addFlashAttribute("msg_success", "guardado exitosamente");
            }else{
                User oldUser = userService.findOne(user.getUsername()).get();
                if(user.getPassword().isEmpty()){
                    user.setPassword(oldUser.getPassword());
                }

                user.setEnabled(true);
                userService.update(user);
                redirectAttributes.addFlashAttribute("msg_success", "editado exitosamente");
            }

        } catch (Exception e) {
        }
        return "redirect:/user/";
    }


    @GetMapping("edit/{id}")
    @Secured("ROLE_USER")
    public String update(Model model, @PathVariable("id") String id){
        Optional<User> user = userService.findOne(id);
        if(user.isEmpty())  return "redirect:/user/";

        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("user", user.get());
        model.addAttribute("navbar", "navbar-all");

        return "user/create";
    }


    @PostMapping("delete/{id}")
    @Secured("ROLE_USER")
    public String delete(Model model, @PathVariable("id") String id){
        userService.delete(id);
        return "redirect:/user/";
    }


}
