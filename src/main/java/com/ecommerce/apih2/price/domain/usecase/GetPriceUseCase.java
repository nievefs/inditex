package com.ecommerce.apih2.price.domain.usecase;

import com.ecommerce.apih2.price.application.param.GetPriceParam;
import com.ecommerce.apih2.price.domain.entity.Price;

public interface GetPriceUseCase {
    Price handle(GetPriceParam params);
}
