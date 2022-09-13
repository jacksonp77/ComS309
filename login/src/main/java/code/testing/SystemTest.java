package code.testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

@RunWith(SpringRunner.class)
public class SystemTest {

  @LocalServerPort
	static int port = 8080;

	@Before
	public void setUp() {
		RestAssured.port = port;
		RestAssured.baseURI = "http://localhost";
	}
	
	
	@Test
	public void registerTest() {
		Response response = RestAssured.given()
				.header("Content-Type","application/json")
				.header("Accept","application/json")
				.body("{\n" + 
						"	\"username\" : \"bob\"\n" +
						"	\"email\" : \"email@email.com\", \n" + 
						"	\"password\" : \"pw\"\n" + 
						"	\"role\" : \"USER\"\n" +
						"}")
				.when()
				.post("/user")
				.then()
				.extract().response();
			
			int statusCode = response.getStatusCode();
			assertEquals(200, statusCode);
			
			String returnString = response.getBody().asString();
			try {
				JSONArray returnArr = new JSONArray(returnString);
				JSONObject returnObj = returnArr.getJSONObject(returnArr.length()-1);
				assertEquals("success", returnObj.get("data"));
			} catch(JSONException e) {
				e.printStackTrace();
			}
	}
	
	@Test
	public void registerTest2() {
		Response response = RestAssured.given()
				.header("Content-Type","application/json")
				.header("Accept","application/json")
				.body("{\n" + 
						"	\"username\" : \"bobby\"\n" +
						"	\"email\" : \"bob@email.com\", \n" + 
						"	\"password\" : \"pw\"\n" + 
						"	\"role\" : \"ADMIN\"\n" +
						"}")
				.when()
				.post("/user")
				.then()
				.extract().response();
			
			int statusCode = response.getStatusCode();
			assertEquals(200, statusCode);
			
			String returnString = response.getBody().asString();
			try {
				JSONArray returnArr = new JSONArray(returnString);
				JSONObject returnObj = returnArr.getJSONObject(returnArr.length()-1);
				assertEquals("success", returnObj.get("data"));
			} catch(JSONException e) {
				e.printStackTrace();
			}
	}
	
