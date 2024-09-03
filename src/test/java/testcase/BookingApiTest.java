package testcase;

import api.BookingApi;
import model.Booking;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import model.Booking2;
import model.BookingNew;
import org.junit.Test;

public class BookingApiTest {
    @Test
    public void testApi(){
        Booking booking = BookingApi.getSummaryBookingByRoomId(1);
        assertThat(booking.getBookings().size()).isGreaterThanOrEqualTo(1);
    }

    @Test
    public void testBooking(){
        BookingNew booking = BookingApi.getBooking1();
        assertThat(booking.getBookings().size()).isGreaterThanOrEqualTo(1);
        assertThat(booking.getBookings().get(0).getBookingid()).isEqualTo(1);
        assertThat(booking.getBookings().get(0).getRoomid()).isEqualTo(1);
        assertThat(booking.getBookings().get(0).getFirstname()).isEqualTo("James");
        assertThat(booking.getBookings().get(0).getLastname()).isEqualTo("Dean");
    }


    @Test
    public void testUpdateBooking(){
        BookingApi.updateBooking(12);
    }

    @Test
    public void testDelete(){
        BookingApi.deleteBooking(1);
    }
}
