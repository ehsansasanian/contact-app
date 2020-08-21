package com.example.contact.rest.dto;

import com.example.contact.constant.ValidationTools;
import com.example.contact.domain.Contact;
import com.example.contact.exceptionhandler.FieldValidationException;
import lombok.Data;

@Data
public class ReqContactDTO {

    private String name;
    private String phoneNumber;
    private String email;
    private String organization;
    private String gitHub;

    public void validation() throws FieldValidationException {
        if (this.email != null)
            ValidationTools.email(email, "email");
    }

    public Contact map() {
        return new Contact(name, phoneNumber, email, organization, gitHub);
    }
}
