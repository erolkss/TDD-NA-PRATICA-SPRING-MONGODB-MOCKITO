package br.com.ero.register.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void should_return_username_length_error() throws Exception {
        var request = new UserRequest("Lu", "1234", LocalDate.now());
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/users")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers
                        .status().isBadRequest())
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.errors[?(@.fieldName == 'name')].message",
                                Matchers.contains("O tamanho deve ser entre 6 e 10.")));
    }
}

