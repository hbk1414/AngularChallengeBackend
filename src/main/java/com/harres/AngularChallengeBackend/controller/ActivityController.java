package com.harres.AngularChallengeBackend.controller;

import com.harres.AngularChallengeBackend.exceptions.ResourceNotFoundException;
import com.harres.AngularChallengeBackend.model.Activity;
import com.harres.AngularChallengeBackend.model.ActivityType;
import com.harres.AngularChallengeBackend.model.Contact;
import com.harres.AngularChallengeBackend.repository.ActivityRepository;
import com.harres.AngularChallengeBackend.repository.ActivityTypeRepository;
import com.harres.AngularChallengeBackend.repository.ContactRepository;
import com.harres.AngularChallengeBackend.view.ActivityView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/v1")
public class ActivityController {
    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ActivityTypeRepository activityTypeRepository;

    @GetMapping("/activities")
    public List <Activity> getAllActivities() {
        return activityRepository.findAll();
    }

    @GetMapping("/activities/types")
    public List <ActivityType> getAllActivitiesTypes() {
        return activityTypeRepository.findAll();
    }

    @GetMapping("/activities/{id}")
    public ResponseEntity < ActivityView > getContactsById(@PathVariable(value = "id") Long activityId)
            throws ResourceNotFoundException {
        Activity activity = activityRepository.findById(activityId)
                .orElseThrow(() -> new ResourceNotFoundException("Activity not found for this id :: " + activityId));
        ActivityView activityView = new ActivityView();
        activityView.setActivityTypeId(activity.getActivityType().getId());
        activityView.setContactId(activity.getContact().getId());
        activityView.setTitle(activity.getTitle());
        activityView.setNotes(activity.getNotes());
        activityView.setDueDate(activity.getDueDate());
        activityView.setId(activityId);
        return ResponseEntity.ok().body(activityView);
    }

    @PostMapping("/activities")
    public Activity createActivity(@Valid @RequestBody ActivityView activityView) throws ResourceNotFoundException {

        Contact contact = contactRepository.findById(activityView.getContactId())
                .orElseThrow(() -> new ResourceNotFoundException("Contact not found for this id :: " + activityView.getContactId()));

        ActivityType activityType = activityTypeRepository.findById(activityView.getContactId())
                .orElseThrow(() -> new ResourceNotFoundException("activity type not found for this id :: " + activityView.getActivityTypeId()));

        Activity activity = new Activity();
        activity.setTitle(activityView.getTitle());
        activity.setContact(contact);
        activity.setActivityType(activityType);
        activity.setNotes(activityView.getNotes());
        activity.setDueDate(activityView.getDueDate());
        return activityRepository.save(activity);
    }

    @PutMapping("/activities/{id}")
    public ResponseEntity < Activity > updateContact(@PathVariable(value = "id") Long activityId,
                                                     @Valid @RequestBody ActivityView activityDetails) throws ResourceNotFoundException {
        Activity activity = activityRepository.findById(activityId)
                .orElseThrow(() -> new ResourceNotFoundException("activity not found for this id :: " + activityId));
        Contact contact = contactRepository.findById(activityDetails.getContactId())
                .orElseThrow(() -> new ResourceNotFoundException("Contact not found for this id :: " + activityDetails.getContactId()));

        ActivityType activityType = activityTypeRepository.findById(activityDetails.getContactId())
                .orElseThrow(() -> new ResourceNotFoundException("activity type not found for this id :: " + activityDetails.getActivityTypeId()));

        activity.setTitle(activityDetails.getTitle());
        activity.setNotes(activityDetails.getNotes());
        activity.setDueDate(activityDetails.getDueDate());
        activity.setContact(contact);
        activity.setActivityType(activityType);
        final Activity updatedActivity = activityRepository.save(activity);
        return ResponseEntity.ok(updatedActivity);
    }
    @DeleteMapping("/activities/{id}")
    public Map < String, Boolean > deleteContact(@PathVariable(value = "id") Long activityId)
            throws ResourceNotFoundException {
        Activity activity = activityRepository.findById(activityId)
                .orElseThrow(() -> new ResourceNotFoundException("Activity not found for this id :: " + activityId));

        activityRepository.delete(activity);
        Map < String, Boolean > response = new HashMap < > ();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}