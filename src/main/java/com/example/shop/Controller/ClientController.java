package com.example.shop.Controller;

import com.example.shop.Services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/client")
public class ClientController {
    @Autowired
    private ClientService clientService;
    @GetMapping("/products")
    public String GetProds(Model model) {
        model.addAttribute("products", clientService.GetProd());
        return "/products";
    }
    //trh'lyjpoyj
}
