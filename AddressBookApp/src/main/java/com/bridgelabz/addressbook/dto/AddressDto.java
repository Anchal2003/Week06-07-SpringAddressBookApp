package com.bridgelabz.addressbook.dto;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Use Annotations
@Data
@AllArgsConstructor
@NoArgsConstructor
//Create an AddressDto Class
public class AddressDto {

    //Create private variable to store the details
    private String fullName;
    private String address;
    private String city;
    private String state;
    private int zipcode;
    private String phoneNO;
}
