package org.example.barappapi.dto.cocktail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateCocktailDto {
    private String name;
    private String ingredients;
    private Float price_s;
    private Float price_m;
    private Float price_l;

    private Long barmaker;
    private List<Long> category;
}
