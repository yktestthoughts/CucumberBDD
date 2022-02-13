package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {
	
	
	public  AddPlace getData(String name, String addr, String lang) {
		
		Location loc = new Location();
		loc.setLat(-38.383494);
		loc.setLng(33.427362);
		
		AddPlace place = new AddPlace();
		place.setLocation(loc);
		place.setAccuracy(50);
		place.setAddress(addr);
		place.setLanguage(lang);
		place.setName(name);
		place.setPhone_number("+91 8078142630");
		place.setWebsite("www.google.com");
		List<String> type = new ArrayList<>();
		type.add("shoe park");
		type.add("shop");
		
		place.setTypes(type);
		
		return place;
	}
	
	public static String deletePayload(String place_id) {
		return "{\r\n"
				+ "    \"place_id\":\""+place_id+"\"\r\n"
				+ "}\r\n"
				+ "";
		
	}

}
