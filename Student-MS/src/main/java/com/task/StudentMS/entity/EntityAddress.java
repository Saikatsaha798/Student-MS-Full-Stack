package com.task.StudentMS.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data                           //Getter and setter
@Entity                         //JPA inclusion
@Table(name="Address")          //Table linking

public class EntityAddress {
    @Id                         //Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)         //Autogenerate ID
    private Long id;

    @NotBlank(message = "Address 1 field cannot be blank.")
    private String addr1;
    private String city;
    private Integer pin;
    private String street;
}
