package com.ecommerce.apih2.price.infrastructure.presenter;

import com.ecommerce.apih2.price.infrastructure.dto.PriceResponseDto;
import com.ecommerce.apih2.price.domain.entity.Price;

public class PricePresenter {

    private static final PricePresenter INSTANCE = new PricePresenter();

    public static PricePresenter getInstance(){
        return INSTANCE;
    }

    public PriceResponseDto convert(Price price){
        return new PriceResponseDto(
                price.getProductId().value(),
                price.getBrandId().value(),
                price.getPriceList().value(),
                price.getStartDate().value().toString(),
                price.getEndDate().value().toString(),
                price.getFinalPrice().value()
        );
    }
}
