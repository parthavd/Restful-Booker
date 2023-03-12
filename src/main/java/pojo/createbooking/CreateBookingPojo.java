package pojo.createbooking;

import lombok.*;

import java.util.Objects;

@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookingPojo {
    public String firstname;
    public String lastname;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreateBookingPojo)) return false;
        CreateBookingPojo that = (CreateBookingPojo) o;
        return totalprice == that.totalprice && depositpaid == that.depositpaid && firstname.equals(that.firstname) && lastname.equals(that.lastname) && bookingdates.equals(that.bookingdates) && additionalneeds.equals(that.additionalneeds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, lastname, totalprice, depositpaid, bookingdates, additionalneeds);
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(int totalprice) {
        this.totalprice = totalprice;
    }

    public boolean isDepositpaid() {
        return depositpaid;
    }

    public void setDepositpaid(boolean depositpaid) {
        this.depositpaid = depositpaid;
    }

    public Bookingdates getBookingdates() {
        return bookingdates;
    }

    public void setBookingdates(Bookingdates bookingdates) {
        this.bookingdates = bookingdates;
    }

    public String getAdditionalneeds() {
        return additionalneeds;
    }

    public void setAdditionalneeds(String additionalneeds) {
        this.additionalneeds = additionalneeds;
    }

    public int totalprice;
    public boolean depositpaid;
    public Bookingdates bookingdates;
    public String additionalneeds;
}
