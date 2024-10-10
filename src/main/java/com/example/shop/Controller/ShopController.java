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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class ShopController {
    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private ShopServices shopServices;

    @GetMapping("/upload")
    public String showUploadPage() {
        return "upload";  // Thymeleaf will resolve this to src/main/resources/templates/upload.html
    }

    // Handle image upload
    @PostMapping("/upload")
    public String uploadImage(@RequestParam("file") MultipartFile file, Model model) throws IOException {
        shopServices.saveImage(file);
        // Assuming image ID is 1 for demonstration purposes
        // You can redirect to the page showing the image, passing the ID of the uploaded image
        return "redirect:/upload";  // Replace '1' with actual ID from the database if needed
    }

    // Serve the view image page
    @GetMapping("/view/{id}")
    public String viewImage(@PathVariable Long id, Model model) {
        model.addAttribute("imageId", id);
        return "view";  // Thymeleaf will resolve this to src/main/resources/templates/view.html
    }

    // Return image binary data (for use with <img> tag)
    @GetMapping("/view/data/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        Products image = shopServices.getImageById(id);

        if (image != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);  // Set appropriate content type
            return new ResponseEntity<>(image.getImage(), headers, HttpStatus.OK);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
