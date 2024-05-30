package com.api_project.home_work;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class TestAPI {

    @BeforeMethod
    public void setUp() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";
    }

    @Test
    public void getPosts() {
        given().log().all()
                .when().get("/posts")
                .then().log().all().statusCode(200);

    }

    @Test
    public void getPostsOne() {
        given().log().all()
                .when().get("/posts/1")
                .then().log().all()
                .assertThat().statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("UserSchemaHW.json"));

    }

    @Test
    public void getPoststOneComments() {
        given().log().all()
                .when().get("/posts/1/comments")
                .then().log().all().statusCode(200);
    }

    @Test
    public void postPosts() {
        File bodyUser = new File("src/test/resources/json/userHomeWork.json");
        given().log().all().header("Content-type", "application/json; charset=UTF-8")
                .body(bodyUser)
                .when().post("/posts")
                .then().log().all()
                .assertThat().statusCode(201)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("UserSchemaHW.json"));
    }

    @Test
    public void putPostsOne() {
        File bodyUserPut = new File("src/test/resources/json/userHomeWorkPut.json");
        given().log().all().header("Content-type", "application/json; charset=UTF-8")  //необхожим обязательно header
                .body(bodyUserPut)
                .when().put("/posts/1")
                .then().log().body().statusCode(200);
    }

    @Test
    public void patchPostsOne() {
        given().log().all().header("Content-type", "application/json; charset=UTF-8")  //необхожим обязательно header
                .body("{\n" +
                        "  \"id\": 1,\n" +
                        "  \"title\":\"HomeWorkPatch\",\n" +
                        "  \"userId\":\"1\"\n" +
                        "}")
                .when().patch("/posts/1")
                .then().log().body().statusCode(200);
    }

    @Test
    public void deletPostsOne() {
        given().log().all()
                .when().delete("/posts/1")
                .then().log().all().statusCode(200);
    }
}
