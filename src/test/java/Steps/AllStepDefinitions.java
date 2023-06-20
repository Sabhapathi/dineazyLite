package Steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import resources.Data;
import resources.Utils;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class AllStepDefinitions extends Utils {


    Response response;
    String accessToken;
    String OTP;
    String email;
    String phone;
    String OTPToken;
    Data data = new Data();

    @Given("^user login with Credentials \"([^\"]*)\" and \"([^\"]*)\"$")
    public void user_login_with_credentials(String username, String pwd) throws Throwable {

        req=given().spec(RequestSpecification())
                .body(data.LoginCredentials(username,pwd));
        response = req.when().post("/api/v2/auth/login").then().spec(ResponseSpecification()).extract().response();
        assertEquals(response.getStatusCode(),201);
        accessToken=getJsonPathValue(response,"data.token");

    }

    @Given("^user login to Dineasy$")
    public void user_login_to_Dineasy() throws Throwable {

        req=given().spec(RequestSpecification())
                .body(data.LoginCredentials(getGlobalValue("username"),getGlobalValue("password")));
        response = req.when().post("/api/v2/auth/login").then().spec(ResponseSpecification()).extract().response();
        assertEquals(response.getStatusCode(),201);

        accessToken=getJsonPathValue(response,"data.token");

    }

    @When("^user gets the profile$")
    public void user_gets_the_profile() throws Throwable {
        response = given().spec(RequestSpecification()).header("Authorization","Bearer "+accessToken)
                .when().get("/api/v2/auth/profile")
                .then().assertThat().statusCode(200).extract().response();
    }

    @Then("^user should verify that \"([^\"]*)\" profile is found$")
    public void user_should_verify_that_something_is_found(String expectedprofilename) throws Throwable {

        assertEquals(expectedprofilename,getJsonPathValue(response,"data.firstName"));
    }

    @When("^user sends OTP$")
    public void user_sends_OTP() throws Throwable {
        req=given().spec(RequestSpecification())
                .body(data.OTPDetails(getGlobalValue("firstname"),getGlobalValue("lastname"),getGlobalValue("username"),getGlobalValue("phonenumber"),getGlobalValue("password")));
        response = req.when().post("/api/v2/auth/otp/send").then().spec(ResponseSpecification()).extract().response();
        assertEquals(response.getStatusCode(),201);
        email=getJsonPathValue(response,"data.email");
        phone=getJsonPathValue(response,"data.phone");
        OTP=getJsonPathValue(response,"data.otp");
    }

    @Then("^user should verify OTP sent is correct$")
    public void user_should_verify_OTP_sent_is_correct() throws Throwable {
        req=given().spec(RequestSpecification())
                .body(data.verifyOTP(email,phone,OTP));
        response = req.when().post("/api/v2/auth/otp/verify").then().spec(ResponseSpecification()).extract().response();
        String ActualMessage=getJsonPathValue(response,"message");
        assertEquals("Otp verified successfully.",ActualMessage);
        assertEquals(response.getStatusCode(),201);
        OTPToken=getJsonPathValue(response,"data.token");

    }

}
