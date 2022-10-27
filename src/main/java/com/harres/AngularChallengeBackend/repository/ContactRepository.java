package com.harres.AngularChallengeBackend.repository;

import com.harres.AngularChallengeBackend.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long>{

}