package iti_normal_auto;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class API_testing {

	
	@Test
	public void cat_fact()
	{
		
		given().
		when().
		get("https://alexwohlbruck.github.io/cat-facts/random").
		then().assertThat().
		body("text",not(empty()));
	
		
		
	}
	
}
