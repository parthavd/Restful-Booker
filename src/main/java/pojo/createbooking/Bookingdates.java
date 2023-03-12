package pojo.createbooking;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Bookingdates{
    public String checkin;

    public String getCheckin() {
        return checkin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bookingdates that = (Bookingdates) o;
        return checkin.equals(that.checkin) && checkout.equals(that.checkout);
    }

    @Override
    public int hashCode() {
        return Objects.hash(checkin, checkout);
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String checkout;
}