package com.gsatechworld.musicapp.modules.details.personal_details.pojo;

public class PersonalDetails {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private String fullName;
    private String gender;
    private String highestDegreeBase;
    private String govtIDFrontBase;
    private String govtIDBackBase;
    private String addressProofFrontBase;
    private String addressProofBackBase;
    private String expertiseDocumentBase;

    /* ------------------------------------------------------------- *
     * Constructor
     * ------------------------------------------------------------- */

    public PersonalDetails(String fullName, String gender, String highestDegreeBase,
                           String govtIDFrontBase, String govtIDBackBase,
                           String addressProofFrontBase, String addressProofBackBase,
                           String expertiseDocumentBase) {
        this.fullName = fullName;
        this.gender = gender;
        this.highestDegreeBase = highestDegreeBase;
        this.govtIDFrontBase = govtIDFrontBase;
        this.govtIDBackBase = govtIDBackBase;
        this.addressProofFrontBase = addressProofFrontBase;
        this.addressProofBackBase = addressProofBackBase;
        this.expertiseDocumentBase = expertiseDocumentBase;
    }

    /* ------------------------------------------------------------- *
     * Getters
     * ------------------------------------------------------------- */

    public String getFullName() {
        return fullName;
    }

    public String getGender() {
        return gender;
    }

    public String getHighestDegreeBase() {
        return highestDegreeBase;
    }

    public String getGovtIDFrontBase() {
        return govtIDFrontBase;
    }

    public String getGovtIDBackBase() {
        return govtIDBackBase;
    }

    public String getAddressProofFrontBase() {
        return addressProofFrontBase;
    }

    public String getAddressProofBackBase() {
        return addressProofBackBase;
    }

    public String getExpertiseDocumentBase() {
        return expertiseDocumentBase;
    }
}