package com.harres.AngularChallengeBackend.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "contact")
public class Contact {

    private long id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String address1;
    private String address2;
    private String city;
    private String postCode;

    public Contact() {

    }

    public Contact(String firstName, String lastName, String emailAddress, String address1, String address2, String city, String postCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.postCode = postCode;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="id")
    private List<Activity> activities;


    @Column(name = "first_name", nullable = false)
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name", nullable = false)
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "email_address", nullable = false)
    public String getEmailAddress() {
        return emailAddress;
    }
    public void setEmailAddress(String emailId) {
        this.emailAddress = emailId;
    }

    @Column(name = "address1", nullable = false)
    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }
    @Column(name = "address2", nullable = false)
    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }
    @Column(name = "city", nullable = false)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    @Column(name = "post_code", nullable = false)
    public String getPostCode() {
        return postCode;
    }

//    public List<Activity> getActivities() {
//        return activities;
//    }
//
//    public void setActivities(List<Activity> activities) {
//        this.activities = activities;
//    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }
}