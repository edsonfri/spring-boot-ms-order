package br.com.edson.msorder.service;

import java.util.List;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    public Order cadastrar(OrderRequest request){
        Order order = new Order();
        modelMapper.map(request, order);

        return orderRepository.save(order);
    }

    public Order detalhar(Integer id) throws RuntimeException{
        return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Id não encontrado"));
    }

    @Transactional
    public Order atualizar( Integer id, OrderRequest request) throws RuntimeException{
        Order order = this.detalhar(id);
        modelMapper.map(request, order);
        order.setId(id);
        
        return order;
    }

    @Transactional
    public void remover(Integer id) throws RuntimeException{
        Order order = this.detalhar(id);

        orderRepository.delete(order);           
    }

    public List<OrderDto> search(String q, Double minTotal , Double maxTotal, String status){
        
        List<Order> orders = orderRepository.search(q, minTotal, maxTotal, status);

        return OrderDto.converter(orders);
    }

}
