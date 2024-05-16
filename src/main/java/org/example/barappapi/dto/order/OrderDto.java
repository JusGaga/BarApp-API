package org.example.barappapi.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.barappapi.dto.cocktail.CocktailOrderDto;
import org.example.barappapi.enums.order.Status;
import org.example.barappapi.model.Barmaker;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Long order_id;
    private Status status;
    private Float totalPrice;
    private Barmaker barmaker;
    private Date orderAt;
    private List<CocktailOrderDto> cocktails;
}
