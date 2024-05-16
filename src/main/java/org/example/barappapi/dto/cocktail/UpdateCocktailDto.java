package org.example.barappapi.dto.cocktail;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCocktailDto {
    @JsonProperty(required = false)
    private String name;

    @JsonProperty(required = false)
    private String ingredient;

    @JsonProperty(required = false)
    private Float price_s;

    @JsonProperty(required = false)
    private Float price_m;

    @JsonProperty(required = false)
    private Float price_l;
}
