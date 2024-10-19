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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ShopServices shopServices;
    @Autowired
    private ProdService prodService;
    @Autowired
    private ProductRepository productRepository;
    @GetMapping("/add")
    public String addProducts(Model model) {
        model.addAttribute("product", new Products());
        return "product_add";
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
    @GetMapping("/admin")
    public String showProducts( Model model) {
        List<Products> products = prodService.getAllProducts();
        model.addAttribute("products", products);
        return "admin";
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getImageByProductId(@PathVariable long id){
        Products product = prodService.getProdByid(id);
        byte[] image = product.getImage();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "image/*")
                .body(image);

    }

    @GetMapping("/delete/{id}")
    public String deleteByProdId(@PathVariable Long id){
        prodService.deleteByProdId(id);
        return "redirect:/product/show";
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
        return "redirect:/product/admin";
    }

    @GetMapping("/sortByPrice")
    public String getProducts(@RequestParam(value = "order", defaultValue = "low_to_high") String order, Model model) {
        List<Products> products = prodService.getProducts(order);
        model.addAttribute("products", products);
        model.addAttribute("order", order);
        return "admin";
    }
}

