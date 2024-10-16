package com.example.shop.Controller;



import com.example.shop.Entity.Products;
import com.example.shop.Repository.ProductRepository;
import com.example.shop.Repository.ShopRepository;
import com.example.shop.Services.ProdService;
import com.example.shop.Services.ShopServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;



@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private ShopServices shopServices;

    @Autowired

    private ProdService prodService;


    @Autowired
    private ProductRepository productRepository;


    @GetMapping("/add")
    public String addProducts(Model model) {


        model.addAttribute("product", new Products());
        return "prodadd";
    }

    @PostMapping("/save")
    public String saveProd(@RequestParam("name") String name,  @RequestParam("quantity") int quantity,
                           @RequestParam("price") double price , @RequestParam("image") MultipartFile image) throws IOException {
        Products pr = new Products();
        pr.setName(name);
        pr.setPrice(price);
        pr.setQuantity(quantity);
        pr.setImageType(image.getContentType());



        prodService.saveAllProd(pr, image);

        return "redirect:/product/add";
    }

//    @PostMapping("/SAP")
////    public String addProduct(@ModelAttribute("product") Products product, Model m,@RequestParam("file") MultipartFile file) {
//        shopServices.saveProds(product);
//        //   shopServices.saveImage(file);
//        return "redirect:AddProducts";
//    }
    @GetMapping("/admin")
    public String showProducts( Model model) {
        List<Products> products = shopServices.getAllProducts();
        model.addAttribute("products", products);
        return "admin";
    }

    @GetMapping("/show")
    public String showAllPro(Model model){


        model.addAttribute("pro", prodService.findAllPro());

        return "control";
    }

}

