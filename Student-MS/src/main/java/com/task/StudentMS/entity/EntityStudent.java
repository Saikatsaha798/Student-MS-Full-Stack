package com.task.StudentMS.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data                                   //Getter and setter
@Entity                                 //JPA inclusion
@Table(name="Student")                  //Table linking
public class EntityStudent {
    @Id                                 //Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)         //Autogenerate ID
    private Long id;

    @NotBlank(message = "Name cannot be blank.")
    private String name;
    @NotNull(message = "Class cannot be blank.")
    private Integer stuClass;
    @NotNull(message = "Age cannot be blank.")
    private Integer age;

    @OneToOne
    private EntityAddress address;
}
