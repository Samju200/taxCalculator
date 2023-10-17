package com.dufuna.berlin.juwonadeyemi.tax.service;

import com.dufuna.berlin.juwonadeyemi.tax.entity.TaxBracket;
import com.dufuna.berlin.juwonadeyemi.tax.repository.TaxBracketRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
/**
 * This class contains unit tests for the {@link TaxBracketServiceImpl} class using the Mockito framework.
 * It ensures that the tax calculation logic is functioning correctly by mocking the {@link TaxBracketRepository}.
 *
 * @author [Your Name]
 * @see TaxBracketServiceImpl
 */
@ExtendWith(MockitoExtension.class)
public class TaxBracketServiceImplTest {
    @Mock
    private TaxBracketRepository taxBracketRepository;

    @InjectMocks
    private TaxBracketServiceImpl taxBracketServiceImpl;




    @Test
    public void testCalculateTax() {
        List<TaxBracket> mockTaxBrackets = Arrays.asList(
                new TaxBracket(1L,0, 18200, 0),
                new TaxBracket(2L,18201, 45000, 0.19),
                new TaxBracket(3L,45001, 120000, 0.325),
                new TaxBracket(4L,120001, 180000, 0.37),
                new TaxBracket(5L, 180001, 9999999.00, 0.45));



        Mockito.when(taxBracketRepository.findAll()).thenReturn(mockTaxBrackets);

        double income = 50000;
        double calculatedTax = taxBracketServiceImpl.calculateTax(income);
        assertEquals(6716.485, calculatedTax, 0.001);
        double income2 = 120000;
        double calculatedTax2 = taxBracketServiceImpl.calculateTax(income2);
        assertEquals(29466.485, calculatedTax2, 0.001);
        double income3 = 10000;
        double calculatedTax3 = taxBracketServiceImpl.calculateTax(income3);
        assertEquals(0, calculatedTax3, 0.001);

    }
}