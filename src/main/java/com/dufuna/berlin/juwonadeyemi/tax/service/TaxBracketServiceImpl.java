package com.dufuna.berlin.juwonadeyemi.tax.service;

import com.dufuna.berlin.juwonadeyemi.tax.entity.TaxBracket;
import com.dufuna.berlin.juwonadeyemi.tax.repository.TaxBracketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
/**
 * Service class for managing tax brackets. This class implements the {@link TaxBracketService}
 * interface and provides methods to create, retrieve, update, and delete tax brackets, as well
 * as calculate taxes based on a given income.
 */
@Service
public class TaxBracketServiceImpl implements TaxBracketService {

    @Autowired
    private TaxBracketRepository taxBracketRepository;
    /**
     * Creates a new tax bracket in the database.
     * @param taxBracket The tax bracket to be created.
     */
    public void createTaxBracket(TaxBracket taxBracket) {
        TaxBracket taxBracket1  = TaxBracket.builder().
                id(taxBracket.getId()).
                lowerBound(taxBracket.getLowerBound()).
                upperBound(taxBracket.getUpperBound()).
                rate(taxBracket.getRate()).build();

        taxBracketRepository.save(taxBracket1);
    }
    /**
     * Retrieves a list of all tax brackets from the database.
     * @return A list of tax brackets.
     */
    @Override
    public List<TaxBracket> getAllTaxBrackets() {

        List<TaxBracket> taxBrackets = taxBracketRepository.findAll();
        return taxBrackets.stream().map(this::mapToTaxBracket).toList();


    }


    private TaxBracket mapToTaxBracket(TaxBracket taxbracket) {
        return TaxBracket.builder().
                id(taxbracket.getId()).
                lowerBound(taxbracket.getLowerBound()).
                upperBound(taxbracket.getUpperBound()).
                rate(taxbracket.getRate()).build();
    }
    /**
     * Retrieves a tax bracket by its unique ID.
     * @param id The ID of the tax bracket to retrieve.
     * @return The tax bracket with the specified ID.
     * @throws RuntimeException if the tax bracket is not found by the provided ID.
     */
    public TaxBracket getTaxBracketById(Long id) {
        Optional<TaxBracket> taxBracketOptional = taxBracketRepository.findById(id);
        if (taxBracketOptional.isPresent()) {
            return taxBracketOptional.get();
        }
     throw new RuntimeException(" TaxBracket is not found by id");
    }
    /**
     * Deletes a tax bracket by its unique ID.
     * @param id The ID of the tax bracket to delete.
     */
    public void deleteTaxBracketById(Long id){
        taxBracketRepository.deleteById(id);
    }

    /**
     * Updates the properties of a tax bracket with the provided ID.
     * @param id The ID of the tax bracket to update.
     * @param updatedTaxBracket The updated tax bracket information.
     * @return The updated tax bracket.
     * @throws RuntimeException if the tax bracket with the specified ID is not found.
     */
    public TaxBracket updateTaxBracket(Long id, TaxBracket updatedTaxBracket) {
        Optional<TaxBracket> existingTaxBracketOptional = taxBracketRepository.findById(id);

        if (existingTaxBracketOptional.isPresent()) {
            TaxBracket existingTaxBracket = existingTaxBracketOptional.get();

            // Update the fields you want to change
            existingTaxBracket.setLowerBound(updatedTaxBracket.getLowerBound());
            existingTaxBracket.setUpperBound(updatedTaxBracket.getUpperBound());
            existingTaxBracket.setRate(updatedTaxBracket.getRate());

            // Save the updated tax bracket
            return taxBracketRepository.save(existingTaxBracket);
        } else {
            // Handle the case where the tax bracket with the given ID is not found
            throw new RuntimeException(" TaxBracket is not found by id");
        }
    }
    /**
     * Calculates the tax based on the given income and the defined tax brackets.
     * @param income The income for which to calculate the tax.
     * @return The calculated tax amount.
     */
    @Override
    public double calculateTax(double income) {
        List<TaxBracket> taxBrackets = getAllTaxBrackets();
        double tax = 0.0;

        for (TaxBracket bracket : taxBrackets) {
            if (income >= bracket.getLowerBound() && income <= bracket.getUpperBound()) {
                tax += (income - bracket.getLowerBound()) * bracket.getRate();
                break;
            } else if (income > bracket.getUpperBound()) {
                tax += (bracket.getUpperBound() - bracket.getLowerBound()) * bracket.getRate();
            }
        }

        return tax;
    }
}
