package application;

import com.ecommerce.apih2.price.application.param.GetPriceParam;
import com.ecommerce.apih2.price.application.usecase.GetPriceUseCase;
import com.ecommerce.apih2.price.domain.entity.Price;
import com.ecommerce.apih2.price.domain.exception.PriceNotFoundException;
import com.ecommerce.apih2.price.domain.port.DateMapper;
import com.ecommerce.apih2.price.domain.port.PriceRepository;
import com.ecommerce.apih2.price.domain.vo.BrandId;
import com.ecommerce.apih2.price.domain.vo.PriceApplicationDate;
import com.ecommerce.apih2.price.domain.vo.ProductId;
import infrastructure.PriceFixture;
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
    private DateMapper dateMapper;

    private GetPriceUseCase useCase;

    @BeforeEach
    void setUp(){
        dateMapper = mock(DateMapper.class);
        priceRepository = mock(PriceRepository.class);
        useCase = new GetPriceUseCase(priceRepository, dateMapper);
    }

    @Test
    void when_testHandle_priceExist(){

        Price price = PriceFixture.createResponse();

        when(priceRepository.findByParams(any(PriceApplicationDate.class), any(ProductId.class), any(BrandId.class)))
                .thenReturn(Optional.of(price));

        GetPriceParam params = new GetPriceParam("2023-05-15 00:00:00", 35000L, 1);
        Price result =  useCase.handle(params);

        Assertions.assertEquals(price.getProductId().value(), result.getProductId().value());
        Assertions.assertEquals(price.getBrandId().value(), result.getBrandId().value());
        Assertions.assertEquals(price.getPriceList().value(), result.getPriceList().value());
        Assertions.assertEquals(price.getStartDate().value(), result.getStartDate().value());
        Assertions.assertEquals(price.getEndDate().value(), result.getEndDate().value());
        Assertions.assertEquals(price.getFinalPrice().value(), result.getFinalPrice().value());
    }

    @Test
    void when_testHandle_priceNotFound(){

        assertThrows(PriceNotFoundException.class, () ->{
            when(priceRepository.findByParams(any(PriceApplicationDate.class), any(ProductId.class), any(BrandId.class)))
                    .thenReturn(Optional.empty());

            GetPriceParam params = new GetPriceParam("2023-05-15 00:00:00", 33000L, 1);
            Price result =  useCase.handle(params);
        });
    }
}
