package br.com.edson.msorder.controller;

import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import br.com.edson.msorder.controller.dto.OrderDto;
import br.com.edson.msorder.controller.dto.OrderRequest;
import br.com.edson.msorder.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
    
    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<OrderDto> list() {
        return orderService.list();
    }

    @PostMapping
    public ResponseEntity<OrderDto> cadastrar(@RequestBody @Valid OrderRequest request, UriComponentsBuilder uriBuilder){

        return orderService.cadastrar(request, uriBuilder);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> detalhar(@PathVariable Integer id){

        return orderService.detalhar(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDto> atualizar(@PathVariable Integer id, @RequestBody @Valid OrderRequest request){
        
        return orderService.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<OrderDto> remover(@PathVariable Integer id){
        
        return orderService.remover(id);
    }

    @GetMapping("/search")
    public List<OrderDto> search(@RequestParam(required = false) String q, @RequestParam(value = "min_total", required = false) Double minTotal , @RequestParam(value = "max_total", required = false) Double maxTotal, @RequestParam(required = false) String status ){
        return orderService.search(q, minTotal, maxTotal, status);
    }
}
