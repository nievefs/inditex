package infrastructure.adapter;

import com.ecommerce.apih2.Entrypoint;
import com.ecommerce.apih2.price.domain.entity.Price;
import com.ecommerce.apih2.price.domain.port.DateMapper;
import com.ecommerce.apih2.price.domain.vo.BrandId;
import com.ecommerce.apih2.price.domain.vo.PriceApplicationDate;
import com.ecommerce.apih2.price.domain.vo.ProductId;
import com.ecommerce.apih2.price.infrastructure.adapter.DateMapperImpl;
import com.ecommerce.apih2.price.infrastructure.adapter.PriceFinderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

@SpringBootTest(classes = Entrypoint.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql({"/db/schema.sql", "/db/data.sql"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ComponentScan(basePackages = "com.ecommerce.apih2.price.infrastructure.adapter")
public class PriceFinderServiceTest {

    @Autowired
    private PriceFinderService priceFinderService;

    @Autowired
    private DateMapper dateMapper;

    @Test
    @DisplayName("Test 1: petición a las 10:00 del día 14 del producto 35455 para la brand 1 (ZARA)")
    void test1_findPriceForDateAndProductAndBrand() {

        Optional<Price> price = priceFinderService.findByParams(
                new PriceApplicationDate(dateMapper.mapToLocalDateTime("2020-06-14 10:00:00")),
                new ProductId(35455L),
                new BrandId(1)
        );
        Assertions.assertTrue(price.isPresent());
        Assertions.assertEquals(35455L, price.get().getProductId().value());
        Assertions.assertEquals(1, price.get().getBrandId().value());
        Assertions.assertEquals(1, price.get().getPriceList().value());
        Assertions.assertEquals("2020-06-14T00:00", price.get().getStartDate().value().toString());
        Assertions.assertEquals("2020-12-31T23:59:59", price.get().getEndDate().value().toString());
        Assertions.assertEquals(35.50, price.get().getFinalPrice().value());
    }

    @Test
    @DisplayName("Test 2: petición a las 16:00 del día 14 del producto 35455 para la brand 1 (ZARA)")
    void test2_findPriceForDateAndProductAndBrand() {

        Optional<Price> price = priceFinderService.findByParams(
                new PriceApplicationDate(dateMapper.mapToLocalDateTime("2020-06-14 16:00:00")),
                new ProductId(35455L),
                new BrandId(1)
        );
        Assertions.assertTrue(price.isPresent());
        Assertions.assertEquals(35455L, price.get().getProductId().value());
        Assertions.assertEquals(1, price.get().getBrandId().value());
        Assertions.assertEquals(2, price.get().getPriceList().value());
        Assertions.assertEquals("2020-06-14T15:00", price.get().getStartDate().value().toString());
        Assertions.assertEquals("2020-06-14T18:30", price.get().getEndDate().value().toString());
        Assertions.assertEquals(25.45, price.get().getFinalPrice().value());
    }

    @Test
    @DisplayName("Test 3: petición a las 21:00 del día 14 del producto 35455 para la brand 1 (ZARA)")
    void test3_findPriceForDateAndProductAndBrand() {

        Optional<Price> price = priceFinderService.findByParams(
                new PriceApplicationDate(dateMapper.mapToLocalDateTime("2020-06-14 21:00:00")),
                new ProductId(35455L),
                new BrandId(1)
        );
        Assertions.assertTrue(price.isPresent());
        Assertions.assertEquals(35455L, price.get().getProductId().value());
        Assertions.assertEquals(1, price.get().getBrandId().value());
        Assertions.assertEquals(1, price.get().getPriceList().value());
        Assertions.assertEquals("2020-06-14T00:00", price.get().getStartDate().value().toString());
        Assertions.assertEquals("2020-12-31T23:59:59", price.get().getEndDate().value().toString());
        Assertions.assertEquals(35.50, price.get().getFinalPrice().value());
    }

    @Test
    @DisplayName("Test 4: petición a las 10:00 del día 15 del producto 35455 para la brand 1 (ZARA)")
    void test4_findPriceForDateAndProductAndBrand() {

        Optional<Price> price = priceFinderService.findByParams(
                new PriceApplicationDate(dateMapper.mapToLocalDateTime("2020-06-15 10:00:00")),
                new ProductId(35455L),
                new BrandId(1)
        );
        Assertions.assertTrue(price.isPresent());
        Assertions.assertEquals(35455L, price.get().getProductId().value());
        Assertions.assertEquals(1, price.get().getBrandId().value());
        Assertions.assertEquals(3, price.get().getPriceList().value());
        Assertions.assertEquals("2020-06-15T00:00", price.get().getStartDate().value().toString());
        Assertions.assertEquals("2020-06-15T11:00", price.get().getEndDate().value().toString());
        Assertions.assertEquals(30.50, price.get().getFinalPrice().value());
    }

    @Test
    @DisplayName("Test 5: petición a las 21:00 del día 16 del producto 35455 para la brand 1 (ZARA)")
    void test5_findPriceForDateAndProductAndBrand() {

        Optional<Price> price = priceFinderService.findByParams(
                new PriceApplicationDate(dateMapper.mapToLocalDateTime("2020-06-16 21:00:00")),
                new ProductId(35455L),
                new BrandId(1)
        );
        Assertions.assertTrue(price.isPresent());
        Assertions.assertEquals(35455L, price.get().getProductId().value());
        Assertions.assertEquals(1, price.get().getBrandId().value());
        Assertions.assertEquals(4, price.get().getPriceList().value());
        Assertions.assertEquals("2020-06-15T16:00", price.get().getStartDate().value().toString());
        Assertions.assertEquals("2020-12-31T23:59:59", price.get().getEndDate().value().toString());
        Assertions.assertEquals(38.95, price.get().getFinalPrice().value());
    }

    @Test
    @DisplayName("Tes 6: petición con un productId que no existe")
    public void test_findPriceNotExistentByParamProductId() {

        Optional<Price> price = priceFinderService.findByParams(
                new PriceApplicationDate(dateMapper.mapToLocalDateTime("2020-06-15 10:00:00")),
                new ProductId(36000L),
                new BrandId(2)
        );
        Assertions.assertFalse(price.isPresent());
    }

    @Test
    @DisplayName("Tes 7: petición con un brandId que no existe")
    public void test_findPriceNotExistentByParamBrandId() {

        Optional<Price> price = priceFinderService.findByParams(
                new PriceApplicationDate(dateMapper.mapToLocalDateTime("2020-06-15 10:00:00")),
                new ProductId(35455L),
                new BrandId(8)
        );
        Assertions.assertFalse(price.isPresent());
    }
}
