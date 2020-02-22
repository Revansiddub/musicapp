package com.gsatechworld.musicapp.modules.details.pojo;

import com.gsatechworld.musicapp.modules.details.coaching_details.pojo.CoachingDetails;
import com.gsatechworld.musicapp.modules.details.personal_details.pojo.PersonalDetails;

public class TrainerDetails {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private CoachingDetails coachingDetails;
    private PersonalDetails personalDetails;

    /* ------------------------------------------------------------- *
     * Constructor
     * ------------------------------------------------------------- */

    public TrainerDetails(CoachingDetails coachingDetails, PersonalDetails personalDetails) {
        this.coachingDetails = coachingDetails;
        this.personalDetails = personalDetails;
    }

    /* ------------------------------------------------------------- *
     * Getters
     * ------------------------------------------------------------- */

    public CoachingDetails getCoachingDetails() {
        return coachingDetails;
    }

    public PersonalDetails getPersonalDetails() {
        return personalDetails;
    }
}