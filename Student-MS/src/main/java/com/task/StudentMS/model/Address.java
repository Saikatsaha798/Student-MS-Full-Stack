package com.task.StudentMS.model;



import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data                       //Getter and Setters
@AllArgsConstructor         //Constructor with all arguments
@NoArgsConstructor          //Constructor with no arguments

@Component
public class Address {
    private String addr1;
    private String city;
    private Integer pin;
    private String street;
}
