package com.api_project;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

/**
 * Unit test for simple App.
 */
public class AppTest {

    @BeforeMethod
    public void setUp() {
        RestAssured.baseURI = "https://reqres.in";
    }

    @Test
    public void getTestIt() {
        given().log().all()
                .when().get("/api/users/2")
                .then().log().all().statusCode(200);
    }

    @Test
    public void createUserIt() {
        given().log().all().body("{\n" +
                        "    \"name\": \"morpheus\",\n" +
                        "    \"job\": \"leader\"\n" +
                        "}")
                .when().post("/api/users")
                .then().log().body().statusCode(201);

    }

    @Test
    public void createUserItJson() {
        File file = new File("src/test/resources/json/user.json");
        given().log().all().contentType(ContentType.JSON).body(file)
                .when().post("/api/users")
                .then().log().body().statusCode(201);

    }

    @Test
    public void updateUserItPut() {
        Map<String, String> bodyPerson = new HashMap<>();
        bodyPerson.put("name", "morpheus");
        bodyPerson.put("job", "zion resident");
        given().log().all().contentType(ContentType.JSON).body(bodyPerson)
                .when().put("/api/users/2")
                .then().log().all().statusCode(200);
    }

    @Test
    public void updateUserItPatch() {
        File file = new File("src/test/resources/json/user.json");
        given().log().all().contentType(ContentType.JSON).body(file)
                .when().patch("/api/users/2")
                .then().log().all().statusCode(200);
    }

    @Test
    public void deleteUserIt() {
        given().log().uri()
                .when().delete("/api/users/2")
                .then().statusCode(204);
    }


}
