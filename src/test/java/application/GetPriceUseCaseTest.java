package application;

import com.ecommerce.apih2.price.application.dto.PriceResponseDto;
import com.ecommerce.apih2.price.application.mapper.PriceMapper;
import com.ecommerce.apih2.price.application.param.GetPriceParam;
import com.ecommerce.apih2.price.application.usecase.GetPriceUseCase;
import com.ecommerce.apih2.price.domain.entity.Price;
import com.ecommerce.apih2.price.domain.exception.PriceNotFoundException;
import com.ecommerce.apih2.price.domain.port.PriceRepository;
import com.ecommerce.apih2.price.domain.vo.BrandId;
import com.ecommerce.apih2.price.domain.vo.PriceApplicationDate;
import com.ecommerce.apih2.price.domain.vo.ProductId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetPriceUseCaseTest {

    @MockBean
    private PriceRepository priceRepository;
    @Autowired
    private PriceMapper priceMapper;

    private GetPriceUseCase useCase;

    @BeforeEach
    void setUp(){
        priceMapper = new PriceMapper();
        priceRepository = mock(PriceRepository.class);
        useCase = new GetPriceUseCase(priceRepository, priceMapper);
    }

    @Test
    void when_testHandle_priceExist(){

        Price price = PriceFixture.create();

        when(priceRepository.findByParams(any(PriceApplicationDate.class), any(ProductId.class), any(BrandId.class)))
                .thenReturn(Optional.of(PriceFixture.create()));

        GetPriceParam params = new GetPriceParam("2023-05-15 00:00:00", 35000L, 1);
        PriceResponseDto result =  useCase.handle(params);

        Assertions.assertEquals(price.getProductId().value(), result.productId() );
        Assertions.assertEquals(price.getBrandId().value(), result.brandId() );
        Assertions.assertEquals(price.getPriceList().value(), result.tariff() );
        Assertions.assertEquals(price.getStartDate().value().toString(), result.starDate() );
        Assertions.assertEquals(price.getEndDate().value().toString(), result.endDate() );
        Assertions.assertEquals(price.getFinalPrice().value(), result.price() );
    }

    @Test
    void when_testHandle_priceNotFound(){

        assertThrows(PriceNotFoundException.class, () ->{
            when(priceRepository.findByParams(any(PriceApplicationDate.class), any(ProductId.class), any(BrandId.class)))
                    .thenReturn(Optional.empty());

            GetPriceParam params = new GetPriceParam("2023-05-15 00:00:00", 33000L, 1);
            PriceResponseDto result =  useCase.handle(params);
        });
    }
}
