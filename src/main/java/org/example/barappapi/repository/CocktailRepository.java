package org.example.barappapi.repository;

import org.example.barappapi.model.Barmaker;
import org.example.barappapi.model.Cocktail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CocktailRepository extends JpaRepository<Cocktail, Long> {
    List<Cocktail> findByBarmaker(Barmaker barmaker);
}
