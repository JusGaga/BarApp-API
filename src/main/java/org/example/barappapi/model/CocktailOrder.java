package org.example.barappapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.barappapi.enums.cocktail.Size;
import org.example.barappapi.enums.order.Status;

@Data
@Builder
@Table(name = "cocktail_order")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class CocktailOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cocktail_order_id;

    private Status status;
    private Size size;
    private Float price;

    @ManyToOne
    @JoinColumn(name = "cocktail_id")
    private Cocktail cocktail;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private Orders order;

}
