package com.bridgelabz.addressbook.controller;

import com.bridgelabz.addressbook.dto.AddressDto;
import com.bridgelabz.addressbook.entity.Address;
import com.bridgelabz.addressbook.services.AddressBookServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    private AddressBookServices useService;

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
        //return the value
        return useService.addRecord(address);
    }

    //Use @GetMapping Annotation to handle all request
    @GetMapping("/all")
    //create a method to get the all records
    public ResponseEntity<List<AddressDto>> getAllAddress() {
        //Return the value
        return useService.findAllRecord();
    }

    //Use @GetMapping Annotation
    @GetMapping("/get/{name}")
    //Create a method to get the record by name
    public ResponseEntity<AddressDto> getDetailByName(@PathVariable String name) {
        //Return the value
        return useService.findRecordByName(name);
    }

    //Use @DeleteMapping Annotation
    @DeleteMapping("/delete/{name}")
    //Create a method to delete record by name
    public ResponseEntity<String> deleteByName(@PathVariable String name) {
        //return the value
        return useService.deleteRecord(name);
    }

    //Use @PutMapping Annotation
    @PutMapping("/update/{name}")
    //Create a method to update record by name
    public ResponseEntity<String> updateRecordByName(@PathVariable String name, @RequestBody Address address) {
        //return the value
        return useService.updateRecord(name, address);
    }

}