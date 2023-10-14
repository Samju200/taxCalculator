package com.dufuna.berlin.juwonadeyemi.tax.service;

import com.dufuna.berlin.juwonadeyemi.tax.entity.TaxBracket;
import com.dufuna.berlin.juwonadeyemi.tax.repository.TaxBracketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class TaxCalculationService {
    @Autowired
    private TaxBracketRepository taxBracketRepository;

    public double calculateTax(double income) {
        List<TaxBracket> brackets = taxBracketRepository.findAll();

        double tax = 0.0;

        for (TaxBracket bracket : brackets) {
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
