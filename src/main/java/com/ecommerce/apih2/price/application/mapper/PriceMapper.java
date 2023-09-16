package com.ecommerce.apih2.price.application.mapper;

import com.ecommerce.apih2.price.application.dto.PriceResponseDto;
import com.ecommerce.apih2.price.infrastructure.entity.PriceEntity;
import com.ecommerce.apih2.price.domain.entity.Price;
import com.ecommerce.apih2.price.domain.vo.*;
import org.springframework.stereotype.Component;

@Component
public class PriceMapper {

    public Price toDomain(PriceEntity entity){
        return new Price(
            new PriceId(entity.getId()),
            new BrandId(entity.getBrandId()),
            new StartDate(entity.getStartDate()),
            new EndDate(entity.getEndDate()),
            new PriceList(entity.getPriceList()),
            new ProductId(entity.getProductId()),
            new Priority(entity.getPriority()),
            new FinalPrice(entity.getPrice()),
            new Curr(entity.getCurr())
        );
    }

    public PriceResponseDto toDto(Price price){
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
