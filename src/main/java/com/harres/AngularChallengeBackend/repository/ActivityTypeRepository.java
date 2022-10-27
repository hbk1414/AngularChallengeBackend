package com.harres.AngularChallengeBackend.repository;

import com.harres.AngularChallengeBackend.model.ActivityType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityTypeRepository extends JpaRepository<ActivityType, Long>{

}