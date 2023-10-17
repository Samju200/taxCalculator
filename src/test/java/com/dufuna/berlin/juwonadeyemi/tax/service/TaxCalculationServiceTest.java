//package com.dufuna.berlin.juwonadeyemi.tax.service;
//import com.dufuna.berlin.juwonadeyemi.tax.entity.TaxBracket;
//import com.dufuna.berlin.juwonadeyemi.tax.repository.TaxBracketRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.TestConfiguration;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.when;
//
//@RunWith(SpringRunner.class)
//
//
//class TaxCalculatorServiceTest {
//
//    @TestConfiguration
//    static class TaxCalculatorServiceTestContextConfiguration{
//        @Bean
//        public TaxBracketService taxCalculationService() {
////            return new TaxBracketService();
//        }
//    }
//
//    @MockBean
//
//    private TaxBracketRepository taxBracketRepository;
//    @Autowired
//    private TaxBracketService taxCalculationService;
//
//    @BeforeEach
//    void setUp() {
//        TaxBracket taxBracket = new TaxBracket();
//        MockitoAnnotations.openMocks(this);
////        when(taxBracketRepository.findAll()).thenReturn(List <TaxBracket taxBracket1>);
//
//    }
//
//    @Test
//    void calculateTax() {
//
////         Set up of mock data and test the service
//
//
//        double result1 = taxCalculationService.calculateTax(50000);
//        assertEquals(7796.81, result1);
//        double result2 = taxCalculationService.calculateTax(0);
//        assertEquals(0, result2);
//        double result3 = taxCalculationService.calculateTax(18200);
//        assertEquals(0, result3);
//        double result4 = taxCalculationService.calculateTax(120000);
//        assertEquals(31896.48, result4);
//    }
//
//
//}
