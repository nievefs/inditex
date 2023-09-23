package infrastructure.exception;

import com.ecommerce.apih2.price.domain.error.ApiError;
import com.ecommerce.apih2.price.domain.exception.PriceNotFoundException;
import com.ecommerce.apih2.price.infrastructure.exception.GlobalControllerAdvice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import java.time.format.DateTimeParseException;

@WebMvcTest(GlobalControllerAdvice.class)
@ContextConfiguration(classes = {GlobalControllerAdvice.class})
public class GlobalControllerAdviceTest {

    @Autowired
    private GlobalControllerAdvice globalControllerAdvice;

    @Test
    public void should_handlePriceNotFoundException() {
        PriceNotFoundException ex = new PriceNotFoundException();
        ResponseEntity<ApiError> response = globalControllerAdvice.handlePriceNotFoundException(ex);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Assertions.assertEquals("THERE_IS_NO_PRICE", response.getBody().message());
    }

    @Test
    public void should_handleDateTimeParseException() {
        DateTimeParseException ex = new DateTimeParseException("Invalid date", "2023/09/15T14:30:00", 0);
        ResponseEntity<ApiError> response = globalControllerAdvice.handleDateTimeParseException(ex);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Assertions.assertEquals("INVALID_DATE", response.getBody().message());
    }

    @Test
    public void should_handleMethodArgumentTypeMismatch_withProductId() {
        MethodArgumentTypeMismatchException ex = new MethodArgumentTypeMismatchException(
                "productId", Integer.class, "invalid-productId", null, null);
        ResponseEntity<ApiError> response = globalControllerAdvice.handleMethodArgumentTypeMismatch(ex);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Assertions.assertEquals("INVALID_ARGUMENT", response.getBody().message());
    }

    @Test
    public void should_handleMethodArgumentTypeMismatch_withBrandId() {
        MethodArgumentTypeMismatchException ex = new MethodArgumentTypeMismatchException(
                "brandId", Integer.class, "invalid-brandId", null, null);
        ResponseEntity<ApiError> response = globalControllerAdvice.handleMethodArgumentTypeMismatch(ex);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Assertions.assertEquals("INVALID_ARGUMENT", response.getBody().message());
    }
}
