package com.dufuna.berlin.juwonadeyemi.tax.repository;
import com.dufuna.berlin.juwonadeyemi.tax.entity.TaxBracket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * A repository interface for managing tax brackets in the database.
 */
public interface TaxBracketRepository extends JpaRepository<TaxBracket, Long> {

    /**
        * Retrieves all available tax brackets from the database.
            * @return A list of {@link TaxBracket} objects representing the tax brackets stored in the database.
     */
    List<TaxBracket> findAll();


}