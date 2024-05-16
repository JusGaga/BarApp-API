package org.example.barappapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Builder
@Entity
@Table(name = "category")
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long category_id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "barmaker_id")
    @JsonIgnore
    private Barmaker barmaker;

    @ManyToMany
    @JoinTable(
            name = "cocktail_category",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "cocktail_id")
    )
    @JsonIgnore
    private List<Cocktail> cocktails;

}
