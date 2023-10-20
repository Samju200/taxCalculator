package com.dufuna.berlin.juwonadeyemi.tax.controller;
import com.dufuna.berlin.juwonadeyemi.tax.entity.TaxBracket;
import com.dufuna.berlin.juwonadeyemi.tax.service.TaxBracketServiceImpl;
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

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
/**
 * JUnit test class for testing the functionalities of the {@link TaxBracketController}.
 * It uses Spring's {@code @WebMvcTest} to set up the test environment and performs
 * various tests on the controller's endpoints.
 */
@WebMvcTest(TaxBracketController.class)
class TaxBracketControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TaxBracketServiceImpl taxBracketServiceImpl;

    @Autowired
    private ObjectMapper objectMapper;
    private TaxBracket taxbracket;

    private static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @BeforeEach
    public void init() {
        taxbracket = TaxBracket.builder().id(2L).lowerBound(18201.0).upperBound(45000.00).rate(0.19).build();
    }

    @Test
    void calculateTax() throws Exception {
        double income = 50000.0;
        double expectedTax = 6716.485;

        // Mock the behavior of the taxBracketService
        when(taxBracketServiceImpl.calculateTax(income)).thenReturn(expectedTax);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/taxBracket/calculateTax")
                        .param("income", String.valueOf(income))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(String.valueOf(expectedTax)));
    }


    @Test
    void getTheTaxBracketApi() throws Exception {
 List<TaxBracket> taxBrackets = Arrays.asList(
        new TaxBracket(1L,0, 18200, 0),
        new TaxBracket(2L,18201, 45000, 0.19),
        new TaxBracket(3L,45001, 120000, 0.325),
        new TaxBracket(4L,120001, 180000, 0.37),
        new TaxBracket(5L, 180001, 9999999.00, 0.45));

        Mockito.when(taxBracketServiceImpl.getAllTaxBrackets()).thenReturn(taxBrackets);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/taxBracket")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].upperBound").value(18200.0))
                .andExpect(MockMvcResultMatchers.jsonPath("$[3].id").value(4))
                .andExpect(MockMvcResultMatchers.jsonPath("$[3].lowerBound").value(120001.0));


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

    }

    @Test
    void updateBracketTaxApi() throws Exception {

        Long id = 4L;
        TaxBracket updatedTaxBracket = new TaxBracket(4L, 120001.0, 180000.0, 0.37);

        // Mock the behavior of the taxBracketService
        when(taxBracketServiceImpl.updateTaxBracket(id, updatedTaxBracket)).thenReturn(updatedTaxBracket);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/taxBracket/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(updatedTaxBracket))) // Use a utility method to convert the object to JSON
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(4))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rate").value(0.37));
    }

    @Test
    void deleteTaxBracketById() throws Exception {
        Long id = 1L;

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/taxBracket/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        // Verify that the deleteTaxBracketById method was called
        Mockito.verify(taxBracketServiceImpl).deleteTaxBracketById(id);
    }
}