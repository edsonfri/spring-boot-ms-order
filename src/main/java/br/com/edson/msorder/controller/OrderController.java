package br.com.edson.msorder.controller;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.edson.msorder.controller.dto.OrderDto;
import br.com.edson.msorder.controller.dto.OrderRequest;
import br.com.edson.msorder.modelo.Order;
import br.com.edson.msorder.repository.OrderRepository;

@RestController
@RequestMapping("/orders")
public class OrderController {
    
    @Autowired
    private OrderRepository orderRepository;

    @GetMapping
    public List<OrderDto> list() {
        List<Order> orders = orderRepository.findAll();

        return OrderDto.converter(orders);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<OrderDto> cadastrar(@RequestBody @Valid OrderRequest request, UriComponentsBuilder uriBuilder){
        Order order = request.converter();
        orderRepository.save(order);

        URI uri = uriBuilder.path("/orders/{id}").buildAndExpand(order.getId()).toUri();

        return ResponseEntity.created(uri).body(new OrderDto(order));
    }

    @RequestMapping("/{id}")
    public ResponseEntity<OrderDto> detalhar(@PathVariable Integer id){
        Optional<Order> order = orderRepository.findById(id);

        if (order.isPresent()) {
            return ResponseEntity.ok(new OrderDto(order.get()));
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<OrderDto> atualizar(@PathVariable Integer id, @RequestBody @Valid OrderRequest request){
        Optional<Order> optional = orderRepository.findById(id);

        if (optional.isPresent()) {
            Order order = request.atualizar(id, orderRepository);
            return ResponseEntity.ok(new OrderDto(order));
        }
        
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable Integer id){
        Optional<Order> optional = orderRepository.findById(id);

        if (optional.isPresent()) {
            orderRepository.deleteById(id);

            return ResponseEntity.ok().build();
        }
        
        return ResponseEntity.notFound().build();
    }
}
