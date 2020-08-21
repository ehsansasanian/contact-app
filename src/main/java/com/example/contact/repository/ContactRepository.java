package com.example.contact.repository;

import com.example.contact.domain.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {

    @Query("FROM Contact " +
            "WHERE (:name IS NULL OR name LIKE %:name%) " +
            "AND (:phoneNumber IS NULL OR phoneNumber LIKE %:phoneNumber%)" +
            "AND (:email IS NULL OR email LIKE %:email%)" +
            "AND (:organization IS NULL OR organization LIKE %:organization%)" +
            "AND (:gitHub IS NULL OR gitHub LIKE %:gitHub%)")
    List<Contact> searchByDetail(@Param("name") String name, @Param("phoneNumber") String phoneNumber,
                                       @Param("email") String email, @Param("organization") String organization,
                                       @Param("gitHub") String gitHub);
}
