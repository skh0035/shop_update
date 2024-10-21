package com.example.shop.Controller;

import com.example.shop.Entity.Category;
import com.example.shop.Repository.CategoryRepository;
import com.example.shop.Services.ProdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProdService prodService;
    @GetMapping("/addCategory")
    public String addCategory(Model model) {
        model.addAttribute("category", new Category());
        return "Category_add";
    }
    @PostMapping("/saveCategory")
    public String saveCategory(Category category) {
        categoryRepository.save(category);
        return "redirect:/addCategory";
    }

    @GetMapping("/getCategory")
    public String getCategory(Model model) {
        List<Category> categories = prodService.getAllCategories();
        model.addAttribute("categories", categories);
        return "/product_page";
    }
}
