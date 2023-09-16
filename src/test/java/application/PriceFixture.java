package application;

import com.ecommerce.apih2.price.application.dto.PriceResponseDto;
import com.ecommerce.apih2.price.application.mapper.DateMapper;
import com.ecommerce.apih2.price.domain.entity.Price;
import com.ecommerce.apih2.price.domain.vo.*;

public class PriceFixture {

    public static Price create(){
       return new Price(
               new PriceId(201L),
               new BrandId(1),
               new StartDate(DateMapper.mapToLocalDateTime("2023-02-15 00:00:00")),
               new EndDate(DateMapper.mapToLocalDateTime("2023-09-15 20:00:00")),
               new PriceList(2),
               new ProductId(35000L),
               new Priority(0),
               new FinalPrice(40.00),
               new Curr("EUR")
       );
    }

    public static PriceResponseDto createDto(){
        return new PriceResponseDto(
            35000L,
            1,
            2,
            "2023-02-15 00:00:00",
            "2023-09-15 20:00:00",
            40.40
        );
    }
}
