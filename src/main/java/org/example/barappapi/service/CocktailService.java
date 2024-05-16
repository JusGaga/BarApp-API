package org.example.barappapi.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.barappapi.dto.cocktail.CreateCocktailDto;
import org.example.barappapi.dto.cocktail.UpdateCocktailDto;
import org.example.barappapi.model.Barmaker;
import org.example.barappapi.model.Category;
import org.example.barappapi.model.Cocktail;
import org.example.barappapi.model.CocktailCategory;
import org.example.barappapi.reponse.CocktailReponse;
import org.example.barappapi.repository.BarmakerRepository;
import org.example.barappapi.repository.CategoryRepository;
import org.example.barappapi.repository.CocktailCategoryRepository;
import org.example.barappapi.repository.CocktailRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CocktailService {

    private final CocktailRepository cocktailRepository;
    private final BarmakerRepository barmakerRepository;
    private final CategoryRepository categoryRepository;
    private final CocktailCategoryRepository cocktailCategoryRepository;

    public List<Cocktail> getAllCocktails() {
        return cocktailRepository.findAll();
    }

    public Cocktail getCocktailById(Long id) {
        return cocktailRepository.findById(id).orElseThrow();
    }

    public List<Cocktail> getCocktailsByBarmakerId(Long barmakerId) {
        Barmaker barmaker = barmakerRepository.findById(barmakerId)
                .orElseThrow(() -> new RuntimeException("Barmaker not found"));

        return cocktailRepository.findByBarmaker(barmaker);
    }

    @Transactional
    public CocktailReponse createCocktail(CreateCocktailDto createCocktailDto) {
        Barmaker barmaker = barmakerRepository.findById(createCocktailDto.getBarmaker()).orElseThrow(() -> new RuntimeException("Barmaker not found"));
        List<Category> categories = categoryRepository.findAllById(createCocktailDto.getCategory());

        Map<Long, Category> categoryMap = categories.stream().collect(Collectors.toMap(Category::getCategory_id, Function.identity()));

        List<Category> cocktailCategories = new ArrayList<>();


        Cocktail cocktail = Cocktail.builder()
                .name(createCocktailDto.getName())
                .ingredient(createCocktailDto.getIngredients())
                .price_s(createCocktailDto.getPrice_s())
                .price_m(createCocktailDto.getPrice_m())
                .price_l(createCocktailDto.getPrice_l())
                .barmaker(barmaker)
                .build();

        for (Long categoryId : createCocktailDto.getCategory()){
            Category category = categoryMap.get(categoryId);
            if(category != null){
                CocktailCategory cocktailCategory = CocktailCategory.builder()
                        .category(category)
                        .cocktail(cocktail)
                        .build();
                cocktailCategoryRepository.save(cocktailCategory);
                cocktailCategories.add(category);
            }
        }

        cocktail.setCategories(cocktailCategories);
        cocktailRepository.save(cocktail);

        return CocktailReponse.builder().id(cocktail.getCocktail_id()).build();
    }

    public Cocktail updateCocktail(Long id, UpdateCocktailDto updateCocktailDto) {
        Cocktail cocktail = cocktailRepository.findById(id).orElseThrow();

        if (updateCocktailDto.getName() != null) {
            cocktail.setName(updateCocktailDto.getName());
        }
        if (updateCocktailDto.getIngredient() != null) {
            cocktail.setIngredient(updateCocktailDto.getIngredient());
        }
        if (updateCocktailDto.getPrice_s() != null) {
            cocktail.setPrice_s(updateCocktailDto.getPrice_s());
        }
        if (updateCocktailDto.getPrice_m() != null) {
            cocktail.setPrice_m(updateCocktailDto.getPrice_m());
        }
        if (updateCocktailDto.getPrice_l() != null) {
            cocktail.setPrice_l(updateCocktailDto.getPrice_l());
        }

        return cocktailRepository.save(cocktail);
    }

    public void deleteCocktailById(Long id) {
        cocktailRepository.deleteById(id);
    }
}
