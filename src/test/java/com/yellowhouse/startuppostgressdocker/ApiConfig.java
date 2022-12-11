package com.yellowhouse.startuppostgressdocker;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.aeonbits.owner.ConfigFactory;

public class ApiConfig {

    protected static final String BASE_PATH = "";
    protected static final String BASE_PATH_CAPSULES = "/capsules";
    protected static final String BASE_PATH_USERS = "/users";
    protected static final String BASE_PATH_CLOTHES = "/clothes";
    protected static final String BASE_PATH_ORDERS = "/orders";

    protected static final String BASE_URI = "http://localhost";
    protected static final int BASE_PORT = 8080;
    protected static BaseConfig config = ConfigFactory.create(BaseConfig.class,System.getenv());
    protected static final RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .setBasePath(BASE_PATH)
            .setBaseUri(BASE_URI)
            .setPort(BASE_PORT)
            .log(LogDetail.ALL)
            .build();

}
