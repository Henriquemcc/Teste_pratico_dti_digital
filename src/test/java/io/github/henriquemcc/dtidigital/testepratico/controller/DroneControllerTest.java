package io.github.henriquemcc.dtidigital.testepratico.controller;

import io.github.henriquemcc.dtidigital.testepratico.configuration.DatabaseContainerConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DroneControllerTest extends DatabaseContainerConfiguration {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    private String RECURSO = "/drones";

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void deveRetornar200QuandoRealizadoUmaRequisicaoGet() {
        try {
            mockMvc.perform(get(RECURSO)).andExpect(status().is2xxSuccessful());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void deveRetornar200QuandoRealizarUmaRequisicaoPost() {
        try{
            mockMvc.perform(post(RECURSO).contentType(MediaType.APPLICATION_JSON).content("{\"marca\":\"Drone INC\", \"modelo\": \"CargoWing Vortex 300\", \"numeroSerie\": \"1234567890\", \"distanciaPorCarga\": 20, \"capacidade\": 100}")).andExpect(status().is2xxSuccessful());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
