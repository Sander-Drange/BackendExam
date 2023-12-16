package com.example.BackendExam.EndToEndTests;

<<<<<<< HEAD:src/test/java/com/example/BackendExam/EndToEndTests/SubassemblyEndToEndTest.java
import com.example.BackendExam.model.Machine;
import com.example.BackendExam.model.Subassembly;
=======
>>>>>>> e37446c48fa3c2a1cac0e3813d68683bb394a2fb:src/test/java/com/example/BackendExam/EndToEndTests/SubassemblyE2ETest.java
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
        when(subassemblyRepository.save(newSubassembly)).thenReturn(newSubassembly);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/subassemblies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newSubassembly)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testDeleteSubassembly() throws Exception {
        Long subassemblyId = 1L;
        when(subassemblyRepository.existsById(subassemblyId)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/subassemblies/{id}", subassemblyId))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
