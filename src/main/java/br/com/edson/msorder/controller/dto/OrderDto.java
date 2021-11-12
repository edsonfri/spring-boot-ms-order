package br.com.edson.msorder.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.edson.msorder.modelo.Order;

public class OrderDto {
    private Integer id;
    private String description;
    private String name;
    private Double total;
    private String status;
    
    public OrderDto(Order order) {
        this.id = order.getId();
        this.description = order.getDescription();
        this.name = order.getName();
        this.total = order.getTotal();
        this.status = order.getStatus().toString();
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public Double getTotal() {
        return total;
    }

    public String getStatus() {
        return status;
    }

    public static List<OrderDto> converter(List<Order> orders) {
        return orders.stream().map(OrderDto::new).collect(Collectors.toList());
    }

}
