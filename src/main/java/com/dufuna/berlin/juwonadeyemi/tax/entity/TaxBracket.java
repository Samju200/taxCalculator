package com.dufuna.berlin.juwonadeyemi.tax.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a tax bracket with a specified range of income and associated tax rate.
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaxBracket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /** The minimum income threshold for this tax bracket. */
    private double lowerBound;
    /** The maximum income threshold for this tax bracket. */
    private double upperBound;
    /** The tax rate applicable within this tax bracket. */
    private double rate;


}

