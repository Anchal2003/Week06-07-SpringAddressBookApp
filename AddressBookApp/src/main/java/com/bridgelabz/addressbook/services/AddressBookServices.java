package com.bridgelabz.addressbook.services;

import com.bridgelabz.addressbook.dto.AddressDto;
import com.bridgelabz.addressbook.entity.Address;
import com.bridgelabz.addressbook.repository.AddressBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public ResponseEntity<String> addRecord(Address address) {
        //call the save method and store the data
        useRepository.save(address);
        //Return the response with HTTPStatus
        return new ResponseEntity<>("Record Added Successfully", HttpStatus.CREATED);
    }

    //Create a method to fetch the all record from the database
    public ResponseEntity<List<AddressDto>> findAllRecord() {

        //Create a list to store the all records
        List<Address> allRecords = useRepository.findAll();

        //Create a  list to store the object of AddressDto class
        List<AddressDto> allAddressDto = new ArrayList<>();

        //Use for loop to iterate list
        for (Address val : allRecords) {
            //add the value into the list
            allAddressDto.add(new AddressDto(val.getFullName(), val.getAddress(), val.getCity(), val.getState(), val.getZipcode(), val.getPhoneNO()));
        }
        //Check the condition
        if (allRecords.isEmpty()) {
            //Return the response and HTTPStatus
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            //Return the response and HTTPStatus
            return new ResponseEntity<>(allAddressDto, HttpStatus.OK);
        }
    }

    //Create  a method to fetch the record by name
    public ResponseEntity<AddressDto> findRecordByName(String name) {
        //Create a reference variable of address class
        Address record = useRepository.findById(name).orElse(null);

        //Check the condition
        if (record != null) {

            //Create an object of AddressDto class and call the getter setter
            AddressDto addressDto = new AddressDto(record.getFullName(), record.getAddress(), record.getCity(), record.getState(), record.getZipcode(), record.getPhoneNO());
            //Return the response and HTTPStatus
            return new ResponseEntity<>(addressDto, HttpStatus.FOUND);
        } else {
            //Return the response and HTTPStatus
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Create a method to delete record from the database using name
    public ResponseEntity<String> deleteRecord(String name) {

        //check the condition
        if (useRepository.findById(name).orElse(null) == null) {
            //Return the response and HTTPStatus
            return new ResponseEntity<>("NO Record Found", HttpStatus.NOT_FOUND);
        } else {
            useRepository.deleteById(name);
            //Return the response and HTTPStatus
            return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
        }
    }

    //Create a method to update records by name
    public ResponseEntity<String> updateRecord(String name, Address address) {
        //Check the condition
        if (useRepository.findById(name).orElse(null) == null) {
            //Return the response and HttpStatus
            return new ResponseEntity<>("NO Record Found", HttpStatus.NOT_FOUND);
        } else {
            //set the value
            address.setFullName(name);
            //call the save method
            useRepository.save(address);
            //Return the response and HttpStatus
            return new ResponseEntity<>("Record Updated Successfully", HttpStatus.OK);
        }
    }
}
