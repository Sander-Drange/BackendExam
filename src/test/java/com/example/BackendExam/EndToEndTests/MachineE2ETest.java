package com.example.BackendExam.EndToEndTests;

import com.example.BackendExam.repository.MachineRepository;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.*;
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
    public void testGetAllMachinesWithPagination() throws Exception {
        int page = 0;
        int size = 2;

        mockMvc.perform(get("/api/machines")
                        .param("page", String.valueOf(page))
                        .param("size", String.valueOf(size))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content.length()", greaterThanOrEqualTo(0)))
                .andExpect(jsonPath("$.number", equalTo(page)))
                .andExpect(jsonPath("$.size", greaterThanOrEqualTo(size)))
                .andExpect(jsonPath("$.totalPages", greaterThanOrEqualTo(1)))
                .andExpect(jsonPath("$.totalElements", greaterThanOrEqualTo(0)));
    }


    @Test
    public void testGetMachineById() throws Exception {
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
        String newMachineJson = "{\"name\":\"New Machine\"}";

        MvcResult createResult = mockMvc.perform(post("/api/machines")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newMachineJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String responseContent = createResult.getResponse().getContentAsString();
        Long machineId = Long.parseLong(JsonPath.read(responseContent, "$.id").toString());

        String updatedMachineJson = "{\"name\":\"Updated Machine\"}";

        mockMvc.perform(put("/api/machines/{id}", machineId)
               .contentType(MediaType.APPLICATION_JSON)
               .content(updatedMachineJson))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$.name").value("Updated Machine"));
    }

    @Test
    public void testDeleteMachine() throws Exception {
        mockMvc.perform(delete("/api/machines/1"))
               .andExpect(status().isOk());
    }
}
