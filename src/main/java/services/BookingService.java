package services;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import constants.APIConstants;
import io.restassured.response.Response;


public class BookingService extends BaseService{
    public Response createBooking(Object payload) {
        setBody(payload);
        Response response = executeApi("POST", APIConstants.CREATE_BOOKING);
        return response;
    }

     public int getBookingIdAfterCreateBooking(Object payload){
        Response response = createBooking(payload);
         return response.jsonPath().getInt("bookingid");
     }

     public Response putBooking(Object payload, int bookingId, String token){
         setBody(payload);
         setCookieToken(token);
         Response response = executeApi("PUT", APIConstants.CREATE_BOOKING + "/" + bookingId);
         return response;
     }

     public Response getBookingIDs(){
         Response response = executeApi("GET", APIConstants.GET_BOOKING);
         return response;
     }

    public Response getSpecificBookingId(int bookingId){
        Response response = executeApi("GET", APIConstants.GET_BOOKING+ "/" + bookingId);
        return response;
    }

     public Response deleteBooking(String token, int bookingId){
        setCookieToken(token);
        return executeApi("DELETE", APIConstants.CREATE_BOOKING + "/" + bookingId);
     }
}
