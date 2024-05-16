package org.example.barappapi.controller;

import lombok.RequiredArgsConstructor;
import org.example.barappapi.dto.order.CreateOrderDto;
import org.example.barappapi.dto.order.OrderDto;
import org.example.barappapi.model.Orders;
import org.example.barappapi.reponse.OrderCreateResponse;
import org.example.barappapi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("order")
@RequiredArgsConstructor
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("")
    public ResponseEntity<List<OrderDto>> getOrders() {
        try {
            return ResponseEntity.ok(orderService.getAllOrder());
        }catch (Exception e ){
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(orderService.getOrderById(id));
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("create")
    public ResponseEntity<OrderCreateResponse> createOrder(@RequestBody CreateOrderDto createOrderDto) {
        try {
            return ResponseEntity.ok(orderService.createOrder(createOrderDto));
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("update/{id}/cocktail/{cocktailId}")
    public ResponseEntity<OrderDto> updateCocktailStatusOrder(@PathVariable Long id, @PathVariable Long cocktailId){
        try {
            return ResponseEntity.ok(orderService.updateCocktailStatusOrder(id,cocktailId));
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Orders> deleteOrder(@PathVariable Long id) {
        try {
            orderService.deleteOrderById(id);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}
