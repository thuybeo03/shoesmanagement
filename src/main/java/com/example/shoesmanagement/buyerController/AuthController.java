package com.example.shoesmanagement.buyerController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/buyer")
public class AuthController {
    @GetMapping("/login")
    public String getFormBuyerLogin(){
        return "online/login";
    }

}
