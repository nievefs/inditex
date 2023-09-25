package infrastructure.controller;

import infrastructure.PriceFixture;
import com.ecommerce.apih2.price.application.param.GetPriceParam;
import com.ecommerce.apih2.price.application.usecase.GetPriceUseCaseImpl;
import com.ecommerce.apih2.price.infrastructure.controller.GetPriceController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;

@WebMvcTest(GetPriceController.class)
@ContextConfiguration(classes = {GetPriceController.class})
public class GetPriceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetPriceUseCaseImpl useCase;

    GetPriceParam params;

    @Test
    void should_response200OK() throws Exception {

        params = new GetPriceParam("2020-06-16 21:00:00", 35455L, 1);

        when(useCase.handle(params)).thenReturn(PriceFixture.createResponse());
        mockMvc
                .perform(MockMvcRequestBuilders.get("/final-price")
                        .param("date", "2020-06-16 21:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
