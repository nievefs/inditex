package infrastructure;

import com.ecommerce.apih2.price.infrastructure.dto.PriceResponseDto;
import com.ecommerce.apih2.price.domain.entity.Price;
import com.ecommerce.apih2.price.domain.vo.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PriceFixture {

    public static Price createResponse(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTimeStart = LocalDateTime.parse("2023-02-15 00:00:00", formatter);
        LocalDateTime localDateTimeEnd = LocalDateTime.parse("2023-09-15 20:00:00", formatter);
       return new Price(
               new PriceId(201L),
               new BrandId(1),
               new StartDate(localDateTimeStart),
               new EndDate(localDateTimeEnd),
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
