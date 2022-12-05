package Activities;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Activity1 {

    String ROOT_URL = "https://petstore.swagger.io/v2/pet";

   @Test(priority = 0)
    public void PostReq(){
        String reqBody = "{\"id\": 1515,\"name\": \"Tommo\",\"status\": \"alive\"}";
        Response response =
                given().contentType(ContentType.JSON) // Set headers
                        .body(reqBody).when().post(ROOT_URL); // Send POST request
        // Print response of POST request
        String body = response.getBody().asPrettyString();
        System.out.println(body);
       // Assertion
       response.then().body("id", equalTo(1515));
       response.then().body("name", equalTo("Tommo"));
       response.then().body("status", equalTo("alive"));
    }

    @Test(priority = 1)
    public void GetReq(){
        // Send GET Request
        Response r2 = given().contentType(ContentType.JSON) // Set headers
                .when().get(ROOT_URL + "/1515"); // Get pet details with GET
        // Print response
        System.out.println(r2.asPrettyString());
        // Assertion
        r2.then().body("id", equalTo(1515));
        r2.then().body("name", equalTo("Tommo"));
        r2.then().body("status", equalTo("alive"));
    }

    @Test(priority = 2)
    public void DeleteReq(){
        Response r3 =
                given().contentType(ContentType.JSON) // Set headers
                        .when().delete(ROOT_URL + "/1515"); // Send DELETE request

        // Assert DELETE operation
        r3.then().body("code", equalTo(200));
        r3.then().body("message", equalTo("1515"));
    }

}