package com.ecommerce.apih2.price.infrastructure.dto;

public record PriceResponseDto(
      Long productId,
      Integer brandId,
      Integer tariff,
      String starDate,
      String endDate,
      Double price

) {
}
