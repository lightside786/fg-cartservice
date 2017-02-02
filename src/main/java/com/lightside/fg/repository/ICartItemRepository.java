package com.lightside.fg.repository;

import com.lightside.fg.domain.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

/**
 * @author Ummers
 */

public interface ICartItemRepository extends JpaRepository<CartItem, Long> {

    CartItem findById(@Param("Id") final Long Id);

    CartItem findByRecordId(@Param("recordId") final String recordId);

    void deleteByRecordId(@Param("recordId") final String recordId) ;

}
