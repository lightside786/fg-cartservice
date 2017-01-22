package com.lightside.fg.web.controller;

import com.lightside.fg.web.request.CreateCartRequest;
import com.lightside.fg.web.response.CartDto;
import com.lightside.fg.web.response.CreateCartResponse;
import io.swagger.annotations.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
                                         @RequestBody @Valid CreateCartRequest createCartRequest, BindingResult errorResult);

    @ApiOperation(
            value = "Retrieves a cart for the id",
            notes = "Retrieves cart",
            httpMethod = "GET",
            response = CreateCartResponse.class)
    @ApiImplicitParam(name = "Record Identifier for Cart", value = "id", required = true, dataType = "string", paramType = "path", defaultValue = "initial-test-load-cartservice")
    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_UTF8_VALUE)
    public CartDto getCart(@PathVariable(value = "id") String id);

    @ApiOperation(
            value = "Retrieves all the carts in the system",
            notes = "Retrieves cart",
            httpMethod = "GET",
            response = CreateCartResponse.class)
    @GetMapping(produces = APPLICATION_JSON_UTF8_VALUE)
    public Page<CartDto> getCarts(Pageable pageable);

    @ApiOperation(
            value = "Deletes cart specified with Id",
            notes = "Deletes cart by Id",
            httpMethod = "DELETE")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Record Identifier for Cart", value = "id", required = true, dataType = "string", paramType = "path", defaultValue = "initial-test-load-cartservice")
    })
    @DeleteMapping(value = "/{id}", produces = APPLICATION_JSON_UTF8_VALUE)
    public void deleteCart(@PathVariable(value = "id") String id);


}
