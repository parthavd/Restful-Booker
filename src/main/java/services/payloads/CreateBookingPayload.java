package services.payloads;

import pojo.createbooking.Bookingdates;
import pojo.createbooking.CreateBookingPojo;
import utilities.DataGenerator;

public class CreateBookingPayload {

    public static CreateBookingPojo createBookingPayload(){
        Bookingdates bookingdates = Bookingdates.builder().checkin("2018-01-01").checkout("2019-01-01").build();
        CreateBookingPojo payload = CreateBookingPojo.builder().firstname(DataGenerator.createFullName()).lastname(DataGenerator.createFullName())
                .depositpaid(true).additionalneeds("Breakfast").totalprice(200).bookingdates(bookingdates).build();
        return payload;
    }

}
