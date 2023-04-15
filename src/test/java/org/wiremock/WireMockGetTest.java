package org.wiremock;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class WireMockGetTest {

    @Test
    public void test() {

        final String path = Settings.getPath;
        final int respCode = 200;
        final String bodyPart = "Base url";

        //        System.out.println("=== Running test with params path = {}, respCode = {}", path, respCode);
        RequestSpecification request = RestAssured.given();
        // Setting Request URL
        request.baseUri(Settings.baseURI);
        request.header("X_HEADER", "QWE");

//        logger.info("=== Request is ===");
        request.log().all();/*.get(getPath).then()
                .assertThat().statusCode(200)
                .log().all();*/

        // Send request
        Response response = request.get(path);
        printResponse(response);

        ArrayList<CompareItem> compareItems = new ArrayList<>();
        compareItems.add(new CompareItem(String.valueOf(response.getStatusCode()), String.valueOf(respCode), CompareEnum.EQUALS, "Status code"));
        compareItems.add(new CompareItem(response.getBody().asString(), bodyPart, CompareEnum.CONTAINS, "Body"));

        boolean result = CompareItems.compareItems(compareItems);

        Assertions.assertTrue(result, "Checking");

    }

    /*
    Print response
 */
    private void printResponse(Response response) {
        System.out.println("=== Response is ===\n" +
                response.getHeaders() + "\n\n" +
                response.getStatusCode() + "\n\n" +
                response.asString());
        System.out.println("=== END Response ===");
    }

}