	@Test
	public void registerTest3() {
		Response response = RestAssured.given()
				.header("Content-Type","application/json")
				.header("Accept","application/json")
				.body("{\n" + 
						"	\"username\" : \"rob\"\n" +
						"	\"email\" : \"bob1@email.com\", \n" + 
						"	\"password\" : \"pw\"\n" + 
						"	\"role\" : \"MODERATOR\"\n" +
						"}")
				.when()
				.post("/user")
				.then()
				.extract().response();
			
			int statusCode = response.getStatusCode();
			assertEquals(200, statusCode);
			
			String returnString = response.getBody().asString();
			try {
				JSONArray returnArr = new JSONArray(returnString);
				JSONObject returnObj = returnArr.getJSONObject(returnArr.length()-1);
				assertEquals("success", returnObj.get("data"));
			} catch(JSONException e) {
				e.printStackTrace();
			}
	}
	
	
	@Test
	public void loginTest() {
		Response response = RestAssured.given()
			.header("Content-Type","application/json")
			.header("Accept","application/json")
			.body("{\n" + 
					"	\"email\" : \"email@email.com\", \n" + 
					"	\"password\" : \"pw\"\n" + 
					"}")
			.when()
			.post("/login")
			.then()
			.extract().response();
		
		int statusCode = response.getStatusCode();
		assertEquals(200, statusCode);
		
		String returnString = response.getBody().asString();
		try {
			JSONArray returnArr = new JSONArray(returnString);
			JSONObject returnObj = returnArr.getJSONObject(returnArr.length()-1);
			assertEquals("{\"message\":\"success\"}", returnObj.get("data"));
		} catch(JSONException e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void listTest() {
		Response response = RestAssured.given()
				.header("Content-Type","application/json")
				.header("Accept","application/json")
				.when()
				.get("/user")
				.then()
				.extract().response();
			
			int statusCode = response.getStatusCode();
			assertEquals(200, statusCode);
			
			String returnString = response.getBody().asString();
			try {
				JSONArray returnArr = new JSONArray(returnString);
				JSONObject returnObj = returnArr.getJSONObject(returnArr.length()-1);
				assertEquals(returnString, returnObj.get("data"));
			} catch(JSONException e) {
				e.printStackTrace();
			}
	}
	
	@Test
	public void deleteTest() {
		Response response = RestAssured.given()
				.header("Content-Type","application/json")
				.header("Accept","application/json")
				.when()
				.delete("/user/2")
				.then()
				.extract().response();
			
		int statusCode = response.getStatusCode();
		assertEquals(200, statusCode);
		
		String returnString = response.getBody().asString();
		try {
			JSONArray returnArr = new JSONArray(returnString);
			JSONObject returnObj = returnArr.getJSONObject(returnArr.length()-1);
			assertEquals("{\"message\":\"success\"}", returnObj.get("data"));
		} catch(JSONException e) {
			e.printStackTrace();
		}
	}


	@Test
	public void friendTest() {
		Response response = RestAssured.given()
				.header("Content-Type","application/json")
				.header("Accept","application/json")
				.body("{\n" + 
						"	\"email\" : \"email@email.com\", \n" + 
						"[" + 
						"	\"email\" : \"bob@email.com\", \n" + 
						"]" + 
						"}")
				.when()
				.post("/userFriendRequest")
				.then()
				.extract().response();
			
		int statusCode = response.getStatusCode();
		assertEquals(200, statusCode);
		
		String returnString = response.getBody().asString();
		try {
			JSONArray returnArr = new JSONArray(returnString);
			JSONObject returnObj = returnArr.getJSONObject(returnArr.length()-1);
			assertEquals(returnString, returnObj.get("data"));
		} catch(JSONException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void friendListTest() {
		Response response = RestAssured.given()
				.header("Content-Type","application/json")
				.header("Accept","application/json")
				.body("{\n" + 
						"	\"email\" : \"email@email.com\", \n" + 
						"}")
				.when()
				.post("/userFriendList")
				.then()
				.extract().response();
			
		int statusCode = response.getStatusCode();
		assertEquals(200, statusCode);
		
		String returnString = response.getBody().asString();
		try {
			JSONArray returnArr = new JSONArray(returnString);
			JSONObject returnObj = returnArr.getJSONObject(returnArr.length()-1);
			assertEquals(returnString, returnObj.get("data"));
		} catch(JSONException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void addGameTest() {
		Response response = RestAssured.given()
				.header("Content-Type","application/json")
				.header("Accept","application/json")
				.body("{\n" + 
						"	\"gameName\" : \"halo\", \n" + 
						"}")
				.when()
				.post("/addGame")
				.then()
				.extract().response();
			
		int statusCode = response.getStatusCode();
		assertEquals(200, statusCode);
		
		String returnString = response.getBody().asString();
		try {
			JSONArray returnArr = new JSONArray(returnString);
			JSONObject returnObj = returnArr.getJSONObject(returnArr.length()-1);
			assertEquals("{\"message\":\"success\"}", returnObj.get("data"));
		} catch(JSONException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void listGamesTest() {
		Response response = RestAssured.given()
				.header("Content-Type","application/json")
				.header("Accept","application/json")
				.when()
				.get("/listgames")
				.then()
				.extract().response();
			
			int statusCode = response.getStatusCode();
			assertEquals(200, statusCode);
			
			String returnString = response.getBody().asString();
			try {
				JSONArray returnArr = new JSONArray(returnString);
				JSONObject returnObj = returnArr.getJSONObject(returnArr.length()-1);
				assertEquals("{\"message\":\"success\"}", returnObj.get("data"));
			} catch(JSONException e) {
				e.printStackTrace();
			}
	}
	@Test
	public void rotateOutTest() {
		Response response = RestAssured.given()
				.header("Content-Type","application/json")
				.header("Accept","application/json")
				.when()
				.get("/rotatein/1/2")
				.then()
				.extract().response();
			
			int statusCode = response.getStatusCode();
			assertEquals(200, statusCode);
			
			String returnString = response.getBody().asString();
			try {
				JSONArray returnArr = new JSONArray(returnString);
				JSONObject returnObj = returnArr.getJSONObject(returnArr.length()-1);
				assertEquals("{\"message\":\"success\"}", returnObj.get("data"));
			} catch(JSONException e) {
				e.printStackTrace();
			}
	}

}
