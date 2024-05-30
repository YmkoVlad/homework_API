package com.api_project;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class JsonTest {

    @BeforeMethod
    public void setUp() {
        RestAssured.baseURI = "https://reqres.in";
    }

    @Test
    public void testJsonResponseIt() {
        Response response = given().log().all()
                .when().get("/api/users/2")
                .then().log().all().extract().response();
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.getHeader("Connection"), "keep-alive");
        System.out.println("ID: " + response.jsonPath().getInt("data.id"));
        System.out.println("FIRST NAME: " + response.jsonPath().getString("data.first_name"));
    }

    @Test
    public void updateUserItPutRespons() {
        String name = "Kesha";
        Map<String, String> bodyPerson = new HashMap<>();
        bodyPerson.put("name", name);
        bodyPerson.put("job", "zion resident");
        String responsName = given().log().all().contentType(ContentType.JSON).body(bodyPerson)
                .when().put("/api/users/1")
                .jsonPath().getString("name");
        Assert.assertEquals(name, responsName);
    }


}
