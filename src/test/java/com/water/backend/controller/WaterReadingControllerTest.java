package com.water.backend.controller;

import com.water.backend.dto.response.WaterReadingResponse;
import com.water.backend.security.JwtService;
import com.water.backend.service.WaterReadingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WaterReadingController.class)
class WaterReadingControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    WaterReadingService service;

    @MockitoBean
    JwtService jwtService;

    @Test
    @WithMockUser(username = "test@example.com", authorities = "RESIDENT")
    void getAllReadingsReturnsOk() throws Exception {

        when(service.getAllReadings()).thenReturn(List.of());

        mockMvc.perform(get("/api/readings"))
                .andExpect(status().isOk());
    }
}
