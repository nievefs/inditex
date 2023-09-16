package com.ecommerce.apih2.price.domain.entity;

import com.ecommerce.apih2.price.domain.vo.*;
public class Price {
    private PriceId id;
    private BrandId brandId;
    private StartDate startDate;
    private EndDate endDate;
    private PriceList priceList;
    private ProductId productId;
    private Priority priority;
    private FinalPrice finalPrice;
    private Curr iso;

    public Price(
            PriceId id,
            BrandId brandId,
            StartDate startDate,
            EndDate endDate,
            PriceList priceList,
            ProductId productId,
            Priority priority,
            FinalPrice finalPrice,
            Curr iso
    ) {
        this.id = id;
        this.brandId = brandId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.priceList = priceList;
        this.productId = productId;
        this.priority = priority;
        this.finalPrice = finalPrice;
        this.iso = iso;
    }

    public PriceId getId() {
        return id;
    }

    public BrandId getBrandId() {
        return brandId;
    }
    public StartDate getStartDate() {
        return startDate;
    }
    public EndDate getEndDate() {
        return endDate;
    }
    public PriceList getPriceList() {
        return priceList;
    }

    public ProductId getProductId() {
        return productId;
    }
    public Priority getPriority() {
        return priority;
    }
    public FinalPrice getFinalPrice() {
        return finalPrice;
    }
    public Curr getIso() {
        return iso;
    }
}
