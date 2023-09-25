package infrastructure.dto;

import com.ecommerce.apih2.price.infrastructure.dto.PriceResponseDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PriceResponseDtoTest {

    @Test
    public void testPriceResponseDto() {
        PriceResponseDto priceResponseDto = new PriceResponseDto(
                35455L,
                1,
                2,
                "2020-06-14 15:00:00",
                "2020-06-14 18:30:00",
                25.45
        );

        Assertions.assertEquals(35455L, priceResponseDto.productId());
        Assertions.assertEquals(1, priceResponseDto.brandId());
        Assertions.assertEquals(2, priceResponseDto.tariff());
        Assertions.assertEquals("2020-06-14 15:00:00", priceResponseDto.starDate());
        Assertions.assertEquals("2020-06-14 18:30:00", priceResponseDto.endDate());
        Assertions.assertEquals(25.45, priceResponseDto.price());
    }
}
