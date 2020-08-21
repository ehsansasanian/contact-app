package com.example.contact.rest;

import com.example.contact.domain.Contact;
import com.example.contact.rest.dto.ReqContactDTO;
import com.example.contact.rest.dto.ResSaveContactDTO;
import com.example.contact.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ContactRestImpl implements ContactRest {

    private final ContactService contactService;
    @Override
    public ResponseEntity<ResSaveContactDTO> saveContact(ReqContactDTO entity) {
        entity.validation();
        return contactService.saveContact(entity);
    }

    @Override
    public ResponseEntity<List<Contact>> searchByDetail(ReqContactDTO entity) {
        return contactService.searchByDetail(entity);
    }
}
