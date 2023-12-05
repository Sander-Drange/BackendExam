package com.example.BackendExam.service;

import com.example.BackendExam.model.Customer;
import com.example.BackendExam.model.Order;
import com.example.BackendExam.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public void addNewOrder(Order order) {
        orderRepository.save(order);
    }

    public void deleteorder(Long orderId) {
        boolean exists = orderRepository.existsById(orderId);
        if (!exists) {
            throw new IllegalStateException("order with id " + orderId + " does not exist");
        }
        orderRepository.deleteById(orderId);
    }

    @Transactional
    public void updateOrder(Long orderId, Customer newCustomer) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isEmpty()) {
            throw new IllegalStateException("Order with id " + orderId + " does not exist");
        }

        Order order = optionalOrder.get();
        order.setCustomer(newCustomer);

        // You may need additional logic here depending on your requirements

        orderRepository.save(order);
    }
}
