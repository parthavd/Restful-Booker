package services;


import constants.APIConstants;
import io.restassured.response.Response;

public class Auth extends BaseService{
    public String createToken(Object payload){
        setBody(payload);
        Response response = executeApi("POST", APIConstants.CREATE_TOKEN);
        return response.jsonPath().getString("token");
    }

}
