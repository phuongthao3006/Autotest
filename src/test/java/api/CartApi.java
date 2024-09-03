package api;

import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import utils.PropertyUtil;

import java.util.HashMap;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class CartApi {
    private Cookies cookies;

    public CartApi(){}

    public CartApi(Cookies cookies){
        this.cookies = cookies;
    }

    public Cookies getCookies(){
        return cookies;
    }

    public Response addToCart(int productId, int quantity){
        Header header = new Header("content-type", "application/x-www-form-urlencoded");
        Headers headers = new Headers(header);
        HashMap<String, Object> formParams = new HashMap<>();
        formParams.put("product_sku", "");
        formParams.put("product_id", productId);
        formParams.put("quantity", quantity);

        if(cookies == null){
            cookies = new Cookies();
        }

        Properties properties = PropertyUtil.propertyLoader("src/test/resources/config/config.properties");
        Response response = given().
                baseUri(properties.getProperty("base.url")).
                headers(headers).
                formParams(formParams).
                cookies(cookies).
                log().all().
                when().
                post("/?wc-ajax=add_to_cart").
                then().
                log().all().
                extract().
                response();
        if(response.getStatusCode() != 200){
            throw new RuntimeException("Failed to add product" + productId + " to the cart" +
                    ", HTTP Status Code: " + response.getStatusCode());
        }
        this.cookies = response.getDetailedCookies();
        return response;
    }
}
