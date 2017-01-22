package com.lightside.fg.repository;

import com.lightside.fg.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

/**
 * @author Ummers
 */

public interface ICartRepository extends JpaRepository<Cart, Long> {

    Cart findById(@Param("Id") final Long Id);

    Cart findByUserId(@Param("userId") final String userId);

    Cart findByRecordId(@Param("recordId") final String recordId);

    void deleteByRecordId(@Param("recordId") final String recordId) ;

}
