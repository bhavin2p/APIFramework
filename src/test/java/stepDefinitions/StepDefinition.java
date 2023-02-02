package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;
import pojo.AddPlace;
import pojo.Location;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class StepDefinition extends Utils {
    RequestSpecification res;
    ResponseSpecification resspec;
    Response response;
    static String place_id;
    TestDataBuild data = new TestDataBuild();
    @Given("Add Place Payload with {string}, {string} and {string}")
    public void add_place_payload_with_and(String name, String lang, String address) throws IOException {
        res=given().spec(requestSpecification())
                .body(data.addPlacePayLoad(name, lang, address));
    }
    @When("user calls {string} with {string} http request")
    public void user_calls_with_http_request(String resource, String method){
        APIResources resourceAPI = APIResources.valueOf(resource);

        if(method.equalsIgnoreCase("Post")){
            response = res.when().post(resourceAPI.getResource());
        } else if(method.equalsIgnoreCase("Get")){
            response = res.when().get(resourceAPI.getResource());
        }


    }
    @Then("the API call is sucess with status code {int}")
    public void the_api_call_is_sucess_with_status_code(Integer statusCode) {
        Assert.assertEquals(response.getStatusCode(), 200);

    }
    @Then("{string} in response body is {string}")
    public void in_response_body_is(String keyValue, String expectedValue) {
        Assert.assertEquals(getJsonPath(response, keyValue), expectedValue);
    }

    @Then("verify place_id created maps to {string} using {string}")
    public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {

        place_id=getJsonPath(response,"place_id");
        res=given().spec(requestSpecification()).queryParam("place_id",place_id);
        user_calls_with_http_request(resource,"GET");
        String actualName=getJsonPath(response,"name");
        Assert.assertEquals(actualName,expectedName);
    }

    @Given("DeletePlace payload")
    public void delete_place_payload() throws IOException {
        System.out.println("Place ID = " +place_id);
        res =given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
    }
}
