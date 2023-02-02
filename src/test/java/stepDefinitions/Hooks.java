package stepDefinitions;

import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {

    @Before("@DeletePlace")
    public void beforeScenario() throws IOException {
        //write a code that will give you place id
        //execute this code only when place is null
        StepDefinition sd = new StepDefinition();
        if(StepDefinition.place_id==null){
            sd.add_place_payload_with_and("Shetty", "French", "Asia");
            sd.user_calls_with_http_request("addPlaceAPI", "post");
            sd.verify_place_id_created_maps_to_using("Shetty", "getPlaceAPI");
        }

    }
}
