package com.dufuna.berlin.juwonadeyemi.tax.service;
import com.dufuna.berlin.juwonadeyemi.tax.entity.TaxBracket;
import com.dufuna.berlin.juwonadeyemi.tax.repository.TaxBracketRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

class TaxCalculatorServiceTest {

    @Mock
    private TaxBracketRepository taxBracketRepository;
    @InjectMocks
    private TaxCalculationService taxCalculationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    void calculateTax() {
        TaxBracket taxBracket = new TaxBracket();
//         Set up of mock data and test the service
        when(taxBracketRepository.findAll()).thenReturn((List<TaxBracket>) taxBracket);
        double result1 = taxCalculationService.calculateTax(50000);
        assertEquals(7796.81, result1);
        double result2 = taxCalculationService.calculateTax(0);
        assertEquals(0, result2);
        double result3 = taxCalculationService.calculateTax(18200);
        assertEquals(0, result3);
        double result4 = taxCalculationService.calculateTax(120000);
        assertEquals(31896.48, result4);
    }


}
