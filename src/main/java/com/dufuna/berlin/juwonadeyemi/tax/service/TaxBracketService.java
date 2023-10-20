package com.dufuna.berlin.juwonadeyemi.tax.service;

import com.dufuna.berlin.juwonadeyemi.tax.entity.TaxBracket;

import java.util.List;
import java.util.Optional;

/**
 * This interface defines the contract for a service that handles tax calculations based on income and tax brackets.
 */
public interface TaxBracketService {
    /**
     * Retrieves all available tax brackets from the database.
     * @return A list of {@link TaxBracket} objects representing the tax brackets.
     */
    List<TaxBracket> getAllTaxBrackets();
    public void createTaxBracket(TaxBracket taxBracket);
    TaxBracket getTaxBracketById(Long id);
    public TaxBracket updateTaxBracket(Long id, TaxBracket updatedTaxBracket);
    public void deleteTaxBracketById(Long id);

    /**
     * Calculates the tax amount for a given income based on the specified tax brackets and rates.
     * @param income The income for which the tax should be calculated.
     * @return The calculated tax amount.
     */
    double calculateTax(double income);
}