package com.example.BackendExam.ServiceTesting;

import com.example.BackendExam.model.Order;
import com.example.BackendExam.repository.OrderRepository;
import com.example.BackendExam.service.OrderService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    @Test
    public void testGetOrders() {
        List<Order> mockOrders = List.of(new Order(), new Order());

        when(orderRepository.findAll()).thenReturn(mockOrders);

        List<Order> result = orderService.getOrders();

        assertThat(result).isEqualTo(mockOrders);
    }

    @Test
    public void testFindById() {
        Long orderId = 1L;
        Order mockOrder = new Order();

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(mockOrder));

        Optional<Order> result = orderService.findById(orderId);

        assertThat(result).contains(mockOrder);
    }
}