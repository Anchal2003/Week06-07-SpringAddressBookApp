package com.bridgelabz.addressbook.repository;

import com.bridgelabz.addressbook.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

//Use @Repository Annotation
@Repository
//Create an interface AddressBookRepository to use CRUD operation
public interface AddressBookRepository extends JpaRepository<Address,String> {
}
