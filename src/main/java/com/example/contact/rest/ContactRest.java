package com.example.contact.rest;

import com.example.contact.domain.Contact;
import com.example.contact.rest.dto.ReqContactDTO;
import com.example.contact.rest.dto.ResSaveContactDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("contact")
public interface ContactRest {

    @PutMapping("/add")
    ResponseEntity<ResSaveContactDTO> saveContact(@RequestBody ReqContactDTO entity);

    @PostMapping("/search")
    ResponseEntity<List<Contact>> searchByDetail(@RequestBody ReqContactDTO entity);

}
