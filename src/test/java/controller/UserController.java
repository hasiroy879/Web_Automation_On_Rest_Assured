package controller;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import setup.UpdateUserModel;
import setup.UserModel;

import java.util.Properties;

import static io.restassured.RestAssured.given;

public class UserController {
    Properties prop;

    public UserController(Properties prop) {
        this.prop = prop;

    }

    public Response UserRegistration(UserModel userModel) {
        RestAssured.baseURI = prop.getProperty("baseUrl");
        return given().contentType("application/json")
                .body(userModel)
                .when().post("/api/auth/register");
    }

    public Response AdminLogin(UserModel userModel) {
        RestAssured.baseURI = prop.getProperty("baseUrl");
        return given().contentType("application/json")
                .body(userModel)
                .when().post("/api/auth/login");
    }


    public Response UserList() {
        RestAssured.baseURI = prop.getProperty("baseUrl");
        return given().contentType("application/json").
                header("Authorization", "Bearer " + prop.getProperty("token"))
                .when().get("/api/user/users");
    }

    public Response SearchUser(String userId) {
        RestAssured.baseURI = prop.getProperty("baseUrl");
        return given().contentType("application/json").
                header("Authorization", "Bearer " + prop.getProperty("token"))
                .when().get("/api/user/" + userId);
    }

    public Response EditUserInfo(UpdateUserModel updateUserModel) {
        RestAssured.baseURI = prop.getProperty("baseUrl");
        return given().contentType("application/json").
                header("Authorization", "Bearer " + prop.getProperty("token"))
                .body(updateUserModel)
                .when().put("/api/user/fbc3757c-147a-4f19-958e-8492d752cbd1");
    }

    public Response LoginAnyUser() {
        RestAssured.baseURI = prop.getProperty("baseUrl");
        return given().contentType("application/json")
                .body("{\n" +
                        "    \"email\": \"restuser997@gmail.com\",\n" +
                        "    \"password\": \"1234\"\n" +
                        "}")
                .when().post("/api/auth/login");

    }

    public Response LoginAnyUserWithWrongCredentials(){
        RestAssured.baseURI = prop.getProperty("baseUrl");
        return given().contentType("application/json")
                .body("{\n" +
                        "    \"email\": \"restuser900@gmail.com\",\n" +
                        "    \"password\": \"1234\"\n" +
                        "}")
                .when().post("/api/auth/login");

    }

    public Response ItemList() {
        RestAssured.baseURI = prop.getProperty("baseUrl");
        return given().contentType("application/json").
                header("Authorization", "Bearer " + prop.getProperty("UserToken"))
                .when().get("/api/costs");

    }

    public Response AddItem() {
        RestAssured.baseURI = prop.getProperty("baseUrl");
        return given().contentType("application/json").
                header("Authorization", "Bearer " + prop.getProperty("UserToken"))
                .body("{\n" +
                        "\"itemName\":\"realmePhn\",\n" +
                        "\"quantity\":1,\n" +
                        "\"amount\":\"5000\",\n" +
                        "\"purchaseDate\":\"2025-05-15\",\n" +
                        "\"month\":\"May\",\n" +
                        "\"remarks\":\"N/A\"\n" +
                        "}")
                .when().post("/api/costs/");
    }

    public Response EditItemName(){
        RestAssured.baseURI = prop.getProperty("baseUrl");
        return given().contentType("application/json").
                header("Authorization", "Bearer "+prop.getProperty("UserToken"))
                .body("{\n" +
                        "        \"_id\": \"7705881a-9981-4ec7-865a-1c6e6056956c\",\n" +
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
                .when().put("/api/costs/7705881a-9981-4ec7-865a-1c6e6056956c");

    }

    public Response DeleteItem(){
        RestAssured.baseURI = prop.getProperty("baseUrl");
        return given().contentType("application/json").
                header("Authorization", "Bearer "+prop.getProperty("UserToken"))
                .when().delete("/api/costs/bc44497c-aa38-421d-b90e-366ab5642fd0");
    }

    public Response DeleteItemNotExist(){
        RestAssured.baseURI = prop.getProperty("baseUrl");
        return given().contentType("application/json").
                header("Authorization", "Bearer "+prop.getProperty("UserToken"))
                .when().delete("/api/costs/083a54ed-9cb7-4349-9f43-8a88dc01010");
    }
}

