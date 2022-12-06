package com.harres.AngularChallengeBackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


import javax.persistence.*;

@Entity
@Table(name = "activity")
public class Activity {

    private long id;
    private String title;
    private String notes;
    private String dueDate;

    public Activity() {
    }

    public Activity(Contact contact, String title, String notes, String dueDate) {
        this.contact = contact;
        this.title = title;
        this.notes = notes;
        this.dueDate = dueDate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    private Contact contact;

    @ManyToOne(fetch = FetchType.EAGER, optional = true, cascade = CascadeType.DETACH)
    @JoinColumn(name = "contact_id")
    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "activity_type_id")
    public ActivityType getActivityType() {
        return activityType;
    }

    public void setActivityType(ActivityType activityType) {
        this.activityType = activityType;
    }

    private ActivityType activityType;

    @Column(name = "title", nullable = false)
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "notes", nullable = false)
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Column(name = "due_date", nullable = false)
    public String getDueDate() {
        return dueDate;
    }
    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
}