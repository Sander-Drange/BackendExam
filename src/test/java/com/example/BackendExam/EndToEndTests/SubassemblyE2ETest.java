package com.example.BackendExam.EndToEndTests;

import com.example.BackendExam.model.Machine;
import com.example.BackendExam.model.Subassembly;
import com.example.BackendExam.repository.SubassemblyRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
public class SubassemblyE2ETest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SubassemblyRepository subassemblyRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllSubassemblies() throws Exception {
        List<Subassembly> mockSubassemblies = List.of(new Subassembly("Subassembly1", new Machine()), new Subassembly("Subassembly2", new Machine()));
        when(subassemblyRepository.findAll()).thenReturn(mockSubassemblies);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/subassemblies"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(mockSubassemblies.size()));
    }

    @Test
    public void testGetSubassemblyById() throws Exception {
        Long subassemblyId = 1L;
        Subassembly mockSubassembly = new Subassembly("Subassembly1", new Machine());
        mockSubassembly.setId(subassemblyId);
        when(subassemblyRepository.findById(subassemblyId)).thenReturn(Optional.of(mockSubassembly));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/subassemblies/{id}", subassemblyId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(subassemblyId));
    }

    @Test
    public void testCreateSubassembly() throws Exception {
        Subassembly newSubassembly = new Subassembly("NewSubassembly", new Machine());
        when(subassemblyRepository.save(any(Subassembly.class))).thenReturn(newSubassembly);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/subassemblies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newSubassembly)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
