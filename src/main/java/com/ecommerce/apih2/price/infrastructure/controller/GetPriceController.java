package com.ecommerce.apih2.price.infrastructure.controller;

import com.ecommerce.apih2.price.application.param.GetPriceParam;
import com.ecommerce.apih2.price.application.usecase.GetPriceUseCase;
import com.ecommerce.apih2.price.domain.error.ApiError;
import com.ecommerce.apih2.price.domain.exception.PriceNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.format.DateTimeParseException;

@RestController
public class GetPriceController {

    private final GetPriceUseCase useCase;

    public GetPriceController(GetPriceUseCase useCase) {
        this.useCase = useCase;
    }

    @Operation(summary = "Get price by params")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Price not found"),
            @ApiResponse(responseCode = "400", description = "Invalid date")})
    @GetMapping(value = "/final-price", produces = "application/json;charset=UTF-8" )
    public @ResponseBody ResponseEntity<?> getPrice(
            @RequestParam("date") String date,
            @RequestParam("productId") Long productId,
            @RequestParam("brandId") int brandId
    ){
        try{
            GetPriceParam params = new GetPriceParam(date, productId, brandId);

            return ResponseEntity.ok(useCase.handle(params));
        }catch (PriceNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ApiError(HttpStatus.NOT_FOUND.value(), "THERE_IS_NO_PRICE")
            );
        }catch (DateTimeParseException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiError(HttpStatus.BAD_REQUEST.value(), "INVALID_DATE"));
        }
    }
}
