package org.example.barappapi.service;

import lombok.RequiredArgsConstructor;
import org.example.barappapi.dto.categorie.CreateCategoryDto;
import org.example.barappapi.dto.categorie.UpdateCategoryDto;
import org.example.barappapi.model.Barmaker;
import org.example.barappapi.model.Category;
import org.example.barappapi.reponse.CategoryResponse;
import org.example.barappapi.repository.BarmakerRepository;
import org.example.barappapi.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final BarmakerRepository barmakerRepository;

    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id) {return categoryRepository.findById(id).orElseThrow();}

    public CategoryResponse createCategory(CreateCategoryDto createCategoryDto) {
        Barmaker barmaker = barmakerRepository.findById(createCategoryDto.getBarmaker()).orElseThrow(() -> new RuntimeException("Barmaker not found"));

        Category category = Category.builder()
                .name(createCategoryDto.getName())
                .barmaker(barmaker)
                .build();

        categoryRepository.save(category);

        return CategoryResponse.builder().id(category.getCategory_id()).build();
    }

    public Category updateCategory(Long id, UpdateCategoryDto updateCategoryDto) {
        Category category = categoryRepository.findById(id).orElseThrow();
        if(updateCategoryDto.getName() != null) {
            category.setName(updateCategoryDto.getName());
        }
        categoryRepository.save(category);
        return Category.builder().name(category.getName()).build();
    }

    public void deleteCategoryById(Long id) {
        categoryRepository.deleteById(id);
    }

}
