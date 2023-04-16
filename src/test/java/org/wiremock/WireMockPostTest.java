package org.wiremock;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.stream.Stream;

public class WireMockPostTest {

    @ParameterizedTest(name = "{index}. ID {0}, name {1}, cost {2}, message {3}")
    @MethodSource("provideData")
    public void test(String id, String name, String cost, String updateMessage) {

        //        System.out.println("=== Running test with params path = {}, respCode = {}", path, respCode);
        RequestSpecification request = RestAssured.given();
        // Setting Request URL
        request.baseUri(Settings.baseURI);
        request.body(Settings.postBody
                .replace("THIS_ID", id)
                .replace("THIS_NAME", name)
                .replace("THIS_COST", cost));
        request.contentType("application/json;charset=UTF-8");

//        logger.info("=== Request is ===");
        request.log().all();/*.get(getPath).then()
                .assertThat().statusCode(200)
                .log().all();*/

        // Send request
        Response response = request.post(Settings.postPath);
        printResponse(response);

        JsonPath jsonPathValidator = response.jsonPath();
        ArrayList<CompareItem> compareItems = new ArrayList<>();
        compareItems.add(new CompareItem(jsonPathValidator.get("id").toString(), id, CompareEnum.EQUALS, "Check ID"));
        compareItems.add(new CompareItem(jsonPathValidator.get("name").toString(), name, CompareEnum.EQUALS, "Check name"));
        compareItems.add(new CompareItem(jsonPathValidator.get("cost").toString(), cost, CompareEnum.EQUALS, "Check cost"));
        compareItems.add(new CompareItem(jsonPathValidator.get("result").toString(), updateMessage, CompareEnum.EQUALS, "Check message"));

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
                Arguments.of("7711", "Имя заказа 1", "56.45", "Update success"),
                Arguments.of("7766", "Имя заказа 2", "88.01", "Update success"),
                Arguments.of("666", "Имя заказа 3", "66.66", "Order not found")
                );
    }
}

/*
        Examples of JSON capabilities (?)

        //2. Count Number of Records(Employees) in the Response
        List < String > jsonResponseRoot = jsonPathValidator.getList("$");
        System.out.println("Number of Employees : " + jsonResponseRoot.size());


        //3. Get the list of all the employee names
        List < String > allEmployeeNames = jsonPathValidator.getList("employee_name");
        System.out.println("\n Here is the names of all the employees :\n");
        for (String i: allEmployeeNames) {
            System.out.println(i);
        }

        //4. To Get the list of ages of all the employees
        String employeeAge = jsonPathValidator.getString("employee_age");
        System.out.println(employeeAge);


        //5. To get the name of the sixth employee in the list using 2 ways:
        //1.
        String sixthEmployeeName = jsonPathValidator.getString("employee_name[5]");
        System.out.println(sixthEmployeeName);
        //2.
        System.out.println(allEmployeeNames.get(5));
 */
