package com.example.BackendExam.EndToEndTests;

import com.example.BackendExam.model.Machine;
import com.example.BackendExam.model.Subassembly;
import com.example.BackendExam.service.SubassemblyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class SubassemblyE2ETest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SubassemblyService subassemblyService;

    private Subassembly subassembly1;
    private Subassembly subassembly2;

    @BeforeEach
    public void setup() {
        Machine machine = new Machine("Machine1");
        subassembly1 = new Subassembly("Subassembly1", machine);
        subassembly2 = new Subassembly("Subassembly2", machine);
    }

    @Test
    public void testGetAllSubassemblies() throws Exception {
        List<Subassembly> subassemblies = List.of(subassembly1, subassembly2);
        Mockito.when(subassemblyService.getSubassemblies()).thenReturn(subassemblies);

        mockMvc.perform(get("/api/subassemblies"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is(subassembly1.getName())))
                .andExpect(jsonPath("$[1].name", is(subassembly2.getName())));
    }

    @Test
    public void testGetSubassemblyById() throws Exception {
        Long id = 1L;
        Mockito.when(subassemblyService.findById(id)).thenReturn(Optional.of(subassembly1));

        mockMvc.perform(get("/api/subassemblies/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is(subassembly1.getName())));
    }

    @Test
    public void testCreateSubassembly() throws Exception {
        Mockito.when(subassemblyService.addNewSubassembly(Mockito.any(Subassembly.class))).thenReturn(subassembly1);

        mockMvc.perform(post("/api/subassemblies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Subassembly1\", \"machine\": {\"name\": \"Machine1\"}}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(subassembly1.getName())));
    }

    @Test
    public void testDeleteSubassembly() throws Exception {
        Long id = 1L;
        Mockito.doNothing().when(subassemblyService).deleteSubassembly(id);

        mockMvc.perform(delete("/api/subassemblies/{id}", id))
                .andExpect(status().isOk());
    }
}
