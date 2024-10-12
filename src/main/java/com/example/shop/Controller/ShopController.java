package com.example.shop.Controller;


import com.example.shop.Entity.Products;
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

@Controller
public class ShopController {
    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private ShopServices shopServices;

   /* @GetMapping("/upload")
    public String showUploadPage() {
        return "upload";
    }


    @PostMapping("/upload")
    public String uploadImage(@RequestParam("file") MultipartFile file, Model model) throws IOException {
        shopServices.saveImage(file);

        return "redirect:/upload";
    }


    @GetMapping("/view/{id}")
    public String viewImage(@PathVariable Long id, Model model) {
        model.addAttribute("imageId", id);
        return "view";
    }


    @GetMapping("/view/data/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        Products image = shopServices.getImageById(id);

        if (image != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity<>(image.getImage(), headers, HttpStatus.OK);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }*/
    @GetMapping("/AddProducts")
    public String addProducts(Model model) {
        model.addAttribute("product", new Products());
        return "addProducts";
    }

    @PostMapping("/SAP")
    public String addProduct(@ModelAttribute("product") Products product, Model model/*,@RequestParam("file") MultipartFile file*/) {
        shopServices.saveProds(product);
     //   shopServices.saveImage(file);
        return "redirect:AddProducts";
    }
}
