package com.dufuna.berlin.juwonadeyemi.tax.service;

import com.dufuna.berlin.juwonadeyemi.tax.entity.TaxBracket;
import com.dufuna.berlin.juwonadeyemi.tax.repository.TaxBracketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaxBracketServiceImpl implements TaxBracketService {

    @Autowired
    private TaxBracketRepository taxBracketRepository;

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
    public void createTaxBracket(TaxBracket taxBracket) {
      TaxBracket taxBracket1  = TaxBracket.builder().
                id(taxBracket.getId()).
                lowerBound(taxBracket.getLowerBound()).
                upperBound(taxBracket.getUpperBound()).
                rate(taxBracket.getRate()).build();

        taxBracketRepository.save(taxBracket1);
    }
    public TaxBracket getTaxBracketById(Long id) {
        Optional<TaxBracket> taxBracketOptional = taxBracketRepository.findById(id);
        if (taxBracketOptional.isPresent()) {
            return taxBracketOptional.get();
        }
     throw new RuntimeException(" TaxBracket is not found by id");
    }
    public void deleteTaxBracketById(Long id){
        taxBracketRepository.deleteById(id);
    }
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
