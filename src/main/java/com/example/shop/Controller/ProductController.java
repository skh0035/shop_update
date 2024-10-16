package com.example.shop.Controller;
import com.example.shop.Entity.Products;
import com.example.shop.Repository.ProdRepo;
import com.example.shop.Repository.ShopRepository;
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
    private ProdRepo prodRepo;

   /* @GetMapping("/AddProducts")
    public String addProducts(Model model) {
        model.addAttribute("product", new Products());
        return "product_add";
    }*/

    @PostMapping("/SAP")
    public String addProduct(@ModelAttribute("product") Products product, Model model/*,@RequestParam("file") MultipartFile file*/) {
        shopServices.saveProd(product);
        //   shopServices.saveImage(file);
        return "redirect:AddProducts";
    }
    @GetMapping("/admin")
    public String showProducts( Model model) {
        List<Products> products = shopServices.getAllProducts();
        model.addAttribute("products", products);
        return "admin";
    }
/*    @GetMapping("/add")
    public String showAddProducts(Model model) {
        model.addAttribute("product", new Products());
        return "product_add";
    }*/

    @PostMapping("/save")
    public String saveProduct(@RequestParam("name") String name,@RequestParam("price") double price,@RequestParam("quantity") int quantity,@RequestParam("file") MultipartFile file) throws IOException {
       Products pr = new Products();
        pr.setName(name);
        pr.setPrice(price);
        pr.setQuantity(quantity);

       pr.setImage(file.getBytes());
        shopServices.saveProd(pr);

        return "product_add";
    }
    @GetMapping("/add")
    public String AddProduct(Model model){
        model.addAttribute("product", new Products());
        return "product_add";
    }
}
