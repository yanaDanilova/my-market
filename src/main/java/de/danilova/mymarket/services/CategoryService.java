package de.danilova.mymarket.services;


import de.danilova.mymarket.models.Category;
import de.danilova.mymarket.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor

public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Optional<Category> findCategoryById(Long Id){
        return categoryRepository.findById(Id);
    }

    public Optional<Category> findCategoryByTitle(String title){
        return categoryRepository.findByTitle(title);
    }
}
