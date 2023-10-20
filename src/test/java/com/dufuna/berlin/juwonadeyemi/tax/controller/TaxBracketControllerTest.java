package com.dufuna.berlin.juwonadeyemi.tax.controller;

import com.dufuna.berlin.juwonadeyemi.tax.entity.TaxBracket;
import com.dufuna.berlin.juwonadeyemi.tax.service.TaxBracketServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
@WebMvcTest(TaxBracketController.class)
class TaxBracketControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TaxBracketServiceImpl taxBracketServiceImpl;

    @Autowired
    private ObjectMapper objectMapper;
    private TaxBracket taxbracket;


    @BeforeEach
    public void init() {
        taxbracket = TaxBracket.builder().id(2L).lowerBound(18201.0).upperBound(45000.00).rate(0.19).build();
    }

    @Test
    void calculateTax() {
    }

    @Test
    void createTaxBracket() {
    }

    @Test
    void getTheTaxBracketApi() {
    }

    @Test
    void getTaxBracketById() throws Exception {

        Long id = 2L;
       Mockito.when(taxBracketServiceImpl.getTaxBracketById(id)).thenReturn(taxbracket);

        ResultActions response = mockMvc.perform(get("/api/taxBracket/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(taxbracket)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.lowerBound", CoreMatchers.is(taxbracket.getLowerBound())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.upperBound", CoreMatchers.is(taxbracket.getUpperBound())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rate", CoreMatchers.is(taxbracket.getRate())));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/taxBracket/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id));
    }

    @Test
    void updateBracketTaxApi() {
    }

    @Test
    void deleteTaxBracketById() {
    }
}