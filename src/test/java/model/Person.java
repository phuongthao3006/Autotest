package model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Person {
    String name, position, office,salary;
    int age;
    Date startDate;

}
