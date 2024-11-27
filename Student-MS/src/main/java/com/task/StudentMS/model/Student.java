package com.task.StudentMS.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.hibernate.validator.constraints.Range;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data                               //Getter and setter
@AllArgsConstructor                 //Constructor with all arguments
@NoArgsConstructor                  //Constructor with no arguments

@Component                          //Creates bean
public class Student {
    private Long id;

    private String name;
    private Integer stuClass;

    @Range(min=10, max=40, message = "Age for student should be between 10 and 40.")          //Validation
    private Integer age;

    @Autowired                      //Dependency injection
    private Address address;
}
