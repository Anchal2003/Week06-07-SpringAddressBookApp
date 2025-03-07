package com.bridgelabz.addressbook.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Use different type of Annotations
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

//Use @table Annotation and set the name
@Table(name = "Address")

//Create a class Address and make it Entity class
public class Address {

    //Use @Id Annotation
    @Id

    //Create private variable to store the details
    private String fullName;
    private String address;
    private String city;
    private String state;
    private int zipcode;
    private String phoneNO;
}
