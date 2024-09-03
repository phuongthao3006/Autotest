package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Booking {
    public ArrayList<BookingDate> bookings;
}

class BookingDate{
    public MyDate1 bookingDates;
}


 class MyDate{
    public Date checkin;
    public Date checkout;
}
