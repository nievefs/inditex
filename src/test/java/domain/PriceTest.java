package domain;

import com.ecommerce.apih2.price.domain.entity.Price;
import com.ecommerce.apih2.price.domain.vo.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PriceTest {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    LocalDateTime localDateTimeStart = LocalDateTime.parse("2023-09-22 00:00:00", formatter);
    LocalDateTime localDateTimeEnd = LocalDateTime.parse("2023-09-22 23:59:59", formatter);
    @Test
    public void testPriceConstructorAndGetters() {
        PriceId priceId = new PriceId(1L);
        BrandId brandId = new BrandId(2);
        StartDate startDate = new StartDate(localDateTimeStart);
        EndDate endDate = new EndDate(localDateTimeEnd);
        PriceList priceList = new PriceList(3);
        ProductId productId = new ProductId(4000L);
        Priority priority = new Priority(1);
        FinalPrice finalPrice = new FinalPrice(40.99);
        Curr iso = new Curr("EUR");

        Price price = new Price(priceId, brandId, startDate, endDate, priceList, productId, priority, finalPrice, iso);

        Assertions.assertNotNull(price);
        Assertions.assertEquals(priceId, price.getId());
        Assertions.assertEquals(brandId, price.getBrandId());
        Assertions.assertEquals(startDate, price.getStartDate());
        Assertions.assertEquals(endDate, price.getEndDate());
        Assertions.assertEquals(priceList, price.getPriceList());
        Assertions.assertEquals(productId, price.getProductId());
        Assertions.assertEquals(priority, price.getPriority());
        Assertions.assertEquals(finalPrice, price.getFinalPrice());
        Assertions.assertEquals(iso, price.getIso());
    }
}

