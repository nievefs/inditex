package com.ecommerce.apih2.price.infrastructure.controller;

import com.ecommerce.apih2.price.application.param.GetPriceParam;
import com.ecommerce.apih2.price.application.usecase.GetPriceUseCase;
import com.ecommerce.apih2.price.domain.error.ApiError;
import com.ecommerce.apih2.price.domain.exception.PriceNotFoundException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.AbstractNamedValueMethodArgumentResolver;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;

import java.time.format.DateTimeParseException;

@RestController
public class GetPriceController {

    private final GetPriceUseCase useCase;

    public GetPriceController(GetPriceUseCase useCase) {
        this.useCase = useCase;
    }

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
        catch (MethodArgumentTypeMismatchException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiError(HttpStatus.BAD_REQUEST.value(), "INVALID_DATE"));
        }
    }
}
