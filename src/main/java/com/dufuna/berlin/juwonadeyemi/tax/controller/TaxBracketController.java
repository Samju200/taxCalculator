package com.dufuna.berlin.juwonadeyemi.tax.controller;
import com.dufuna.berlin.juwonadeyemi.tax.entity.TaxBracket;
import com.dufuna.berlin.juwonadeyemi.tax.service.TaxBracketServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * Controller class for managing tax brackets. This class provides RESTful endpoints for
 * calculating taxes, creating, updating, and deleting tax brackets.
 * It is annotated with {@code @RestController} to indicate that it handles HTTP requests and
 * returns the response as JSON, and it is mapped to the URL path "/api/taxBracket".
 */
@RestController
@RequestMapping("/api/taxBracket")
public class TaxBracketController {
    private final TaxBracketServiceImpl taxBracketServiceImpl;

    /**
     * Constructor for the controller. It is responsible for injecting an instance of
     * {@link TaxBracketServiceImpl} to handle tax bracket operations.
     * @param taxBracketServiceImpl The service used to manage tax brackets.
     */
    @Autowired
    public TaxBracketController(TaxBracketServiceImpl taxBracketServiceImpl) {
        this.taxBracketServiceImpl = taxBracketServiceImpl;
    }
    /**
     * Calculates the tax based on the provided income.
     * @param income The income for which to calculate the tax.
     * @return The calculated tax amount.
     */
    @GetMapping("/calculateTax")
    @ResponseStatus(HttpStatus.OK)
    public double calculateTax(@RequestParam double income) {
        return taxBracketServiceImpl.calculateTax(income);
    }

    /**
     * Endpoint to create a new tax bracket.
     * @param taxBracket The tax bracket to be created, provided in the request body.
     */
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createTaxBracket(@RequestBody TaxBracket taxBracket) {
        taxBracketServiceImpl.createTaxBracket(taxBracket);
    }
    /**
     * Retrieves a list of all tax brackets.
     * @return A list of tax brackets.
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TaxBracket> getTheTaxBracketApi(){
        return  taxBracketServiceImpl.getAllTaxBrackets();
    }

    /**
     * Endpoint to retrieve a tax bracket by its unique ID.
     * @param id The ID of the tax bracket to retrieve.
     * @return The tax bracket with the specified ID.
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TaxBracket getTaxBracketById(@PathVariable Long id) {
        return taxBracketServiceImpl.getTaxBracketById(id);
    }
    /**
     * Endpoint to update a tax bracket by its unique ID.
     * @param id The ID of the tax bracket to update.
     * @param updatedTaxBracket The updated tax bracket information, provided in the request body.
     * @return The updated tax bracket.
     */
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TaxBracket updateBracketTaxApi(@PathVariable Long id , @RequestBody TaxBracket updatedTaxBracket){
        return taxBracketServiceImpl.updateTaxBracket(id,updatedTaxBracket);
    }
    /**
     * Endpoint to delete a tax bracket by its unique ID.
     * @param id The ID of the tax bracket to delete.
     */
    @DeleteMapping("/{id}")
    public void deleteTaxBracketById(@PathVariable Long id){
        taxBracketServiceImpl.deleteTaxBracketById(id);
        System.out.println("The TaxBracket of the id has been deleted");
    }
}
