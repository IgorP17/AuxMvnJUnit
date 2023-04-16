package org.wiremock;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.stream.Stream;

public class WireMockGetTest {

    @ParameterizedTest(name = "{index}. Path {0}, resp {1}, body {2}")
    @MethodSource("provideData")
    public void test(String path, int respCode, String bodyPart) {

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

    private static Stream<Arguments> provideData() {
        return Stream.of(
                Arguments.of(Settings.getPath, 200, "Base url"),
                Arguments.of(Settings.getPath + Settings.getPathParams, 200, "БУЙА"),
                Arguments.of("/some_wrong_path", 404, "Request was not matched")
                );
    }
}
