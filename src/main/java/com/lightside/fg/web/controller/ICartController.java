package com.lightside.fg.web.controller;

import com.lightside.fg.web.request.CartRequest;
import com.lightside.fg.web.response.CartDto;
import com.lightside.fg.web.response.CreateCartResponse;
import io.swagger.annotations.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.MediaType.ALL_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

/**
 * Created by ummers on 08/11/16.
 */

@RestController
@RequestMapping("/api/v1/cart")
@Api(tags = {"Cart"}, description = "Information About All the carts")
@SwaggerDefinition(info = @Info(
        description = "This API's of carts are used to control Carts that are coming to the system.  ",
        version = "V1",
        title = "FG Cart Service")
)
public interface ICartController {


    @ApiOperation(
            value = "Cretes new cart",
            notes = "Create new Cart for FG Service",
            httpMethod = "POST",
            response = CreateCartResponse.class)

    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CreateCartResponse createCart(@ApiParam(value = "Cart to insert", required = true)
                                         @RequestBody @Valid CartRequest cartRequest, BindingResult errorResult);



    @ApiOperation(
            value = "Updates cart",
            notes = "Updates  Cart for FG Service",
            httpMethod = "PUT",
            response = CreateCartResponse.class)

    @ApiImplicitParams({
            @ApiImplicitParam(name = "recordId", value = "record Id for Cart", required = true, dataType = "string", paramType = "path", defaultValue = "initial-cart-record-id")
    })
    @RequestMapping(value="/{recordId}",method=RequestMethod.PUT)
    public CreateCartResponse updateCart(@PathVariable final String recordId,
                                         @RequestBody CartRequest cartRequest, BindingResult errorResult);

    @ApiOperation(
            value = "Retrieves a cart for the recordId",
            notes = "Retrieves cart",
            httpMethod = "GET",
            response = CreateCartResponse.class)
    @ApiImplicitParam(name = "recordId", value = "recordId", required = true, dataType = "string", paramType = "path", defaultValue = "initial-test-load-cartservice")
    @GetMapping(value = "/{recordId}", produces = APPLICATION_JSON_UTF8_VALUE)
    public CartDto getCart(@PathVariable(value = "recordId") String recordId);


    @ApiOperation(
            value = "Retrieves a cart for the User",
            notes = "Retrieves cart for a user",
            httpMethod = "GET",
            response = CreateCartResponse.class)
    @ApiImplicitParam(name = "userName", value = "userName", required = true, dataType = "string", paramType = "query", defaultValue = "ummershervani")
    @GetMapping(params = {"userName"}, consumes = ALL_VALUE , produces = APPLICATION_JSON_UTF8_VALUE)
    public CartDto getCartByUser(@RequestParam("userName") String userName);


    @ApiOperation(
            value = "Retrieves all the carts in the system",
            notes = "Retrieves cart",
            httpMethod = "GET",
            response = CreateCartResponse.class)
    @GetMapping(consumes = APPLICATION_JSON_UTF8_VALUE , produces = APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)

    public Page<CartDto> getCarts(Pageable pageable);

    @ApiOperation(
            value = "Deletes cart specified with record Id",
            notes = "Deletes cart by record Id",
            httpMethod = "DELETE")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "recordId", value = "recordId", required = true, dataType = "string", paramType = "path", defaultValue = "initial-test-load-cartservice")
    })
    @DeleteMapping(value = "/{recordId}", produces = APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteCart(@PathVariable(value = "recordId") String recordId);


}
