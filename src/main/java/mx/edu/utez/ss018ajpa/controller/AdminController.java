package mx.edu.utez.ss018ajpa.controller;

import mx.edu.utez.ss018ajpa.entity.User;
import mx.edu.utez.ss018ajpa.service.RoleService;
import mx.edu.utez.ss018ajpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.Optional;

@Controller
@RequestMapping("/admin/")
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @GetMapping("")
    @Secured("ROLE_ADMINISTRATOR")
    public String findAll(Model model){
        model.addAttribute("navbar", "navbar-all");
        model.addAttribute("users", userService.findAllAdmins());
        return "admin/home";
    }
    @GetMapping("create")
    @Secured("ROLE_ADMINISTRATOR")
    public String create(Model model, User user){
        model.addAttribute("navbar", "navbar-all");
        model.addAttribute("users", userService.findAllUsers());
        model.addAttribute("roles", roleService.findAll());
        return "admin/create";
    }

    @PostMapping("create")
    @Secured("ROLE_ADMINISTRATOR")
    public String save(Model model,User user, BindingResult bindingResult,
                       RedirectAttributes redirectAttributes,
                       @ModelAttribute("newUser") String newUser) {
        try {
            if (bindingResult.hasErrors()) {
                model.addAttribute("navbar", "navbar-all");
                return "admin/create";
            }


            if(userService.findOne(user.getUsername()).isEmpty()){
                user.setEnabled(true);
                user.setRoles(Arrays.asList(roleService.findOne("ROLE_ADMINISTRATOR").get()));
                userService.save(user);
                redirectAttributes.addFlashAttribute("msg_success", "guardado exitosamente");
            }else{
                userService.update(user);
                redirectAttributes.addFlashAttribute("msg_success", "editado exitosamente");

            }

        } catch (Exception e) {
        }
        return "redirect:/admin/";
    }


    @GetMapping("edit/{id}")
    @Secured("ROLE_ADMINISTRATOR")
    public String update(Model model, @PathVariable("id") String id){
        Optional<User> user = userService.findOne(id);
        if(user.isEmpty())  return "redirect:/admin/";

        /*model.addAttribute("roles", roleService.findAll());*/
        model.addAttribute("user", user.get());
        model.addAttribute("navbar", "navbar-all");

        return "admin/create";
    }


    @PostMapping("delete/{id}")
    @Secured("ROLE_ADMINISTRATOR")
    public String delete(Model model, @PathVariable("id") String id){
        userService.delete(id);
        return "redirect:/admin/";
    }
}
