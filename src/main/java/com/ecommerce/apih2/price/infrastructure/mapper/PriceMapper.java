package com.ecommerce.apih2.price.infrastructure.mapper;

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
}
