package com.example.BackendExam.ServiceTesting;

import com.example.BackendExam.model.Order;
import com.example.BackendExam.repository.OrderRepository;
import com.example.BackendExam.service.OrderService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
public class OrderServiceTesting {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    @Test
    public void testGetOrdersWithPagination() {
        int page = 0;
        int size = 2;
        List<Order> mockOrders = List.of(new Order(), new Order());

        when(orderRepository.findAll(PageRequest.of(page, size))).thenReturn(new PageImpl<>(mockOrders));

        List<Order> result = orderService.getOrders(page, size);

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