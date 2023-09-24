package com.ecommerce.apih2.price.infrastructure.adapter;

import com.ecommerce.apih2.price.domain.entity.Price;
import com.ecommerce.apih2.price.domain.port.PriceRepository;
import com.ecommerce.apih2.price.domain.vo.BrandId;
import com.ecommerce.apih2.price.domain.vo.PriceApplicationDate;
import com.ecommerce.apih2.price.domain.vo.ProductId;
import com.ecommerce.apih2.price.infrastructure.entity.PriceEntity;
import com.ecommerce.apih2.price.infrastructure.mapper.PriceMapper;
import com.ecommerce.apih2.price.infrastructure.repository.PriceJpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PriceFinderService implements PriceRepository {

    private final PriceJpaRepository priceJpaRepository;
    private final PriceMapper priceMapper;

    public PriceFinderService(PriceJpaRepository priceJpaRepository, PriceMapper priceMapper) {
        this.priceJpaRepository = priceJpaRepository;
        this.priceMapper = priceMapper;
    }

    @Override
    public Optional<Price> findByParams(PriceApplicationDate date, ProductId productId, BrandId brandId) {
        Optional<PriceEntity> result = priceJpaRepository.findPrice(date.value(),
                productId.value(),
                brandId.value());

        if (result == null || !result.isPresent()) {
            return Optional.empty();
        }

        return result.map(priceMapper::toDomain);
    }
}
