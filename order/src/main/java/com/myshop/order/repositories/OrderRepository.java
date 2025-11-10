package com.myshop.order.repositories;

import com.myshop.order.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order,Integer> {


    Optional<Order> findByOrderNumber(UUID orderNumber);
}
