package com.example.contact.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@Entity
public class GitRepositories {

    @Id
    @GeneratedValue
    private Integer id;
    private String repositoryName;

    @ManyToOne
    private Contact contact;

    public GitRepositories() {
    }

    public GitRepositories(String name, Contact contact) {
        this.repositoryName = name;
        this.contact = contact;
    }
}
