package com.example.BackendExam.repository;

import com.example.BackendExam.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
