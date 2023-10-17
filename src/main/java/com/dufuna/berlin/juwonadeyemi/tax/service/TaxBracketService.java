package com.dufuna.berlin.juwonadeyemi.tax.service;

import com.dufuna.berlin.juwonadeyemi.tax.entity.TaxBracket;

import java.util.List;
/**
 * This interface defines the contract for a service that handles tax calculations based on income and tax brackets.
 */
public interface TaxBracketService {
    /**
     * Retrieves all available tax brackets from the database.
     *
     * @return A list of {@link TaxBracket} objects representing the tax brackets.
     */
    List<TaxBracket> getAllTaxBrackets();

    /**
     * Calculates the tax amount for a given income based on the specified tax brackets and rates.
     *
     * @param income The income for which the tax should be calculated.
     * @return The calculated tax amount.
     */
    double calculateTax(double income);
}