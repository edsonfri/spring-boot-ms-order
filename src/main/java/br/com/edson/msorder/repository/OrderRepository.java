package br.com.edson.msorder.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.edson.msorder.modelo.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{
    @Query(value = "SELECT * FROM Orders WHERE" + "(:q IS NULL OR LOWER (name) LIKE LOWER(CONCAT('%',:q,'%')) OR :q IS NULL OR LOWER (description) LIKE LOWER(CONCAT('%',:q,'%')))"
                + "AND (:minTotal IS NULL OR total >= :minTotal)"
                + "AND (:maxTotal IS NULL OR total <= :maxTotal)"
                + "AND (:status IS NULL OR status >= :status)", nativeQuery = true)
    List<Order> search(String q, Double minTotal , Double maxTotal, String status );
}
