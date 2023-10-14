package com.dufuna.berlin.juwonadeyemi.tax.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TaxBracket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private double lowerBound;
    private double upperBound;
    private double rate;


}

