package com.example.BackendExam.service;

import com.example.BackendExam.model.Customer;
import com.example.BackendExam.model.Order;
import com.example.BackendExam.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public List<Order> getOrders(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Order> orderPage = orderRepository.findAll(pageable);
        return orderPage.getContent();
    }

    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    public void addNewOrder(Order order) {
        orderRepository.save(order);
    }

    public void deleteOrder(Long orderId) {
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

        orderRepository.save(order);
    }
}
