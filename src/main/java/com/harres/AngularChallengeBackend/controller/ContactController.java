package com.harres.AngularChallengeBackend.controller;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.harres.AngularChallengeBackend.exceptions.ResourceNotFoundException;
import com.harres.AngularChallengeBackend.model.Contact;
import com.harres.AngularChallengeBackend.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1")
public class ContactController {
    @Autowired
    private ContactRepository contactRepository;

    @GetMapping("/contacts")
    public List <Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    @GetMapping("/contacts/{id}")
    public ResponseEntity < Contact > getContactsById(@PathVariable(value = "id") Long contactId)
    throws ResourceNotFoundException {
        Contact contact = contactRepository.findById(contactId)
            .orElseThrow(() -> new ResourceNotFoundException("Contact not found for this id :: " + contactId));
        return ResponseEntity.ok().body(contact);
    }

    @PostMapping("/contacts")
    public Contact createContact(@Valid @RequestBody Contact contact) {
        return contactRepository.save(contact);
    }

    @PutMapping("/contacts/{id}")
    public ResponseEntity < Contact > updateContact(@PathVariable(value = "id") Long contactId,
        @Valid @RequestBody Contact contactDetails) throws ResourceNotFoundException {
        Contact contact = contactRepository.findById(contactId)
            .orElseThrow(() -> new ResourceNotFoundException("contact not found for this id :: " + contactId));

        contact.setEmailAddress(contactDetails.getEmailAddress());
        contact.setLastName(contactDetails.getLastName());
        contact.setFirstName(contactDetails.getFirstName());
        final Contact updatedContact = contactRepository.save(contact);
        return ResponseEntity.ok(updatedContact);
    }

    @DeleteMapping("/contact/{id}")
    public Map < String, Boolean > deleteContact(@PathVariable(value = "id") Long contactId)
    throws ResourceNotFoundException {
        Contact contact = contactRepository.findById(contactId)
            .orElseThrow(() -> new ResourceNotFoundException("Contact not found for this id :: " + contactId));

        contactRepository.delete(contact);
        Map < String, Boolean > response = new HashMap < > ();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}