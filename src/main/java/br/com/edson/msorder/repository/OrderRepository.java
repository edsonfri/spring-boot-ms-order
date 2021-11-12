package br.com.edson.msorder.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.edson.msorder.modelo.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{
    
}
