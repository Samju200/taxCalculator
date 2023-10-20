package com.dufuna.berlin.juwonadeyemi.tax.repository;
import com.dufuna.berlin.juwonadeyemi.tax.entity.TaxBracket;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class TaxBracketRepositoryTest {
@Autowired
 private TaxBracketRepository taxBracketRepository;
@Test
 void findByIDTaxBracket(){
    Long id = 3L;
    TaxBracket idTaxBracket = taxBracketRepository.findById(id).get();
    assertEquals(45001.0, idTaxBracket.getLowerBound());
    assertEquals(120000.0, idTaxBracket.getUpperBound());
    System.out.println(idTaxBracket);


 }
 @Test
 void findAllTaxBracket(){
    List<TaxBracket> allTaxBrackets = taxBracketRepository.findAll();
    for( TaxBracket allTaxBracket:allTaxBrackets){
        System.out.println(allTaxBracket);


    }

 }
 @Test
    void countTaxBracket(){
     long countTaxBrackets = taxBracketRepository.count();
     System.out.println(countTaxBrackets);
     assertEquals(5, countTaxBrackets);

 }
}