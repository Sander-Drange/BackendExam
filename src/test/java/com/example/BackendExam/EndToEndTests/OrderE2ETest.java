package com.example.BackendExam.EndToEndTests;

import com.example.BackendExam.model.Order;
import com.example.BackendExam.repository.OrderRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderE2ETest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderRepository orderRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetOrdersWithPagination() throws Exception {
        int page = 0;
        int size = 2;
        List<Order> mockOrders = List.of(new Order(), new Order());
        when(orderRepository.findAll(PageRequest.of(page, size))).thenReturn(new PageImpl<>(mockOrders));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/orders?page={page}&size={size}", page, size))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(mockOrders.size()));
    }


    @Test
    public void testGetOrderById() throws Exception {
        Long orderId = 1L;
        Order mockOrder = new Order();
        mockOrder.setId(orderId);
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(mockOrder));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/orders/{id}", orderId))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(orderId));
    }

    @Test
    public void testCreateOrder() throws Exception {
        Order newOrder = new Order(1L);
        when(orderRepository.save(newOrder)).thenReturn(newOrder);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/orders")
               .contentType(MediaType.APPLICATION_JSON)
               .content(objectMapper.writeValueAsString(newOrder)))
               .andExpect(MockMvcResultMatchers.status().isOk());
    }
}