import constants.APIConstants;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pojo.authentication.CreateToken;
import pojo.createbooking.Bookingdates;
import pojo.createbooking.CreateBookingPojo;
import services.Auth;
import services.BookingService;
import services.payloads.CreateBookingPayload;
import utilities.DataGenerator;
import utilities.PropertyReader;

import java.util.ArrayList;
import java.util.List;

public class BookingTest {
    String token;
    int bookingId;

    @BeforeMethod
    public void createToken(){
        PropertyReader propertyReader = new PropertyReader(APIConstants.CONSTANTSFILE);
        String username = propertyReader.getKey("booker.username.env.qa");
        String password = propertyReader.getKey("booker.password.env.qa");
        Auth auth = new Auth();
        CreateToken createTokenPayload = CreateToken.builder().username(username).password(password).build();
        token = auth.createToken(createTokenPayload);
    }

    @Test
    public void createBookingTest(){
        Bookingdates bookingdates = Bookingdates.builder().checkin("2018-01-01").checkout("2019-01-01").build();
        CreateBookingPojo payload = CreateBookingPojo.builder().firstname(DataGenerator.createFullName()).lastname(DataGenerator.createFullName())
                .depositpaid(true).additionalneeds("Breakfast").totalprice(200).bookingdates(bookingdates).build();
        BookingService bookingService = new BookingService();
        Response response = bookingService.createBooking(payload);
        bookingId = response.jsonPath().getInt("bookingid");
        Assert.assertEquals(response.getStatusCode(), APIConstants.STATUS_OK);
        Assert.assertTrue(bookingId > 0);
        System.out.println("New booking ID: " + bookingId);
    }

    @Test
    public void putBookingTest(){
        BookingService bookingService = new BookingService();
        /*Response getResponse = bookingService.getBookingIDs();
        List<Integer> bookingIdList = getResponse.jsonPath().getList("bookingid");
        bookingId = bookingIdList.get(0);*/
        CreateBookingPojo payload = CreateBookingPayload.createBookingPayload();
        Response addBookingResponse = bookingService.createBooking(payload);
        bookingId = addBookingResponse.jsonPath().getInt("bookingid");
        Response putResponse = bookingService.putBooking(payload, bookingId, token);
        Assert.assertEquals(putResponse.getStatusCode(), APIConstants.STATUS_OK);
        CreateBookingPojo responseObject = putResponse.as(CreateBookingPojo.class);
        Assert.assertEquals(responseObject, payload);
    }

    @Test
    public void deleteBooking(){
        BookingService bookingService = new BookingService();
        CreateBookingPojo payload = CreateBookingPayload.createBookingPayload();
        Response addBookingResponse = bookingService.createBooking(payload);
        bookingId = addBookingResponse.jsonPath().getInt("bookingid");
        Response deleteResponse = bookingService.deleteBooking(token, bookingId);
        Assert.assertEquals(deleteResponse.getStatusCode(), APIConstants.STATUS_CREATED);
        Response getResponse = bookingService.getSpecificBookingId(bookingId);
        Assert.assertEquals(getResponse.getStatusCode(), APIConstants.STATUS_NOTFOUND);
    }
}
