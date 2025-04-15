package com.example.restapicontactmanagement.web.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.restapicontactmanagement.business.services.ContactService;
import com.example.restapicontactmanagement.dao.entities.Contact;
import com.example.restapicontactmanagement.exceptions.DuplicateContactException;
import com.example.restapicontactmanagement.web.dto.ContactDTO;
import com.example.restapicontactmanagement.web.dto.ContactSummaryDTO;

import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/contacts")

public class ContactController {
    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping()
    public ResponseEntity<?> getAllContacts() {
        List<ContactSummaryDTO> contacts = this.contactService.getAllContacts()
                .stream()
                .map(ContactSummaryDTO::toContactSummaryDTO)
                // .map(contact->ContactSummaryDTO.toContactSummaryDTO(contact))
                .collect(Collectors.toList());
              
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getContactById(@PathVariable(name="id") Long id) {
        ContactDTO contact = ContactDTO.toContactDTO(this.contactService.getContactById(id));
        return new ResponseEntity<>(contact, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> addContact(@RequestBody ContactDTO contactDTO) throws DuplicateContactException  {
       
        Contact contact = ContactDTO.fromContactDTO(contactDTO);
        return new ResponseEntity<>(this.contactService.addContact(contact), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateContact(@PathVariable(name="id") Long id, @RequestBody ContactDTO contactDTO) throws DuplicateContactException {
        Contact contact = ContactDTO.fromContactDTO(contactDTO);
        return new ResponseEntity<>(this.contactService.updateContact(id, contact), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteContact(@PathVariable(name="id") Long id) {
        this.contactService.deleteContact(id);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);

    }

}
 