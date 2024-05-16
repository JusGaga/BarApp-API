package org.example.barappapi.repository;

import org.example.barappapi.model.CocktailOrder;
import org.example.barappapi.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CocktailOrderRepository extends JpaRepository<CocktailOrder, Long> {
    List<CocktailOrder> findByOrder(Orders order);
}
