package br.com.edson.msorder.controller.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;

import br.com.edson.msorder.modelo.Order;
import br.com.edson.msorder.repository.OrderRepository;

public class OrderRequest {
    @NotNull @NotEmpty @Length(min = 5)
    private String description;
    
    @NotNull @NotEmpty @Length(min = 5)
    private String name;
   
    @NotNull @Positive
    private Double total;

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Double getTotal() {
        return total;
    }
    public void setTotal(Double total) {
        this.total = total;
    }
  
    public Order atualizar(Integer id, OrderRepository orderRepository) {
        Order order = orderRepository.getById(id);
        
        order.setDescription(this.description);
        order.setName(this.name);
        order.setTotal(this.total);

        return order;
    }
    
    
}
