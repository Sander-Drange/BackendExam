package com.example.BackendExam.ServiceTesting;

import com.example.BackendExam.model.Order;
import com.example.BackendExam.repository.OrderRepository;
import com.example.BackendExam.service.OrderService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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
        // Mock data
        List<Order> mockOrders = List.of(new Order(), new Order());

        // Mock behavior
        when(orderRepository.findAll()).thenReturn(mockOrders);

        // Test
        List<Order> result = orderService.getOrders();

        // Verify
        assertThat(result).isEqualTo(mockOrders);
    }
}