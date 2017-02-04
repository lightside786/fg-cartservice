package com.lightside.fg.service.impl;

import com.lightside.fg.domain.Cart;
import com.lightside.fg.repository.ICartRepository;
import com.lightside.fg.service.ICartService;
import com.lightside.fg.web.exception.ErrorReporter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.ObjectError;

/**
 * @author Anwar
 */

@Transactional
@Component
@Slf4j
public class CartServiceImpl implements ICartService {

    private ICartRepository ICartRepository;

    private ErrorReporter errorReporter;

    public CartServiceImpl(ICartRepository ICartRepository) {
        this.ICartRepository = ICartRepository;
    }

    @Override
    public Cart createCart(Cart cart) {
        log.info("Creating Cart : {}", cart);
        return ICartRepository.saveAndFlush(cart);
    }

    @Override
    public Cart updateCart(Cart cart) {
        if(cart.getId() == null  || cart.getId() <= 0){
            errorReporter.createErrors(new ObjectError("cart.doesnotexist", "cart.doesnotexist"));
        }
        return ICartRepository.save(cart);
    }

    @Override
    public Cart getCartByRecordId(final String recordIdentifier) {
        return ICartRepository.findByRecordId(recordIdentifier);
    }

    @Override
    public Cart getCartById(final Long id) {
        return ICartRepository.findById(id);
    }

    @Override
    public Page<Cart> getCarts(Pageable pageable) {
        return ICartRepository.findAll(pageable);
    }

    @Override
    public void delete(final Long id) {
        ICartRepository.delete(id);
    }

    @Override
    public void deleteByRecordId(final String recordIdentifier) {
        ICartRepository.deleteByRecordId(recordIdentifier);
    }

}
