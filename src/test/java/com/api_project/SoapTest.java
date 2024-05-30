package com.api_project;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class SoapTest {

    @Test
    public void soapTest() {
        given().contentType("application/soap+xml").body("<soapenv:Envelope xmlns:soapenv=\\\"http://schemas.xmlsoap.org/soap/envelope/\\\" xmlns:web=\\\"http://www.dataaccess.com/webservicesserver/\\\">\\n\" +\n" +
                        "                \"   <soapenv:Header/>\\n\" +\n" +
                        "                \"   <soapenv:Body>\\n\" +\n" +
                        "                \"      <web:NumberToDollars>\\n\" +\n" +
                        "                \"         <web:dNum>10</web:dNum>\\n\" +\n" +
                        "                \"      </web:NumberToDollars>\\n\" +\n" +
                        "                \"   </soapenv:Body>\\n\" +\n" +
                        "                \"</soapenv:Envelope>")
                .when().post("https://www.dataaccess.com/webservicesserver/numberconversion.wso")
                .then().log().all().statusCode(200).extract().response();

    }
}
