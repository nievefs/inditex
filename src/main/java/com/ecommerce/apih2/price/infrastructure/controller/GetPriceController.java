package com.ecommerce.apih2.price.infrastructure.controller;

import com.ecommerce.apih2.price.application.param.GetPriceParam;
import com.ecommerce.apih2.price.application.usecase.GetPriceUseCase;
import com.ecommerce.apih2.price.domain.entity.Price;
import com.ecommerce.apih2.price.infrastructure.presenter.PricePresenter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public @ResponseBody ResponseEntity getPrice(
            @Parameter(description = "Date format yyyy-MM-dd HH:mm:ss") @RequestParam("date") String date,
            @Parameter(description = "Product id example 35000") @RequestParam("productId") Long productId,
            @Parameter(description = "Brand id example 1") @RequestParam("brandId") int brandId
    ){
        GetPriceParam params = new GetPriceParam(date, productId, brandId);
        Price price = useCase.handle(params);
        return ResponseEntity.ok(PricePresenter.getInstance().convert(price));
    }
}
