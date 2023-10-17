package com.dufuna.berlin.juwonadeyemi.tax.service;

import com.dufuna.berlin.juwonadeyemi.tax.entity.TaxBracket;
import com.dufuna.berlin.juwonadeyemi.tax.repository.TaxBracketRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class TaxBracketServiceImplTest {

    @InjectMocks
    private TaxBracketService taxBracketService = new TaxBracketServiceImpl();

    @Mock
    private TaxBracketRepository taxBracketRepository;

    @Before
    public void setUp() {
        List<TaxBracket> mockTaxBrackets = taxBracketRepository.findAll();
        MockitoAnnotations.openMocks(this);
        Mockito.when(taxBracketRepository.findAll()).thenReturn(mockTaxBrackets);
    }

    @Test
    public void testCalculateTax() {


        double income = 50000;
        double expectedTax = (18200 * 0) + ((45000 - 18200) * 0.19) + ((income - 45000) * 0.325);
        double calculatedTax = taxBracketService.calculateTax(income);
        assertEquals(expectedTax, calculatedTax, 0.001);
    }
}