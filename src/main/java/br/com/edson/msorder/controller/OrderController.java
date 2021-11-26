package br.com.edson.msorder.controller;

import java.net.URI;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import br.com.edson.msorder.controller.dto.OrderDto;
import br.com.edson.msorder.controller.dto.OrderRequest;
import br.com.edson.msorder.modelo.Order;
import br.com.edson.msorder.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/orders")
@Api(value = "API REST Ordens")
@CrossOrigin(origins = "*")
public class OrderController {
    
    @Autowired
    private OrderService orderService;

    @GetMapping
    @ApiOperation(value = "Retorna uma lista de ordens") 
    public List<OrderDto> list() {
        return orderService.list();
    }

    @PostMapping
    @ApiOperation(value = "Cadastra uma nova ordens") 
    public ResponseEntity<OrderDto> cadastrar(@RequestBody @Valid OrderRequest request, UriComponentsBuilder uriBuilder){
        Order order = orderService.cadastrar(request);

        URI uri = uriBuilder.path("/orders/{id}").buildAndExpand(order.getId()).toUri();

        return ResponseEntity.created(uri).body(new OrderDto(order));

    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Busca uma determinada ordem pelo id referenciado") 
    public ResponseEntity<OrderDto> detalhar(@PathVariable Integer id) throws RuntimeException{ 
        Order order = orderService.detalhar(id);

        return ResponseEntity.ok(new OrderDto(order));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Atualiza uma ordem") 
    public ResponseEntity<OrderDto> atualizar(@PathVariable Integer id, @RequestBody @Valid OrderRequest request) throws RuntimeException{
        Order order = orderService.atualizar(id, request);

        return ResponseEntity.ok(new OrderDto(order));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Remove uma ordem") 
    public void remover(@PathVariable Integer id) throws RuntimeException {
        orderService.remover(id);
        ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    @ApiOperation(value = "Busca uma ordem") 
    public List<OrderDto> search(@RequestParam(required = false) String q, @RequestParam(value = "min_total", required = false) Double minTotal , @RequestParam(value = "max_total", required = false) Double maxTotal, @RequestParam(required = false) String status ){
        return orderService.search(q, minTotal, maxTotal, status);
    }
}
