package services;

import constants.APIConstants;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.PropertyReader;

public class BaseService {

    private static RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();


    public BaseService(){
        PropertyReader propertyReader = new PropertyReader(APIConstants.CONSTANTSFILE);
        String env = System.getProperty("env") == null ? "qa" : System.getProperty("env");
        String url = propertyReader.getKey("booker.env." + env);
        requestSpecBuilder.setBaseUri(url).addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json").log(LogDetail.ALL);
    }

    public void setBody(Object object){
        requestSpecBuilder.setBody(object);
    }

    public void setAuthentication(String token){
        requestSpecBuilder.addHeader("token", token);
    }

    public void setCookieToken(String token){
        requestSpecBuilder.addHeader("Cookie", "token=" + token);
    }

    public Response executeApi(String httpMethod, String endPoint){
        Response response = null;
        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
        responseSpecBuilder.log(LogDetail.ALL);
        RequestSpecification requestSpecification = RestAssured.given().spec(requestSpecBuilder.build());
        switch(httpMethod){
            case "GET":{
                return requestSpecification.when().get(endPoint).then().spec(responseSpecBuilder.build()).extract().response();
            }
            case "POST":{
                return requestSpecification.when().post(endPoint).then().spec(responseSpecBuilder.build()).extract().response();
            }
            case "PUT":{
                return requestSpecification.when().put(endPoint).then().spec(responseSpecBuilder.build()).extract().response();
            }
            case "DELETE":{
                return requestSpecification.when().delete(endPoint).then().spec(responseSpecBuilder.build()).extract().response();
            }
            case "PATCH":{
                return requestSpecification.when().patch(endPoint).then().spec(responseSpecBuilder.build()).extract().response();
            }
        }
        return response;
    }

}
