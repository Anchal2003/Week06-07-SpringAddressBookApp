package com.bridgelabz.addressbook.services;

import com.bridgelabz.addressbook.entity.Address;
import com.bridgelabz.addressbook.repository.AddressBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

//Use @Service Annotations
@Service
//Create a class AddressBookServices to indicate the services of the address book app
public class AddressBookServices {

    //Use the @Autowired Annotation
    @Autowired
    //Create an object of AddressBookRepository
    private AddressBookRepository useRepository;

    //Create a method to add records in the database
    public String addRecord(Address address) {
        //call the save method and store the data
        useRepository.save(address);
        //Return the message of save data
        return "Record Added Successfully";
    }

    //Create a method to fetch the all record from the database
    public List<Address> findAllRecord() {
        //return the records
        return useRepository.findAll();
    }

    //Create  a method to fetch the record by name
    public Address findRecordByName(String name) {
        //return the value
        return  useRepository.findById(name).orElse(null);
    }
}
