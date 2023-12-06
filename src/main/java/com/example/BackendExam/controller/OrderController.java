package com.example.BackendExam.controller;

import com.example.BackendExam.model.Customer;
import com.example.BackendExam.model.Order;
import com.example.BackendExam.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/controller")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<Order> getOrders() {
        return orderService.getOrders();
    }

    @PostMapping
    public void registerNewOrder(@RequestBody Order order) {
        orderService.addNewOrder(order);
    }

    @DeleteMapping(path = "{orderId}")
    public void deleteOrder(@PathVariable("orderId") Long orderId) {
        orderService.deleteOrder(orderId);
    }

    @PutMapping(path = "{orderId}")
    public void updateOrder(
            @PathVariable("orderId") Long orderId,
            @RequestParam(required = false) Customer newCustomer) {
        orderService.updateOrder(orderId, newCustomer);
    }
}