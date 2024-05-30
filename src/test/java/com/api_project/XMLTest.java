package com.api_project;


import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


public class XMLTest {

    @Test
    public void validateXML() {
        Response response = given().get("https://mocktarget.apigee.net/xml")
                .then().statusCode(200).log().all().contentType(ContentType.XML).extract().response();
        System.out.println(response.xmlPath().getString("root.city"));

        XmlPath xmlPath = new XmlPath(response.asString());
        System.out.println(xmlPath.getString("root.state"));
    }

    @Test
    public void validateXMLString() {
        String response = given().get("https://mocktarget.apigee.net/xml")
                .xmlPath().getString("root.city");
        System.out.println(response);
    }


}
