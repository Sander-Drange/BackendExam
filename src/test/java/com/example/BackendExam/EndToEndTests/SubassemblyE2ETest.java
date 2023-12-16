package com.example.BackendExam.EndToEndTests;

import com.example.BackendExam.repository.SubassemblyRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class SubassemblyE2ETest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SubassemblyRepository subassemblyRepository;

    @Autowired
    private ObjectMapper objectMapper;

    /*@Test
    public void testGetSubassemblies() throws Exception {
        List<Subassembly> mockSubassemblies = List.of(new Subassembly(), new Subassembly());
        when(subassemblyRepository.findAll()).thenReturn(mockSubassemblies);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/subassemblies"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(mockSubassemblies.size()));
    }

    @Test
    public void testGetSubassemblyById() throws Exception {
        Long subassemblyId = 1L;
        Subassembly mockSubassembly = new Subassembly();
        mockSubassembly.setId(subassemblyId);
        when(subassemblyRepository.findById(subassemblyId)).thenReturn(Optional.of(mockSubassembly));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/subassemblies/{id}", subassemblyId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(subassemblyId));
    }

    @Test
    public void testCreateSubassembly() throws Exception {
        Subassembly newSubassembly = new Subassembly();
        when(subassemblyRepository.save(newSubassembly)).thenReturn(newSubassembly);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/subassemblies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newSubassembly)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }*/
}
