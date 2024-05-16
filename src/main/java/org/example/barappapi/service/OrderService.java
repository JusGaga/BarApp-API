package org.example.barappapi.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.barappapi.dto.cocktail.CocktailOrderDto;
import org.example.barappapi.dto.order.CocktailsDetailsOrderDto;
import org.example.barappapi.dto.order.CreateOrderDto;
import org.example.barappapi.dto.order.OrderDto;
import org.example.barappapi.dto.order.UpdateOrderDto;
import org.example.barappapi.enums.cocktail.Size;
import org.example.barappapi.enums.order.Status;
import org.example.barappapi.model.Barmaker;
import org.example.barappapi.model.Cocktail;
import org.example.barappapi.model.CocktailOrder;
import org.example.barappapi.model.Orders;
import org.example.barappapi.reponse.OrderCreateResponse;
import org.example.barappapi.repository.CocktailOrderRepository;
import org.example.barappapi.repository.CocktailRepository;
import org.example.barappapi.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.example.barappapi.enums.cocktail.Size.*;

@Service
@RequiredArgsConstructor
public class    OrderService {
    private final OrderRepository orderRepository;
    private final CocktailRepository cocktailRepository;
    private final CocktailOrderRepository cocktailOrderRepository;

    public List<OrderDto> getAllOrder() {
        List<Orders> orders = orderRepository.findAll();
        List<OrderDto> orderDTOs = new ArrayList<>();

        for (Orders order : orders) {
            orderDTOs.add(getOrderDto(order));
        }

        return orderDTOs;
    }

    public OrderDto getOrderById(Long id) {
        Orders order = orderRepository.findById(id).orElseThrow();

        return getOrderDto(order);
    }


    @Transactional
    public OrderCreateResponse createOrder(CreateOrderDto createOrderDto) {
        List<Long> cocktailIds = createOrderDto.getCocktails().stream().map(CocktailsDetailsOrderDto::getCocktails_id).toList();
        List<Cocktail> cocktails = cocktailRepository.findAllById(cocktailIds);

        Map<Long, Cocktail> cocktailMap = cocktails.stream().collect(Collectors.toMap(Cocktail::getCocktail_id, Function.identity()));

        Orders order = Orders.builder()
                .status(Status.ORDERED)
                .order_at(new Date())
                .build();

        float total_price = 0;
        Barmaker barmaker = null;

        for (CocktailsDetailsOrderDto cocktailDto : createOrderDto.getCocktails()){
            Long cocktailId = cocktailDto.getCocktails_id();
            Size cocktailSize = cocktailDto.getSize();
            Cocktail cocktail = cocktailMap.get(cocktailId);

            float price = 0;
            barmaker = cocktail.getBarmaker();

            if (cocktail!=null) {
                if (cocktailSize == S){price = cocktail.getPrice_s();}
                if (cocktailSize == M){price = cocktail.getPrice_m();}
                if (cocktailSize == L){price = cocktail.getPrice_l();}
                total_price += price;
                CocktailOrder cocktailOrder = CocktailOrder.builder()
                        .order(order)
                        .cocktail(cocktail)
                        .price(price)
                        .status(Status.ORDERED)
                        .size(cocktailSize)
                        .build();
                cocktailOrderRepository.save(cocktailOrder);

            }
        }

        order.setTotal_price(total_price);
        order.setBarmaker(barmaker);
        orderRepository.save(order);

        return OrderCreateResponse.builder().order_id(order.getOrder_id()).build();
    }

    public OrderDto updateCocktailStatusOrder(Long orderId, Long cocktailId) {
        CocktailOrder cocktailOrder = cocktailOrderRepository.findById(cocktailId).orElseThrow();
        Orders order = orderRepository.findById(orderId).orElseThrow();

        if (cocktailOrder.getStatus() == Status.ORDERED) {
            cocktailOrder.setStatus(Status.PREPARATION);
        } else if (cocktailOrder.getStatus() == Status.PREPARATION) {
            cocktailOrder.setStatus(Status.FINISH);
        }

        cocktailOrderRepository.save(cocktailOrder);

        boolean allCocktailsFinished = order.getCocktails().stream()
                .map(cocktail -> cocktailOrderRepository.findById(cocktail.getCocktail_id()).orElseThrow())
                .allMatch(co -> co.getStatus() == Status.FINISH);

        if (allCocktailsFinished) {
            order.setStatus(Status.FINISH);
        } else {
            order.setStatus(Status.PREPARATION);
        }

        orderRepository.save(order);

        return getOrderDto(order);
    }


    private OrderDto getOrderDto(Orders order) {
        OrderDto orderDTO = new OrderDto();
        orderDTO.setStatus(order.getStatus());
        orderDTO.setTotalPrice(order.getTotal_price());
        orderDTO.setOrderAt(order.getOrder_at());
        orderDTO.setBarmaker(order.getBarmaker());
        orderDTO.setOrder_id(order.getOrder_id());

        List<CocktailOrder> cocktailOrders = cocktailOrderRepository.findByOrder(order);
        List<CocktailOrderDto> cocktailOrderDTOs = new ArrayList<>();

        for (CocktailOrder cocktailOrder : cocktailOrders) {
            CocktailOrderDto cocktailOrderDTO = new CocktailOrderDto();
            cocktailOrderDTO.setCocktail_id(cocktailOrder.getCocktail().getCocktail_id());
            cocktailOrderDTO.setCocktailName(cocktailOrder.getCocktail().getName());
            cocktailOrderDTO.setPrice(cocktailOrder.getPrice());
            cocktailOrderDTO.setSize(cocktailOrder.getSize());
            cocktailOrderDTO.setStatus(cocktailOrder.getStatus());
            cocktailOrderDTO.setCocktail_order_id(cocktailOrder.getCocktail_order_id());
            cocktailOrderDTOs.add(cocktailOrderDTO);
        }

        orderDTO.setCocktails(cocktailOrderDTOs);

        return orderDTO;
    }

    public void deleteOrderById(Long id) {
        orderRepository.deleteById(id);
    }


}
