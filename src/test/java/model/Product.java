package model;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Getter
@Setter
public class Product {
    public int id;
    public String name;

    public Product(){}

    public Product(int id) throws IOException {
        InputStream is = new FileInputStream("src/test/resources/config/product.json");
        ObjectMapper objectMapper = new ObjectMapper();
        Product[] products = objectMapper.readValue(is, Product[].class);
        for(Product product: products){
            if(product.getId() == id){
                this.id = id;
                this.name = product.getName();
            }
        }
    }
}
