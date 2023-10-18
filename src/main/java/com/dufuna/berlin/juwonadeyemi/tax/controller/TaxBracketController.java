package com.dufuna.berlin.juwonadeyemi.tax.controller;

import com.dufuna.berlin.juwonadeyemi.tax.entity.TaxBracket;
import com.dufuna.berlin.juwonadeyemi.tax.service.TaxBracketServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TaxBracketController {
    private final TaxBracketServiceImpl taxBracketServiceImpl;
    @Autowired
    public TaxBracketController(TaxBracketServiceImpl taxBracketServiceImpl) {
        this.taxBracketServiceImpl = taxBracketServiceImpl;
    }
@GetMapping("/calculateTax")
@ResponseStatus(HttpStatus.OK)
    public double calculateTax(@RequestParam double income) {
        return taxBracketServiceImpl.calculateTax(income);
    }
    @GetMapping("/taxBracket")
    @ResponseStatus(HttpStatus.OK)
    public List<TaxBracket> getTheTaxBracketApi(){
        return  taxBracketServiceImpl.getAllTaxBrackets();
    }
}
