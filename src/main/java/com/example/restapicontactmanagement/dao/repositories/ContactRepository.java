package com.example.restapicontactmanagement.dao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.restapicontactmanagement.dao.entities.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    List<Contact> findContactByName(String name);     
}
