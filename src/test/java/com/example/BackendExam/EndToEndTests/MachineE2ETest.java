package com.example.BackendExam.EndToEndTests;

import com.example.BackendExam.repository.MachineRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class MachineE2ETest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MachineRepository machineRepository;

    @Test
    public void testGetAllMachines() throws Exception {
        mockMvc.perform(get("/api/machines")
               .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void testGetMachineById() throws Exception {
        // Assuming a machine with ID 1 exists
        mockMvc.perform(get("/api/machines/1")
               .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void testCreateMachine() throws Exception {
        String newMachineJson = "{\"name\":\"New Machine\"}";

        mockMvc.perform(post("/api/machines")
               .contentType(MediaType.APPLICATION_JSON)
               .content(newMachineJson))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$.name").value("New Machine"));
    }

    @Test
    public void testUpdateMachine() throws Exception {
        String updatedMachineJson = "{\"name\":\"Updated Machine\"}";

        mockMvc.perform(put("/api/machines/1")
               .contentType(MediaType.APPLICATION_JSON)
               .content(updatedMachineJson))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$.name").value("Updated Machine"));
    }

    @Test
    public void testDeleteMachine() throws Exception {
        // Assuming a machine with ID 1 exists and can be deleted
        mockMvc.perform(delete("/api/machines/1"))
               .andExpect(status().isOk());
    }
}
