package com.coredesk.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TaskControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnCreatedStatusWhenPostValidTask() throws Exception {
        String validTaskJson = """
                {
                  "title": "Інтеграційний тест",
                  "deadline": "2026-12-31T23:59:00"
                }
                """;

        mockMvc.perform(post("/api/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validTaskJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Інтеграційний тест"))
                .andExpect(jsonPath("$.status").value("pending"));
    }

    @Test
    void shouldReturnBadRequestWhenTitleIsEmpty() throws Exception {
        String invalidTaskJson = """
                {
                  "title": "",
                  "deadline": "2026-12-31T23:59:00"
                }
                """;

        mockMvc.perform(post("/api/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidTaskJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Bad Request"))
                .andExpect(jsonPath("$.messages.title").exists());
    }
}