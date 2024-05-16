package org.example.barappapi.controller;

import lombok.RequiredArgsConstructor;
import org.example.barappapi.dto.categorie.CreateCategoryDto;
import org.example.barappapi.dto.categorie.UpdateCategoryDto;
import org.example.barappapi.model.Category;
import org.example.barappapi.reponse.CategoryResponse;
import org.example.barappapi.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("category")
@RequiredArgsConstructor
public class CategoryController {
    @Autowired
    private final CategoryService categoryService;

    @GetMapping("")
    public ResponseEntity<List<Category>> getCategory() {
        try {
            return ResponseEntity.ok(categoryService.getAllCategory());
        }catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(categoryService.getCategoryById(id));
        }catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("create")
    public ResponseEntity<CategoryResponse> createCategory(@RequestBody CreateCategoryDto createCategoryDto) {
        try {
            return ResponseEntity.ok(categoryService.createCategory(createCategoryDto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody UpdateCategoryDto updateCategoryDto) {
        try {
            return ResponseEntity.ok(categoryService.updateCategory(id, updateCategoryDto));
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Category> deleteCategory(@PathVariable Long id) {
        try{
            categoryService.deleteCategoryById(id);
            return ResponseEntity.ok().build();
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
