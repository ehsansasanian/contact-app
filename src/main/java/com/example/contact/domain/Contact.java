package com.example.contact.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Contact {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String phoneNumber;
    private String email;
    private String organization;
    private String gitHub;

    public Contact() {
    }

    public Contact(String name, String phoneNumber, String email, String organization, String gitHub) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.organization = organization;
        this.gitHub = gitHub;
    }
}
