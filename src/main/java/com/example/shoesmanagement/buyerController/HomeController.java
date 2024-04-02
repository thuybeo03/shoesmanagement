package com.example.shoesmanagement.buyerController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/buyer")
public class HomeController {
    @RequestMapping(value = {"", "/", "/home"})
    public String hone(){
        return "online/index";
    }
}
