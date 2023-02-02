package resources;

import pojo.AddPlace;
import pojo.Location;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {

    public AddPlace addPlacePayLoad(String name, String language, String address){
        AddPlace ap = new AddPlace();
        Location loc = new Location();

        ap.setAccuracy(50);
        ap.setAddress(address);
        ap.setLanguage(language);
        ap.setPhone_number("(+91) 983 893 3937");
        ap.setWebsite("www.google.com");
        ap.setName(name);
        List<String> myList = new ArrayList<String>();
        myList.add("shoe park");
        myList.add("shop");
        ap.setTypes(myList);

        loc.setLat(-38.383494);
        loc.setLng(33.427362);
        ap.setLocation(loc);
        return ap;
    }

    public String deletePlacePayload(String placeId){
        return "{\n" +
                "    \"place_id\": \""+placeId+"\"\n" +
                "}";
    }

}
