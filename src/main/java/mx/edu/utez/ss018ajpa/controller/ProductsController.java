package mx.edu.utez.ss018ajpa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products/")
public class ProductsController {

    @GetMapping("")
    public String findAll(){
        return "product/home";
    }

    @GetMapping("create")
    public String create(){
        return "product/create";
    }

}
