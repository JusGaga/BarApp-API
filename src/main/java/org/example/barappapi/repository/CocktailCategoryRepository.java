package org.example.barappapi.repository;

import org.example.barappapi.model.CocktailCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CocktailCategoryRepository extends JpaRepository<CocktailCategory, Long> {
}
