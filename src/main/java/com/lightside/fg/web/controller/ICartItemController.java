package com.lightside.fg.web.controller;

import com.lightside.fg.web.request.CartItemRequest;
import com.lightside.fg.web.response.CartItemResponse;
import com.lightside.fg.web.response.CreateCartResponse;
import com.lightside.fg.web.response.mapper.CreateCartItemResponse;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

/**
 * Created by ummers on 08/11/16.
 */

@RestController
@RequestMapping("/api/v1/cart/{cartId}/cartitem")
@Api(tags = {"CartItem"}, description = "Information About All the cartItems")
@SwaggerDefinition(info = @Info(
        description = "This API's of cartItems are used to control CartItems that are coming to the system.  ",
        version = "V1",
        title = "FG CartItem Service")
)
public interface ICartItemController {


    @ApiOperation(
            value = "Cretes new cartItem",
            notes = "Create new CartItem for FG Service",
            httpMethod = "POST",
            response = CreateCartResponse.class)

    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParam(name = "cartId", value = " Record Id for CartItem", required = true, dataType = "string", paramType = "path", defaultValue = "initial-cart-record-id")
    @ResponseStatus(HttpStatus.CREATED)
    public CreateCartResponse createCartItem(
            @PathVariable final String cartId,
            @ApiParam(value = "CartItem to insert", required = true)
                                                 @RequestBody @Valid CartItemRequest cartItemRequest, BindingResult errorResult);



    @ApiOperation(
            value = "Updates cartItem",
            notes = "Updates  CartItem for FG Service",
            httpMethod = "PUT",
            response = CreateCartResponse.class)

    @ApiImplicitParams({
            @ApiImplicitParam(name = "cartId", value = " Record Id for CartItem", required = true, dataType = "string", paramType = "path", defaultValue = "initial-cart-record-id"),
            @ApiImplicitParam(name = "recordId", value = "record Id for CartItem", required = true, dataType = "string", paramType = "path", defaultValue = "initial-cartItem-record-id")
    })
    @RequestMapping(value="/{recordId}",method=RequestMethod.PUT)
    public CreateCartResponse updateCartItem(@PathVariable final String cartId,
                                                @PathVariable final String recordId,
                                                @RequestBody CartItemRequest cartItemRequest, BindingResult errorResult);

    @ApiOperation(
            value = "Retrieves a cartItem for the recordId",
            notes = "Retrieves cartItem",
            httpMethod = "GET",
            response = CreateCartItemResponse.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cartId", value = " Record Id for CartItem", required = true, dataType = "string", paramType = "path", defaultValue = "initial-cart-record-id"),
            @ApiImplicitParam(name = "recordId", value = "record Id for CartItem", required = true, dataType = "string", paramType = "path", defaultValue = "initial-cartItem-record-id")
    })
    @GetMapping(value = "/{recordId}", produces = APPLICATION_JSON_UTF8_VALUE)
    public CartItemResponse getCartItem(@PathVariable final String cartId, @PathVariable(value = "recordId") String recordId);

//    @ApiOperation(
//            value = "Retrieves all the cartItems in the system",
//            notes = "Retrieves cartItem",
//            httpMethod = "GET",
//            response = CreateCartResponse.class)
//    @GetMapping(produces = APPLICATION_JSON_UTF8_VALUE)
//    @ApiImplicitParam(name = "cartId", value = " Record Id for CartItem", required = true, dataType = "string", paramType = "path", defaultValue = "initial-cart-record-id")
//    @ResponseStatus(HttpStatus.OK)
//    public Page<CartItemResponse> getCartItems(@PathVariable(value = "cartId") final String cartId,
//                                               Pageable pageable);

    @ApiOperation(
            value = "Deletes cartItem specified with record Id",
            notes = "Deletes cartItem by record Id",
            httpMethod = "DELETE")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cartId", value = " Record Id for CartItem", required = true, dataType = "string", paramType = "path", defaultValue = "initial-cart-record-id"),
            @ApiImplicitParam(name = "recordId", value = "recordId", required = true, dataType = "string", paramType = "path", defaultValue = "initial-test-load-cartItemservice")
    })
    @DeleteMapping(value = "/{recordId}", produces = APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public CreateCartResponse deleteCartItem(
                                             @PathVariable(value = "cartId") final String cartId,
                                             @PathVariable(value = "recordId") String recordId);


}
