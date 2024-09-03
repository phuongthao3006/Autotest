package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MyBooking {
    public int bookingid;
    public int roomid;
    public String firstname;
    public String lastname;
    public boolean depositpaid;
    public MyDate1 bookingdates;
}
