package com.example.shop.Controller;

import com.example.shop.Entity.Category;
import com.example.shop.Entity.Products;
import com.example.shop.Entity.User;
import com.example.shop.Repository.ProductRepository;
import com.example.shop.Services.ProdService;
import com.example.shop.Services.ShopServices;
import com.example.shop.Services.UserService;
import jakarta.jws.soap.SOAPBinding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin/{userId}")
public class ProductController extends GlobalController{
    @Autowired
    private ShopServices shopServices;
    @Autowired
    private ProdService prodService;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserService userService;

    @GetMapping("/add")
    public String addProducts(Model model) {
        model.addAttribute("product", new Products());
        model.addAttribute("category", prodService.getAllCategories());
        return "product_add";
    }

    @PostMapping("/save")
    public String saveProd(@RequestParam("name") String name,  @RequestParam("quantity") int quantity,@RequestParam("category") Category category,
                           @RequestParam("price") double price ,@RequestParam("desc")String desc, @RequestParam("image") MultipartFile image) throws IOException {

        Products pr = new Products();
        pr.setName(name);
        pr.setPrice(price);
        pr.setQuantity(quantity);
        pr.setCategory(category);
        pr.setDesc(desc);
        pr.setImageType(image.getContentType());
        prodService.saveAllProd(pr, image);
        return "redirect:/admin/{userId}/add";
    }
    @GetMapping("/product_page")
    public String showProducts( Model model) {
        List<Products> products = prodService.getAllProducts();
        model.addAttribute("products", products);
        return "product_page";
    }
    @GetMapping("/admin")
    public String AdminPage(){
        return "admin";
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getImageByProductId(@PathVariable long id){
        Products product = prodService.getProductById(id);
        byte[] image = product.getImage();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "image/*")
                .body(image);

    }

    @PostMapping("/deleteProdById/{id}")
    public String deleteByProdId(@PathVariable Long id){
        prodService.deleteByProdId(id);
        return "redirect:/admin/{userId}/product_page";
    }
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Products product = prodService.getProductById(id);
        model.addAttribute("product", product);
        return "Update";  // This refers to the edit-product.html file
    }

    @PostMapping("/postEdit/{id}")
    public String updateProduct(
            @PathVariable Long id,
            @ModelAttribute Products product,
            RedirectAttributes redirectAttributes) {
        prodService.updateProduct(id, product);
        return "redirect:/admin/{userId}/product_page";
    }

    @GetMapping("/sortBy")
    public String sortProducts(
            @RequestParam(value = "order", defaultValue = "price_low_to_high") String order,
            Model model) {
        List<Products> products = prodService.sortProducts(order);
        model.addAttribute("products", products);
        model.addAttribute("order", order);
        return "product_page";
    }

    @GetMapping("/getUser")
    public String getUser(Model model){
        List<User> users = userService.findAll();
        model.addAttribute("user", users);
        return "user_page";
    }
    @PostMapping("/update")
    public String updateUser(@PathVariable Long userId,
                             User user,
                             @RequestParam("role")String role){
        userService.updateUser(userId, user, role);
        return "redirect:/admin/{userId}/getUser";
    }
}

