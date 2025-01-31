package com.gsatechworld.musicapp.modules.details.personal_details.pojo;

public class PersonalDetails {

    public String getProfile_Image() {
        return profile_Image;
    }

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */
    private String profile_Image;
    private String fullName;
    private String emailAddress;
    private String mobileNumber;
    private String gender;
    private String highestDegreeBase;
    private String govtIDFrontBase;
    private String govtIDBackBase;
    private String addressProofFrontBase;
    private String addressProofBackBase;
    private String expertiseDocumentBase;
    private String amount;

    /* ------------------------------------------------------------- *
     * Constructor
     * ------------------------------------------------------------- */

    public PersonalDetails(String profile_Image,String fullName, String emailAddress, String mobileNumber, String gender,
                           String highestDegreeBase, String govtIDFrontBase, String govtIDBackBase,
                           String addressProofFrontBase, String addressProofBackBase,
                           String expertiseDocumentBase) {
        this.profile_Image=profile_Image;
        this.fullName = fullName;
        this.emailAddress = emailAddress;
        this.mobileNumber = mobileNumber;
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

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getMobileNumber() {
        return mobileNumber;
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