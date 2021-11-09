package hu.clubsWebService.controllers;

import hu.clubsWebService.domain.Category;
import hu.clubsWebService.domain.Club;
import hu.clubsWebService.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    private CategoryService service;

    @Autowired
    public void setService(CategoryService service) {
        this.service = service;
    }

    @GetMapping("/categories")
    public List<Category> getCategories(){
        return service.getCategories();
    }

    @GetMapping("/categories/{id}")
    public Category getCategory(@PathVariable("id") int id){
        return service.getCategory(id);
    }

    @GetMapping("/categories/{id}/clubs")
    public List<Club> getClubsInCategory(@PathVariable("id") int id){
        return service.getClubsInCategory(id);
    }

    @PostMapping("/categories")
    @ResponseStatus(HttpStatus.CREATED)
    public Category addCategory(@RequestBody Category category){
        return service.addCategory(category);
    }

    @DeleteMapping("categories/{id}")
    public void deleteCategory(@PathVariable("id") int id){
        service.deleteCategory(id);
    }

    @PatchMapping("categories/{id}/{agelimit}")
    public void updateCategory(@PathVariable("id") int id, @PathVariable("agelimit") int agelimit){
        service.updateCategory(id, agelimit);
    }


}
