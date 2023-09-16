package com.ecommerce.apih2.price.application.usecase;

import com.ecommerce.apih2.price.application.dto.PriceResponseDto;
import com.ecommerce.apih2.price.application.mapper.DateMapper;
import com.ecommerce.apih2.price.application.mapper.PriceMapper;
import com.ecommerce.apih2.price.application.param.GetPriceParam;
import com.ecommerce.apih2.price.domain.entity.Price;
import com.ecommerce.apih2.price.domain.exception.PriceNotFoundException;
import com.ecommerce.apih2.price.domain.port.PriceRepository;
import com.ecommerce.apih2.price.domain.vo.BrandId;
import com.ecommerce.apih2.price.domain.vo.PriceApplicationDate;
import com.ecommerce.apih2.price.domain.vo.ProductId;
import org.springframework.stereotype.Service;

@Service
public class GetPriceUseCase {

    private final PriceRepository priceRepository;
    private final PriceMapper priceMapper;

    public GetPriceUseCase(PriceRepository priceRepository, PriceMapper priceMapper) {
        this.priceRepository = priceRepository;
        this.priceMapper = priceMapper;
    }

    public PriceResponseDto handle(GetPriceParam params){
        PriceApplicationDate date = new PriceApplicationDate(DateMapper.mapToLocalDateTime(params.date()));
        ProductId productId = new ProductId(params.productId());
        BrandId brandId = new BrandId(params.brandId());

        Price price = priceRepository.findByParams(date, productId, brandId)
                .orElseThrow(PriceNotFoundException::new);

        return priceMapper.toDto(price);
    }

}
