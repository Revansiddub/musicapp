package com.gsatechworld.musicapp.modules.student_home.pojo;

import java.util.List;

public class EntrollmentResponse {
    private String response;
    private String message;

    public EntrollmentResponse(String response, String message, List<Entrollments> entrollmentsList) {
        this.response = response;
        this.message = message;
        this.entrollmentsList = entrollmentsList;
    }

    public String getResponse() {
        return response;
    }

    public String getMessage() {
        return message;
    }

    public List<Entrollments> getEntrollmentsList() {
        return entrollmentsList;
    }

    private List<Entrollments> entrollmentsList;

}
