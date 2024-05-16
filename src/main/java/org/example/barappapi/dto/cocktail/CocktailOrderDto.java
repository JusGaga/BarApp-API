package org.example.barappapi.dto.cocktail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.barappapi.enums.cocktail.Size;
import org.example.barappapi.enums.order.Status;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CocktailOrderDto {
    private Long cocktail_order_id;
    private Long cocktail_id;
    private Status status;
    private String cocktailName;
    private Float price;
    private Size size;
}
