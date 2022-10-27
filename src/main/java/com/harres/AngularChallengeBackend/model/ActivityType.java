package com.harres.AngularChallengeBackend.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "activityType")
public class ActivityType {

    private long id;
    private String activityType;

    public ActivityType() {

    }

    public ActivityType(String activityType) {
        this.activityType = activityType;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    @OneToMany(mappedBy ="activityType")
    private List<Activity> activities;

    @Column(name = "activity_type", nullable = false)
    public String getActivityType() {
        return activityType;
    }
    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }


}