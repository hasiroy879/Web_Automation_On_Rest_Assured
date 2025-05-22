import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.configuration.ConfigurationException;
import org.junit.jupiter.api.Test;
import utils.Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;


public class DailyFinance {
    Properties prop;
    public DailyFinance() throws IOException {
        prop = new Properties();
        FileInputStream fs = new FileInputStream("./src/test/resources/config.properties");
        prop.load(fs);
    }

    @Test
    public void adminLogin() throws ConfigurationException {
        RestAssured.baseURI = prop.getProperty("baseUrl");
        Response res = given().contentType("application/json")
                .body("{\n" +
                        "  \"email\": \"admin@test.com\",\n" +
                        "  \"password\": \"admin123\"\n" +
                        "}")
                .when().post("/api/auth/login");

        //System.out.println(res.asString());
        JsonPath jsonObj = res.jsonPath(); // jsonpath string k obj e convert kore
        String token = jsonObj.get("token");
        System.out.println(token);
        Utils.setEnvVar("token", token);
    }

    @Test
    public void UserList(){
        RestAssured.baseURI = prop.getProperty("baseUrl");
        Response res = given().contentType("application/json").
                header("Authorization", "Bearer "+prop.getProperty("token"))
                        .when().get("/api/user/users");

        System.out.println(res.asString());
    }

    @Test
    public void SearchUser(){
        RestAssured.baseURI = prop.getProperty("baseUrl");
        Response res = given().contentType("application/json").
                header("Authorization", "Bearer "+prop.getProperty("token"))
                         .when().get("/api/user/534642e3-880c-4c29-8da5-97ade5db1112");

        System.out.println(res.asString());
    }

    @Test
    public void EditUserInfo(){
        RestAssured.baseURI = prop.getProperty("baseUrl");
        Response res = given().contentType("application/json").
                header("Authorization", "Bearer "+prop.getProperty("token"))
                .body("{\n" +
                        "        \"_id\": \"001d6759-bac0-4f57-8d3b-0a3acd3f171a\",\n" +
                        "        \"firstName\": \"Farhana\",\n" +
                        "        \"lastName\": \"Tillman\",\n" +
                        "        \"email\": \"Ludwig_Schroeder0@gmail.com\",\n" +
                        "        \"password\": \"$2a$10$jpX/rpQ9OZdnrHhmm/afFehY45mkUWIK466a55HQ2z9/UNbjhpfI.\",\n" +
                        "        \"phoneNumber\": \"01906282940\",\n" +
                        "        \"address\": \"Yeseniastad\",\n" +
                        "        \"gender\": \"Male\",\n" +
                        "        \"termsAccepted\": true,\n" +
                        "        \"role\": \"user\",\n" +
                        "        \"profileImage\": \"/uploads/profileImage-1737683597483.png\",\n" +
                        "        \"resetPasswordToken\": \"1ab82c31ace20197f7ead62405f0f0d8bf39855ef88f425599d3006e380a440b\",\n" +
                        "        \"resetPasswordExpire\": \"2025-01-24 02:58:43\",\n" +
                        "        \"createdAt\": \"2025-01-24T01:53:11.000Z\",\n" +
                        "        \"updatedAt\": \"2025-01-24T01:58:43.000Z\"\n" +
                        "    }")
                .when().put("/api/user/001d6759-bac0-4f57-8d3b-0a3acd3f171a");
        System.out.println(res.asString());

    }

    @Test
    public void LoginAnyUser() throws ConfigurationException {
            RestAssured.baseURI = prop.getProperty("baseUrl");
            Response res = given().contentType("application/json")
                    .body("{\n" +
                            "    \"email\": \"Rhianna_Turcotte37@hotmail.com\",\n" +
                            "    \"password\": \"1234\"\n" +
                            "}")
                    .when().post("/api/auth/login");

        System.out.println(res.asString());

    }

    @Test
    public void itemList(){
        RestAssured.baseURI = prop.getProperty("baseUrl");
        Response res = given().contentType("application/json").
                header("Authorization", "Bearer "+prop.getProperty("token"))
                .when().get("/api/costs");

        System.out.println(res.asString());

    }


    @Test
    public void addItem(){
        RestAssured.baseURI = prop.getProperty("baseUrl");
        Response res = given().contentType("application/json").
                header("Authorization", "Bearer "+prop.getProperty("token"))
                .body("{\n" +
                        "\"itemName\":\"Fan\",\n" +
                        "\"quantity\":1,\n" +
                        "\"amount\":\"999\",\n" +
                        "\"purchaseDate\":\"2025-05-12\",\n" +
                        "\"month\":\"May\",\n" +
                        "\"remarks\":\"N/A\"\n" +
                        "}")
                .when().post("/api/costs");

        System.out.println(res.asString());

    }

    @Test
    public void editItemName(){
        RestAssured.baseURI = prop.getProperty("baseUrl");
        Response res = given().contentType("application/json").
                header("Authorization", "Bearer "+prop.getProperty("token"))
                .body("{\n" +
                        "        \"_id\": \"17bea143-2cd9-4018-9c00-61a7c54f7d01\",\n" +
                        "        \"itemName\": \"Table\",\n" +
                        "        \"quantity\": 2,\n" +
                        "        \"amount\": 297,\n" +
                        "        \"purchaseDate\": \"2025-05-10T00:00:00.000Z\",\n" +
                        "        \"month\": \"May\",\n" +
                        "        \"remarks\": \"N/A\",\n" +
                        "        \"userId\": \"61cfc9b8-469e-4edf-a0bf-8860a9783d9d\",\n" +
                        "        \"createdAt\": \"2025-05-10T06:25:23.000Z\",\n" +
                        "        \"updatedAt\": \"2025-05-12T10:04:59.000Z\"\n" +
                        "    }")
                .when().put("/api/costs/17bea143-2cd9-4018-9c00-61a7c54f7d01");

        System.out.println(res.asString());
    }

    @Test
    public void deleteItem(){
        RestAssured.baseURI = prop.getProperty("baseUrl");
        Response res = given().contentType("application/json").
                header("Authorization", "Bearer "+prop.getProperty("token"))
                .when().delete("/api/costs/307c5713-1d84-4ab4-87c8-484e03fc0e7b");

        System.out.println(res.asString());

    }
}
