package org.example.barappapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Builder
@Entity
@Table(name = "cocktail")
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"orders","barmaker","categories"})
public class Cocktail  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cocktail_id;
    private String name;
    private String ingredient;
    private Float price_s;
    private Float price_m;
    private Float price_l;

    @ManyToOne
    @JoinColumn(name = "barmaker_id")
    private Barmaker barmaker;

    @ManyToMany(mappedBy = "cocktails")
    private List<Category> categories;

    @ManyToMany(mappedBy = "cocktails")
    @JsonIgnore
    private List<Orders> orders;

}
