package com.dufuna.berlin.juwonadeyemi.tax.controller;

import com.dufuna.berlin.juwonadeyemi.tax.entity.TaxBracket;
import com.dufuna.berlin.juwonadeyemi.tax.service.TaxBracketServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/taxBracket")
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

    // Endpoint to create a TaxBracket
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createTaxBracket(@RequestBody TaxBracket taxBracket) {
        taxBracketServiceImpl.createTaxBracket(taxBracket);
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TaxBracket> getTheTaxBracketApi(){
        return  taxBracketServiceImpl.getAllTaxBrackets();
    }

    // Endpoint to get a TaxBracket by ID
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TaxBracket getTaxBracketById(@PathVariable Long id) {
        return taxBracketServiceImpl.getTaxBracketById(id);
    }
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TaxBracket updateBracketTaxApi(@PathVariable Long id , @RequestBody TaxBracket updatedTaxBracket){
        return taxBracketServiceImpl.updateTaxBracket(id,updatedTaxBracket);
    }
    @DeleteMapping("/{id}")
    public void deleteTaxBracketById(@PathVariable Long id){
        taxBracketServiceImpl.deleteTaxBracketById(id);
        System.out.println("The TaxBracket of the id has been deleted");
    }
}
