package api;

import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import model.User;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.Cookie;
import utils.PropertyUtil;

import java.util.HashMap;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class SignUpApi {
    private Cookies cookies;

    public Cookies getCookies(){
        return cookies;
    }

    private String fetchRegisterNonceValueUsingGroovy(){
        Response response = getAccount();
        return response.htmlPath().getString("**.findAll { it.@name == 'woocommerce-register-nonce' }.@value");
    }

    private String fetchRegisterNonceValueUsingJsoup(){
        Response response = getAccount();
        Document doc = Jsoup.parse(response.body().prettyPrint());
        Element element = doc.selectFirst("#woocommerce-register-nonce");
        return element.attr("value");
    }

    private Response getAccount(){
        Properties properties = PropertyUtil.propertyLoader("src/test/resources/config/config.properties");
        Cookies cookies = new Cookies();
        Response response = given().
                baseUri(properties.getProperty("base.url")).
                cookies(cookies).
                log().all().
                when().
                get("/account").
                then().
                log().all().
                extract().
                response();
        if(response.getStatusCode() != 200){
            throw new RuntimeException("Failed to fetch the account, HTTP Status Code: " + response.getStatusCode());
        }
        return response;
    }

    public Response register(User user){
        Cookies cookies = new Cookies();
        Header header = new Header("content-type", "application/x-www-form-urlencoded");
        Headers headers = new Headers(header);
        HashMap<String, String> formParams = new HashMap<>();
        formParams.put("username", user.getUsername());
        formParams.put("email", user.getEmail());
        formParams.put("password", user.getPassword());
        formParams.put("woocommerce-register-nonce", fetchRegisterNonceValueUsingJsoup());
        formParams.put("register", "Register");

        Properties properties = PropertyUtil.propertyLoader("src/test/resources/config/config.properties");
        Response response = given().
                baseUri(properties.getProperty("base.url")).
                headers(headers).
                formParams(formParams).
                cookies(cookies).
                log().all().
                when().
                post("/account").
                then().
                log().all().
                extract().
                response();
        if(response.getStatusCode() != 302){
            throw new RuntimeException("Failed to register the account, HTTP Status Code: " + response.getStatusCode());
        }
        this.cookies = response.getDetailedCookies();
        return response;
    }
}
