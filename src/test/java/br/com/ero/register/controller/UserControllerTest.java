package br.com.ero.register.controller;

import br.com.ero.register.controller.request.UserRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
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


    @ParameterizedTest
    @CsvSource({"Jo,12", "Danilo Arantes,1234567"})
    void should_return_username_length_error(String name, String password) throws Exception {
        var request = new UserRequest(name, password, null);
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/users")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers
                        .status().isBadRequest())
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.errors", Matchers.hasSize(3)))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.errors[?(@.fieldName == 'name')].message",
                                Matchers.contains("O tamanho deve ser entre 6 e 10.")))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.errors[?(@.fieldName == 'password')].message",
                                Matchers.contains("O tamanho deve ser entre 4 e 6.")))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.errors[?(@.fieldName == 'dateOfBirth')].message",
                                Matchers.contains("O campo de data não pode pode ser null.")));
    }
}

