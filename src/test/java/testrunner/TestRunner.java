package testrunner;

import com.github.javafaker.Faker;
import controller.UserController;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.configuration.ConfigurationException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import setup.Setup;
import setup.UpdateUserModel;
import setup.UserModel;
import utils.Utils;

public class TestRunner extends Setup {

    private UserController userController;
    @BeforeClass
    public void initUserController(){
        userController = new UserController(prop);
    }


    @Test(priority=1, description= "User Registration")
    public void userRegistration() throws ConfigurationException {
        UserModel userModel = new UserModel();
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = "restuser" + Utils.generateRandomNum(100, 999) + "@gmail.com";
        String password = "1234";
        String phoneNumber = "0130" + Utils.generateRandomNum(1000000, 9999999);
        String address = faker.address().fullAddress();
        String gender = Utils.setGender();
        boolean termsAccepted = userModel.isTermsAccepted();

        userModel.setFirstName(firstName);
        userModel.setLastName(lastName);
        userModel.setEmail(email);
        userModel.setPassword(password);
        userModel.setPhoneNumber(phoneNumber);
        userModel.setAddress(address);
        userModel.setGender(gender);
        userModel.setTermsAccepted(termsAccepted);

        Response res = userController.UserRegistration(userModel);
        System.out.println(res.asString());

        JsonPath jsonObj = res.jsonPath(); // jsonpath string k obj e convert kore
        String id = jsonObj.get("_id").toString();
        Utils.setEnvVar("NewUserId", id);
    }

    @Test(priority=1, description= "User Registration with duplicate info")
    public void userRegistrationWithDuplicateInfo() throws ConfigurationException {
        UserModel userModel = new UserModel();
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = "Maegan68@yahoo.com";
        String password = "1234";
        String phoneNumber = "0130" + Utils.generateRandomNum(1000000, 9999999);
        String address = faker.address().fullAddress();
        String gender = Utils.setGender();
        boolean termsAccepted = userModel.isTermsAccepted();

        userModel.setFirstName(firstName);
        userModel.setLastName(lastName);
        userModel.setEmail(email);
        userModel.setPassword(password);
        userModel.setPhoneNumber(phoneNumber);
        userModel.setAddress(address);
        userModel.setGender(gender);
        userModel.setTermsAccepted(termsAccepted);

        Response res = userController.UserRegistration(userModel);
        System.out.println(res.asString());
    }

    @Test(priority = 3, description = "Admin Login")
    public void adminLogin() throws ConfigurationException {
        UserModel userModel = new UserModel();
        userModel.setEmail("admin@test.com");
        userModel.setPassword("admin123");
        Response res = userController.AdminLogin(userModel);
        System.out.println(res.asString());

        JsonPath jsonObj = res.jsonPath(); // jsonpath string k obj e convert kore
        String token = jsonObj.get("token");
        System.out.println(token);
        Utils.setEnvVar("token", token);
    }
   @Test(priority = 4, description = "Admin Login with incorrect email or password")
    public void adminLoginWithWrongCredentials() throws ConfigurationException {
        UserModel userModel = new UserModel();
        userModel.setEmail("adminnnn@test.com");
        userModel.setPassword("admin123");
        Response res = userController.AdminLogin(userModel);
        System.out.println(res.asString());

    }


   @Test(priority = 5, description = "User List")
    public void userList() {
        Response res = userController.UserList();
        System.out.println(res.asString());
    }

    @Test(priority = 6, description = "Search user by user id")
    public void searchUser() {
        Response res = userController.SearchUser(prop.getProperty("UserId"));
        System.out.println(res.asString());

    }

    @Test(priority = 7, description = "Search user by invalid user id")
    public void searchUserWithInvalidId() {
        Response res = userController.SearchUser("fbc3757c-147a-4f19-958e-8492d752cb");
        System.out.println(res.asString());

    }

   @Test(priority = 8, description = "Edit user info")
    public void editUserInfo() {
        UpdateUserModel updateUserModel = new UpdateUserModel();
        String firstName = "Lilima";
        String lastName = "Roy";
        String email = "hasiroy125.cse@gmail.com";
        String phoneNumber = "01717296890";
        String address = "Rangpur Sadar";
        String gender = "Female";

        updateUserModel.setFirstName(firstName);
        updateUserModel.setLastName(lastName);
        updateUserModel.setEmail(email);
        updateUserModel.setPhoneNumber(phoneNumber);
        updateUserModel.setAddress(address);
        updateUserModel.setGender(gender);

        Response res = userController.EditUserInfo(updateUserModel);
        System.out.println(res.asString());
    }

    @Test(priority = 9, description = "Login any user")
    public void loginAnyUser() throws ConfigurationException {
        Response res = userController.LoginAnyUser();
        System.out.println(res.asString());

        JsonPath jsonObj = res.jsonPath();
        String UserToken = jsonObj.get("token");
        System.out.println(UserToken);
        Utils.setEnvVar("UserToken", UserToken);

        String id = jsonObj.get("_id").toString();
        Utils.setEnvVar("UserId", id);

    }

    @Test(priority = 10, description = "Login any user with incorrect email or password")
    public void loginAnyUserWithWrongCredentials() {
        Response res = userController.LoginAnyUserWithWrongCredentials();
        System.out.println(res.asString());

    }

    @Test(priority = 11, description = "Item list")
    public void itemList() {
        Response res = userController.ItemList();
        System.out.println(res.asString());

    }

    @Test(priority = 12, description = "Add item")
    public void addItem() throws ConfigurationException {
        Response res = userController.AddItem();
        System.out.println(res.asString());

        JsonPath jsonObj = res.jsonPath();
        String ItemId = jsonObj.get("_id").toString();
        Utils.setEnvVar("ItemId", ItemId);
    }

    @Test(priority = 13, description = "Edit item name")
    public void editItemName() {
        Response res = userController.EditItemName();
        System.out.println(res.asString());
    }

    @Test(priority = 14, description = "Delete item")
    public void deleteItem() {
        Response res = userController.DeleteItem();
        System.out.println(res.asString());

    }
    @Test(priority = 15, description = "Delete an item that doesn't exist")
    public void deleteItemNotExist() {
        Response res = userController.DeleteItemNotExist();
        System.out.println(res.asString());

    }

}
