package stepDefination;

import java.io.IOException;
import io.cucumber.java.Before;


public class Hooks {
	
	
	@Before("@DELETEPLACEAPI")
	public void beforeDeleteAPI() throws IOException {
		if(StepDefination.place_id == null) {
			StepDefination addplace = new StepDefination();
			addplace.base_uri_and_add_place_payload("Justin House", "Kurukkanmoola", "English");
			addplace.user_calls_using_http_method("AddPlaceAPI", "post");
			addplace.api_calls_got_successfull_with_a_status_code(200);
			addplace.verfiy_place_id_created_maps_to_using("Justin House", "GetPlaceAPI");
		}
	}

}
