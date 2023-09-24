package com.ecommerce.apih2.price.application.usecase;

import com.ecommerce.apih2.price.domain.port.DateMapper;
import com.ecommerce.apih2.price.application.param.GetPriceParam;
import com.ecommerce.apih2.price.domain.entity.Price;
import com.ecommerce.apih2.price.domain.exception.PriceNotFoundException;
import com.ecommerce.apih2.price.domain.port.PriceRepository;
import com.ecommerce.apih2.price.domain.usecase.GetPriceUseCase;
import com.ecommerce.apih2.price.domain.vo.BrandId;
import com.ecommerce.apih2.price.domain.vo.PriceApplicationDate;
import com.ecommerce.apih2.price.domain.vo.ProductId;
import org.springframework.stereotype.Service;

@Service
public class GetPriceUseCaseImpl implements GetPriceUseCase {

    private final PriceRepository priceRepository;
    private final DateMapper dateMapper;

    public GetPriceUseCaseImpl(PriceRepository priceRepository, DateMapper dateMapper) {
        this.priceRepository = priceRepository;
        this.dateMapper = dateMapper;
    }

    @Override
    public Price handle(GetPriceParam params){
        PriceApplicationDate date = new PriceApplicationDate(dateMapper.mapToLocalDateTime(params.date()));
        ProductId productId = new ProductId(params.productId());
        BrandId brandId = new BrandId(params.brandId());

        return priceRepository.findByParams(date, productId, brandId)
                .orElseThrow(PriceNotFoundException::new);
    }

}
