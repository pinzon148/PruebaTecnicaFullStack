package com.example.orchestrator;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class CreditResourceTest {
    @Test
    void testHelloEndpoint() {
        given()
          .when().get("/v1/credit-evaluations")
          .then()
             .statusCode(200)
             .body(is("Hello from Quarkus REST"));
    }

}