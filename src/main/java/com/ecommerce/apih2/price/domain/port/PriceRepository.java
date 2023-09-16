package com.ecommerce.apih2.price.domain.port;

import com.ecommerce.apih2.price.domain.vo.BrandId;
import com.ecommerce.apih2.price.domain.vo.PriceApplicationDate;
import com.ecommerce.apih2.price.domain.entity.Price;
import com.ecommerce.apih2.price.domain.vo.ProductId;

import java.util.Optional;

public interface PriceRepository {

    Optional<Price> findByParams(PriceApplicationDate date, ProductId productId, BrandId brandId);
}
