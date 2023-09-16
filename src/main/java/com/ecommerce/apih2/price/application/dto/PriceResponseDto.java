package com.ecommerce.apih2.price.application.dto;

public record PriceResponseDto(
      Long productId,
      Integer brandId,
      Integer tariff,
      String starDate,
      String endDate,
      Double price

) {
}
