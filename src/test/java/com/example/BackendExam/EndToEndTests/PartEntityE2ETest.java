package com.example.BackendExam.EndToEndTests;

import com.example.BackendExam.model.PartEntities;
import com.example.BackendExam.repository.PartEntitiesRepository;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class PartEntityE2ETest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PartEntitiesRepository partEntitiesRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetPartEntitiesWithPagination() throws Exception {
        int page = 0;
        int size = 2;
        List<PartEntities> mockPartEntities = List.of(new PartEntities(), new PartEntities());
        when(partEntitiesRepository.findAll(PageRequest.of(page, size))).thenReturn(new PageImpl<>(mockPartEntities));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/part-entities?page={page}&size={size}", page, size))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(mockPartEntities.size()));
    }

    @Test
    public void testGetPartEntityById() throws Exception {
        Long partEntityId = 1L;
        PartEntities mockPartEntity = new PartEntities();
        mockPartEntity.setId(partEntityId);
        when(partEntitiesRepository.findById(partEntityId)).thenReturn(Optional.of(mockPartEntity));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/part-entities/{id}", partEntityId))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(partEntityId));
    }

    @Test
    public void testCreatePartEntity() throws Exception {
        PartEntities newPartEntity = new PartEntities();

        when(partEntitiesRepository.save(any(PartEntities.class))).thenReturn(newPartEntity);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/part-entities")
               .contentType(MediaType.APPLICATION_JSON)
               .content(objectMapper.writeValueAsString(newPartEntity)))
               .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
