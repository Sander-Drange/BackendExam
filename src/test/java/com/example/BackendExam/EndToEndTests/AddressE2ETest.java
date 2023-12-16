package com.example.BackendExam.EndToEndTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class AddressE2ETest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testPreloadedAddresses() throws Exception {
        mockMvc.perform(get("/api/addresses"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].street").value("Oslostreet"))
                .andExpect(jsonPath("$[0].city").value("Oslo"))
                .andExpect(jsonPath("$[0].country").value("Norway"))
                .andExpect(jsonPath("$[1].street").value("Swedenstreet"))
                .andExpect(jsonPath("$[1].city").value("Gothenburg"))
                .andExpect(jsonPath("$[1].country").value("Sweden"));
    }

    @Test
    public void testGetAddress() throws Exception {
        mockMvc.perform(get("/api/addresses/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.street").exists())
                .andExpect(jsonPath("$.city").exists())
                .andExpect(jsonPath("$.country").exists());
    }
}
