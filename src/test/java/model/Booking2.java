package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class Booking2 {
    public int bookingid;
    public int roomid;
    public String firstname;
    public String lastname;
    public boolean depositpaid;
    public String email;
    public String phone;
    public MyDate1 bookingdates;
}
