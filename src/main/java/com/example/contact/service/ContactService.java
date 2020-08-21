package com.example.contact.service;

import com.example.contact.domain.Contact;
import com.example.contact.rest.dto.ReqContactDTO;
import com.example.contact.rest.dto.ResSaveContactDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ContactService {

    ResponseEntity<List<Contact>> searchByDetail(ReqContactDTO entity);

    ResponseEntity<ResSaveContactDTO> saveContact(ReqContactDTO entity);
}
