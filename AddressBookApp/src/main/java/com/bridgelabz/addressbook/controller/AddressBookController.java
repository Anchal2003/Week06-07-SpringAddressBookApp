package com.bridgelabz.addressbook.controller;

import com.bridgelabz.addressbook.dto.AddressDto;
import com.bridgelabz.addressbook.entity.Address;
import com.bridgelabz.addressbook.repository.AddressBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

//Use @RestController Annotation
@RestController
//Use @RequestMapping Annotation
@RequestMapping("/address")
//Create  a class AddressBookController to handle the api request
public class AddressBookController {

    //Use @Autowired Annotation
    @Autowired
    //Create an object of AddressBookServices class
    private AddressBookRepository useRepository;

    //Use @GetMapping Annotation
    @GetMapping(value = {"", "/"})
    //Create a method to get the message
    public String getMessage() {
        //Return the message
        return "Welcome to Address Book!";
    }

    //Use @PostMapping Annotation
    @PostMapping("/create")
    //Create a method to create record
    public ResponseEntity<String> createRecord(@RequestBody Address address) {
        //call the save method and store the data
        useRepository.save(address);
        //Return the response with HTTPStatus
        return new ResponseEntity<>("Record Added Successfully", HttpStatus.CREATED);
    }

    //Use @GetMapping Annotation to handle all request
    @GetMapping("/all")
    //create a method to get the all records
    public ResponseEntity<List<AddressDto>> getAllAddress() {

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

    //Use @GetMapping Annotation
    @GetMapping("/get/{name}")
    //Create a method to get the record by name
    public ResponseEntity<AddressDto> getDetailByName(@PathVariable String name) {
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

    //Use @DeleteMapping Annotation
    @DeleteMapping("/delete/{name}")
    //Create a method to delete record by name
    public ResponseEntity<String> deleteByName(@PathVariable String name) {
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

    //Use @PutMapping Annotation
    @PutMapping("/update/{name}")
    //Create a method to update record by name
    public ResponseEntity<String> updateRecordByName(@PathVariable String name, @RequestBody Address address) {
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
