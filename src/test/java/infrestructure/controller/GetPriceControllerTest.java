package infrestructure.controller;

import com.ecommerce.apih2.price.application.dto.PriceResponseDto;
import com.ecommerce.apih2.price.application.param.GetPriceParam;
import com.ecommerce.apih2.price.application.usecase.GetPriceUseCase;
import com.ecommerce.apih2.price.domain.exception.PriceNotFoundException;
import com.ecommerce.apih2.price.infrastructure.controller.GetPriceController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.format.DateTimeParseException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(GetPriceController.class)
@ContextConfiguration(classes = {GetPriceController.class})
public class GetPriceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetPriceUseCase useCase;

    GetPriceParam params;

    @BeforeEach
    void setUp(){
        params = new GetPriceParam("2020-06-16 21:00:00", 35455L, 1);
    }

    @Test
    void should_response200OK() throws Exception {

        PriceResponseDto response = createResponse();
        when(useCase.handle(params)).thenReturn(response);
        mockMvc
                .perform(MockMvcRequestBuilders.get("/final-price")
                        .param("date", "2020-06-16 21:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void should_responseNotFound() throws Exception {

        when(useCase.handle(any(GetPriceParam.class))).thenThrow(new PriceNotFoundException());
        mockMvc.perform(MockMvcRequestBuilders.get("/final-price")
                        .param("date", "2023-09-15 14:30:00")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void should_response400BadRequest_InvalidDate() throws Exception {

        when(useCase.handle(any(GetPriceParam.class))).thenThrow(DateTimeParseException.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/final-price")
                        .param("date", "2023/09/15T14:30:00")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("INVALID_DATE"));

    }

    private PriceResponseDto createResponse(){
        return new PriceResponseDto(
                35455L,
                1,
                4,
                "2020-06-15 16:00:00",
                "2020-12-31 23:59:59",
                38.95
        );
    }

}
