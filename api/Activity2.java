package Activities;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Activity2 {
    final static String ROOT_URL = "https://petstore.swagger.io/v2/user";

    @Test
    public void data() throws IOException {
        // Import JSON file
        File file = new File("C:\\Users\\001ZLV744\\Desktop\\FST\\FST_RestAssured\\src\\test\\java\\Activities\\file.json");
        FileInputStream inputJSON = new FileInputStream(file);
        // Get all bytes from JSON file
        byte[] bytes = new byte[(int) file.length()];
        inputJSON.read(bytes);
        // Read JSON file as String
        String reqBody = new String(bytes, "UTF-8");
        System.out.println(reqBody);
        Response response = given()
                .contentType(ContentType.JSON) // Set headers
                .body(reqBody) // Pass request body from file
                .when().post(ROOT_URL); // Send POST request
        // Print response
        String body = response.getBody().asPrettyString();
        System.out.println(body);
        inputJSON.close();
        // Assertion
        response.then().body("code", equalTo(200));
        response.then().body("message", equalTo("251"));
    }
    @Test(priority = 1)
    public void GetReq(){
        // Import JSON file to write to
        File outputJSON = new File("C:\\Users\\001ZLV744\\Desktop\\FST\\FST_RestAssured\\src\\test\\java\\Activities\\output.json");
        // Send GET Request
        Response r2 = given().contentType(ContentType.JSON) // Set headers
                .when().pathParam("username", "keer")
                .get(ROOT_URL + "/{username}");
        // Print response
        String body = r2.getBody().asPrettyString();
        System.out.println(r2.asPrettyString());
        try {
            // Create JSON file
            outputJSON.createNewFile();
            FileWriter writer = new FileWriter(outputJSON.getPath());
            writer.write(body);
            writer.close();
        } catch (IOException excp) {
            excp.printStackTrace();
        }
        // Assertion
        r2.then().body("id", equalTo(251));
        r2.then().body("username", equalTo("keer"));
        r2.then().body("firstName", equalTo("Justin"));
        r2.then().body("lastName", equalTo("Case"));
        r2.then().body("email", equalTo("justincase@mail.com"));
        r2.then().body("password", equalTo("password123"));
        r2.then().body("phone", equalTo("9812763450"));
    }

    @Test(priority = 2)
    public void DeleteReq(){
        Response r3 = given().contentType(ContentType.JSON) // Set headers
                .when().pathParam("username", "keer")
                .delete(ROOT_URL + "/{username}");

        // Assert DELETE operation
        r3.then().body("code", equalTo(200));
        r3.then().body("message", equalTo("keer"));
    }
}