package com.example.contact.rest.dto;

import com.example.contact.domain.Contact;
import lombok.Data;

@Data
public class ResSaveContactDTO {
    private Contact contact;
    private Response response;

    public enum Response{
        GIT_REPOSITORY_NOT_FOUND,REPOSITORIES_ADDED_SUCCESSFULLY
    }
}
