package org.example.barappapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@Table(name = "cocktail_category")
@NoArgsConstructor
@AllArgsConstructor
public class CocktailCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cocktail_category_id;

    @ManyToOne
    @JoinColumn(name = "cocktail_id")
    private Cocktail cocktail;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}
