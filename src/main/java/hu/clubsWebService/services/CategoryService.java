package hu.clubsWebService.services;

import hu.clubsWebService.domain.Category;
import hu.clubsWebService.domain.Club;
import hu.clubsWebService.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public List<Category> getCategories() {
        return repository.findAll();
    }

    public Category getCategory(int id) {
        Optional<Category> category =  repository.findById(id);
        if(category.isPresent()) {
            return category.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    public List<Club> getClubsInCategory(int id) {
        Optional<Category> category =  repository.findById(id);
        if(category.isPresent()) {
            return category.get().getClubs();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    public Category addCategory(Category category) {
        if(isUnique(category.getName()))
            return repository.save(category);
        throw new ResponseStatusException(HttpStatus.CONFLICT);
    }

    private boolean isUnique(String name) {
        List<Category> categories = repository.findAll();
        for (Category category : categories) {
            if(category.getName().equals(name))
                return false;
        }
        return true;
    }

    public void deleteCategory(int id) {
        Optional<Category> category =  repository.findById(id);
        if(category.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public void updateCategory(int id, int agelimit) {
        Optional<Category> optionalCategory =  repository.findById(id);
        if(optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            category.setAgelimit(agelimit);
            repository.save(category);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
