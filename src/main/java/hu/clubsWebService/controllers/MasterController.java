package hu.clubsWebService.controllers;

import hu.clubsWebService.domain.Category;
import hu.clubsWebService.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MasterController {

    private CategoryService categoryService;

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    public String home(){
        return "ClubService";
    }

    @GetMapping("/categories")
    public List<Category> getCategories(){
        return categoryService.getCategories();
    }
}
