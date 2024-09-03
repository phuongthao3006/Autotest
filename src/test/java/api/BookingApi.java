package api;
import com.google.gson.Gson;
import io.restassured.filter.cookie.CookieFilter;
import io.restassured.response.Response;
import model.Booking;
import model.Booking2;
import model.BookingNew;
import model.MyDate1;
import org.junit.Assert;
import utils.PropertyUtil;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Map;
import java.util.Properties;

import static io.restassured.RestAssured.given;


public class BookingApi {
    public static Booking getSummaryBookingByRoomId(int roomId){
        Properties properties = PropertyUtil.propertyLoader("src/test/resources/config/config.properties");
        Response response = given().baseUri(properties.getProperty("base.api.url"))
                .queryParam("roomid",1)
                .get("/booking/summary")
                .then()
                .extract()
                .response();
        Assert.assertEquals(200, response.getStatusCode());
        Booking booking = response.jsonPath().getObject("$",Booking.class);
        return booking;
    }

    public static String getToken(){
        Properties properties = PropertyUtil.propertyLoader("src/test/resources/config/config.properties");
        Map<String , String > body = Map.of(
                "username" , "admin",
                "password" , "password"
        );
        Map<String, String> cookie = given().baseUri(properties.getProperty("base.api.url"))
                .header("Content-Type","application/json")
                .body(new Gson().toJson(body))
                .post("/auth/login")
                .then()
                .log()
                .all()
                .extract().cookies();
        return cookie.get("token");
    }

    public static void getBooking(){
        Properties properties = PropertyUtil.propertyLoader("src/test/resources/config/config.properties");
        String token = getToken();
        Response response = given().baseUri(properties.getProperty("base.api.url"))
                .cookies("token",token)
                .when()
                .get("/booking");
        Assert.assertEquals(200, response.getStatusCode());
    }

    public static BookingNew getBooking1(){
        Properties properties = PropertyUtil.propertyLoader("src/test/resources/config/config.properties");
        String token = getToken();
        Response response = given().baseUri(properties.getProperty("base.api.url"))
                .cookies("token", token)
                .when()
                .get("/booking")
                .then()
                .extract()
                .response();
        Assert.assertEquals(200, response.getStatusCode());
        BookingNew bookingNew = response.jsonPath().getObject("$", BookingNew.class);
        return bookingNew;
    }

    public static void updateBooking(int bookingid){
        Properties properties = PropertyUtil.propertyLoader("src/test/resources/config/config.properties");
        String token = getToken();
        MyDate1 mydate = MyDate1.builder()
                .checkin(LocalDate.now().minusDays(1).toString())
                .checkout(LocalDate.now().toString()).build();
        Booking2 bookingUpdate = Booking2.builder()
                .bookingid(bookingid)
                .roomid(2)
                .firstname("Thao")
                .lastname("Phuong")
                .depositpaid(true)
                .email("thao@gmail.com")
                .phone("0869339894")
                .bookingdates(mydate).build();
        Response response = given().baseUri(properties.getProperty("base.api.url"))
                .cookies("token", token)
                .pathParams("bookingid", bookingid)
                .header("Content-Type","application/json")
                .body(new Gson().toJson(bookingUpdate))
                .when()
                .put("/booking/{bookingid}")
                .then()
                .log()
                .all()
                .extract()
                .response();

        Assert.assertEquals(200, response.getStatusCode());
    }

    public static void deleteBooking(int bookingid){
        Properties properties = PropertyUtil.propertyLoader("src/test/resources/config/config.properties");
        String token = getToken();
        Response response = given().baseUri(properties.getProperty("base.api.url"))
                .cookies("token", token)
                .pathParams("bookingid", bookingid)
                .when()
                .delete("/booking/{bookingid}")
                .then()
                .log()
                .all()
                .extract()
                .response();
        Assert.assertEquals(200, response.getStatusCode());
    }
}
