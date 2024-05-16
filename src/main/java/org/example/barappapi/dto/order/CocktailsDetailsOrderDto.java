package org.example.barappapi.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.barappapi.enums.cocktail.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CocktailsDetailsOrderDto {
    private Long cocktails_id;
    private Size size;
}
