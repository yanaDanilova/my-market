package de.danilova.mymarket.controllers;


import de.danilova.mymarket.error_handling.ResourceNotFoundException;
import de.danilova.mymarket.models.Category;
import de.danilova.mymarket.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/{id}")
    private Category getCategoryById(@PathVariable Long id){
        return categoryService.findCategoryById(id).orElseThrow(()-> new ResourceNotFoundException("Categoty doesn' t exist"));
    }


}
