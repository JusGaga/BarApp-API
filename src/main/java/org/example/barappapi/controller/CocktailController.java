package org.example.barappapi.controller;


import lombok.RequiredArgsConstructor;
import org.example.barappapi.dto.cocktail.CreateCocktailDto;
import org.example.barappapi.dto.cocktail.UpdateCocktailDto;
import org.example.barappapi.model.Cocktail;
import org.example.barappapi.reponse.CocktailReponse;
import org.example.barappapi.service.CocktailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cocktail")
@RequiredArgsConstructor
public class CocktailController {

    @Autowired
    private final CocktailService cocktailService;

    @GetMapping("")
    public ResponseEntity<List<Cocktail>> getAllCocktails() {
        try {
            return ResponseEntity.ok( cocktailService.getAllCocktails());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Cocktail> getCocktailById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(cocktailService.getCocktailById(id));
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/barmaker/{id}")
    public ResponseEntity<List<Cocktail>> getCocktailsByBarmakerId(@PathVariable Long id) {
        try{
            return ResponseEntity.ok(cocktailService.getCocktailsByBarmakerId(id));
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("create")
    public ResponseEntity<CocktailReponse> createCocktail(@RequestBody CreateCocktailDto createCocktailDto) {
        try {
            return ResponseEntity.ok(cocktailService.createCocktail(createCocktailDto));
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Cocktail> updateCocktail(@PathVariable Long id, @RequestBody UpdateCocktailDto updateCocktailDto) {
        try {
            return ResponseEntity.ok(cocktailService.updateCocktail(id, updateCocktailDto));
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteCocktail(@PathVariable Long id) {
        try {
            cocktailService.deleteCocktailById(id);
            return ResponseEntity.ok().build();
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
