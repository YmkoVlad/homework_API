package com.api_project;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class BearerAuth {

    String getToken;

    @Test(priority = 1)
    public void login(ITestContext context) {
        Response response = given().contentType(ContentType.JSON).body("{\"username\":\"emilys\",\"password\":\"emilyspass\",\"expiresInMins\": 30}")
                .when().post("https://dummyjson.com/auth/login")
                .then().log().body().statusCode(200).extract().response();
        getToken = response.jsonPath().getString("token");
        context.setAttribute("token", response.jsonPath().getString("token"));
    }

    @Test(priority = 2)
    public void getUserFirst() {
        given()
                .header("Authorization", "Bearer " + getToken)
                .when()
                .get("https://dummyjson.com/auth/me")
                .then().statusCode(200);
    }

    @Test(priority = 3)
    public void getUserSecond(ITestContext context) {
        given()
                .header("Authorization", "Bearer " + context.getAttribute("token"))
                .when()
                .get("https://dummyjson.com/auth/me")
                .then().statusCode(200);
    }


    //testITestContext
    @Test
    public void firsTest(ITestContext context) {
        context.setAttribute("user_id", 5);
    }

    @Test
    public void secondTest(ITestContext context) {
        System.out.println(context.getAttribute("user_id"));
    }
}
