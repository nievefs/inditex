package com.ecommerce.apih2.price.application.param;

public record GetPriceParam (
    String date,
    Long productId,
    int brandId

) {
}
