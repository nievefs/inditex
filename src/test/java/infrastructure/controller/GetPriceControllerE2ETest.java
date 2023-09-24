package infrastructure.controller;

import com.ecommerce.apih2.Entrypoint;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(classes = Entrypoint.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "/db/data.sql")
public class GetPriceControllerE2ETest {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp() {
        // Configure the base URL for RestAssured tests
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    @Test
    @DisplayName("E2E-Test 1: petición a las 10:00 del día 14 del producto 35455 para la brand 1 (ZARA)")
    public void test1_GetPriceEndpoint() {
        given()
                .param("date", "2020-06-14 10:00:00")
                .param("productId", 35455)
                .param("brandId", 1)
                .when()
                .get("/final-price")
                .then()
                .statusCode(200)
                .contentType("application/json;charset=UTF-8")
                .body("productId", equalTo(35455))
                .body("brandId", equalTo(1))
                .body("tariff", equalTo(1))
                .body("starDate", equalTo("2020-06-14T00:00"))
                .body("endDate", equalTo("2020-12-31T23:59:59"))
                .body("price", equalTo(35.5F));
    }

    @Test
    @DisplayName("E2E-Test 2: petición a las 16:00 del día 14 del producto 35455 para la brand 1 (ZARA)")
    public void test2_GetPriceEndpoint() {
        given()
                .param("date", "2020-06-14 16:00:00")
                .param("productId", 35455)
                .param("brandId", 1)
                .when()
                .get("/final-price")
                .then()
                .statusCode(200)
                .contentType("application/json;charset=UTF-8")
                .body("productId", equalTo(35455))
                .body("brandId", equalTo(1))
                .body("tariff", equalTo(2))
                .body("starDate", equalTo("2020-06-14T15:00"))
                .body("endDate", equalTo("2020-06-14T18:30"))
                .body("price", equalTo(25.45F));
    }

    @Test
    @DisplayName("E2E-Test 3: petición a las 21:00 del día 14 del producto 35455 para la brand 1 (ZARA)")
    public void test3_GetPriceEndpoint() {
        given()
                .param("date", "2020-06-14 21:00:00")
                .param("productId", 35455)
                .param("brandId", 1)
                .when()
                .get("/final-price")
                .then()
                .statusCode(200)
                .contentType("application/json;charset=UTF-8")
                .body("productId", equalTo(35455))
                .body("brandId", equalTo(1))
                .body("tariff", equalTo(1))
                .body("starDate", equalTo("2020-06-14T00:00"))
                .body("endDate", equalTo("2020-12-31T23:59:59"))
                .body("price", equalTo(35.50F));
    }

    @Test
    @DisplayName("E2E-Test 4: petición a las 10:00 del día 15 del producto 35455 para la brand 1 (ZARA)")
    public void test4_GetPriceEndpoint() {
        given()
                .param("date", "2020-06-15 10:00:00")
                .param("productId", 35455)
                .param("brandId", 1)
                .when()
                .get("/final-price")
                .then()
                .statusCode(200)
                .contentType("application/json;charset=UTF-8")
                .body("productId", equalTo(35455))
                .body("brandId", equalTo(1))
                .body("tariff", equalTo(3))
                .body("starDate", equalTo("2020-06-15T00:00"))
                .body("endDate", equalTo("2020-06-15T11:00"))
                .body("price", equalTo(30.50F));
    }

    @Test
    @DisplayName("E2E-Test 5: petición a las 21:00 del día 16 del producto 35455 para la brand 1 (ZARA)")
    public void test5_GetPriceEndpoint() {
        given()
                .param("date", "2020-06-16 21:00:00")
                .param("productId", 35455)
                .param("brandId", 1)
                .when()
                .get("/final-price")
                .then()
                .statusCode(200)
                .contentType("application/json;charset=UTF-8")
                .body("productId", equalTo(35455))
                .body("brandId", equalTo(1))
                .body("tariff", equalTo(4))
                .body("starDate", equalTo("2020-06-15T16:00"))
                .body("endDate", equalTo("2020-12-31T23:59:59"))
                .body("price", equalTo(38.95F));
    }

    @Test
    @DisplayName("E2E-Test 6: petición a las 21:00 del día 16 del producto 35455 para la brand 2")
    public void test6_GetPriceEndpoint_ReturnNotFound() {
        given()
                .param("date", "2020-06-16 21:00:00")
                .param("productId", 35455)
                .param("brandId", 2)
                .when()
                .get("/final-price")
                .then()
                .statusCode(404)
                .contentType(Matchers.containsString("application/json"))
                .body("errorCode", equalTo(404))
                .body("message", equalTo("THERE_IS_NO_PRICE"));
    }

    @Test
    @DisplayName("E2E-Test 7: petición a las 21:00 del día 16 del producto 35455 para la brand 1(ZARA)")
    public void test7_GetPriceEndpoint_ReturnInvalidDate() {
        given()
                .param("date", "2020/06/16-21:00:00")
                .param("productId", 35455)
                .param("brandId", 1)
                .when()
                .get("/final-price")
                .then()
                .statusCode(400)
                .contentType(Matchers.containsString("application/json"))
                .body("errorCode", equalTo(400))
                .body("message", equalTo("INVALID_DATE"));
    }

    @Test
    @DisplayName("E2E-Test 8: petición a las 21:00 del día 16 del producto prod-invalid para la brand 1(ZARA)")
    public void test8_GetPriceEndpoint_ReturnInvalidDate() {
        given()
                .param("date", "2020/06/16-21:00:00")
                .param("productId", "prod-invalid")
                .param("brandId", 1)
                .when()
                .get("/final-price")
                .then()
                .statusCode(400)
                .contentType(Matchers.containsString("application/json"))
                .body("errorCode", equalTo(400))
                .body("message", equalTo("INVALID_ARGUMENT"));
    }
}
