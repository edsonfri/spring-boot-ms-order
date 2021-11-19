package br.com.edson.msorder.service;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import br.com.edson.msorder.controller.dto.OrderDto;
import br.com.edson.msorder.controller.dto.OrderRequest;
import br.com.edson.msorder.modelo.Order;
import br.com.edson.msorder.repository.OrderRepository;

@Service
public class OrderService {
    
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<OrderDto> list() {
        List<Order> orders = orderRepository.findAll();

        return OrderDto.converter(orders);
    }

    @Transactional
    public ResponseEntity<OrderDto> cadastrar(OrderRequest request, UriComponentsBuilder uriBuilder){
        Order order = new Order();
        modelMapper.map(request, order);

        orderRepository.save(order);

        URI uri = uriBuilder.path("/orders/{id}").buildAndExpand(order.getId()).toUri();

        return ResponseEntity.created(uri).body(new OrderDto(order));
    }

    public ResponseEntity<OrderDto> detalhar(Integer id){
        Optional<Order> order = orderRepository.findById(id);

        if (order.isPresent()) {
            return ResponseEntity.ok(new OrderDto(order.get()));
        }

        return ResponseEntity.notFound().build();
    }

    @Transactional
    public ResponseEntity<OrderDto> atualizar( Integer id, OrderRequest request){
        Optional<Order> optional = orderRepository.findById(id);

        if (optional.isPresent()) {
            Order order = request.atualizar(id, orderRepository);
            return ResponseEntity.ok(new OrderDto(order));
        }
        
        return ResponseEntity.notFound().build();
    }

    @Transactional
    public ResponseEntity<OrderDto> remover(Integer id){
        Optional<Order> optional = orderRepository.findById(id);

        if (optional.isPresent()) {
            orderRepository.deleteById(id);

            return ResponseEntity.ok().build();
        }
        
        return ResponseEntity.notFound().build();
    }

    public List<OrderDto> search(String q, Double minTotal , Double maxTotal, String status){
        
        List<Order> orders = orderRepository.search(q, minTotal, maxTotal, status);

        return OrderDto.converter(orders);
    }

}
