package com.example.shoesmanagement.buyerController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/buyer")
public class ShopController {
    @GetMapping("/shop")
    private String getShopBuyer(){
            return "online/shop";
    }

}
