package com.example.demo.repository;

import com.example.demo.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByOrderDate(LocalDate orderDate);

    Page<Order> findAll(Pageable pageable);

    @Query("SELECT o FROM Order o WHERE o.totalAmount >= :amount")
    List<Order> findOrdersByMinAmount(@Param("amount") Double amount);

    @Query(value = "SELECT * FROM order WHERE order_date > :date", nativeQuery = true)
    List<Order> findOrdersAfterDate(@Param("date") LocalDate date);
}
