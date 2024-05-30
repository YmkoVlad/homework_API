package com.api_project;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.matcher.RestAssuredMatchers;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;


public class SchemaValidation {

    @BeforeMethod
    public void setUp() {
        RestAssured.baseURI = "https://reqres.in";
    }


    @Test
    public void testJsonSchemaIT() {
        given().log().all()
                .when().get("/api/users/2")
                .then().log().all().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("jsonschema.json"));

    }

    @Test
    public void updateUserIt() {
        String name = "Petja";
        Map<String, String> bodyInfo = new HashMap<>();
        bodyInfo.put("name", name);
        bodyInfo.put("job", "GoodJob");
        given().log().all().contentType(ContentType.JSON).body(bodyInfo)
                .when().put("/api/users/1")
                .then().log().all().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("updatetest.json"));

    }

    @Test
    public void testXMLSchemaIT() {
        given()
                .get("https://mocktarget.apigee.net/xml")
                .then().log().all().assertThat().body(RestAssuredMatchers.matchesXsdInClasspath("xmltest.xsd"));
    }

}
