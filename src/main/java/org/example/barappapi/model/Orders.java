package org.example.barappapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.example.barappapi.enums.order.Status;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;

@Data
@Builder
@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "barmaker")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long order_id;
    private Status status;
    private Float total_price;

    @CreationTimestamp
    private Date order_at;

    @ManyToOne
    @JoinColumn(name = "barmaker_id")
    private Barmaker barmaker;

    @ManyToMany
    @JoinTable(
            name = "cocktail_order",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "cocktail_id")
    )
    private List<Cocktail> cocktails;
}
