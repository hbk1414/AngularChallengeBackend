package com.harres.AngularChallengeBackend.controller;

import com.harres.AngularChallengeBackend.exceptions.ResourceNotFoundException;
import com.harres.AngularChallengeBackend.model.Activity;
import com.harres.AngularChallengeBackend.model.Contact;
import com.harres.AngularChallengeBackend.repository.ActivityRepository;
import com.harres.AngularChallengeBackend.repository.ContactRepository;
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

    @GetMapping("/activities")
    public List <Activity> getAllActivities() {
        return activityRepository.findAll();
    }

    @GetMapping("/contacts/{id}")
    public ResponseEntity < Activity > getContactsById(@PathVariable(value = "id") Long contactId)
    throws ResourceNotFoundException {
        Activity activity = activityRepository.findById(activityId)
            .orElseThrow(() -> new ResourceNotFoundException("Activity not found for this id :: " + activityId));
        return ResponseEntity.ok().body(activity);
    }

    @PostMapping("/activities")
    public Activity createActivity(@Valid @RequestBody Activity activity) {
        return activityRepository.save(activity);
    }

    @PutMapping("/activity/{id}")
    public ResponseEntity < Activity > updateContact(@PathVariable(value = "id") Long activityId,
        @Valid @RequestBody Activity activityDetails) throws ResourceNotFoundException {
        Activity activity = activityRepository.findById(activityId)
            .orElseThrow(() -> new ResourceNotFoundException("activity not found for this id :: " + activityId));

        activity.setTitle(activityDetails.getTitle());
        activity.setNotes(activityDetails.getNotes());
        activity.setDueDate(activityDetails.getDueDate());
        final Activity updatedActivity = activityRepository.save(activity);
        return ResponseEntity.ok(updatedActivity);
    }

    @DeleteMapping("/activity/{id}")
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