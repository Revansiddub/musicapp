package com.gsatechworld.musicapp.modules.home.trainer_home.pojo;

import java.util.List;

public class StudentsResponse {
    private String response;

    public String getResponse() {
        return response;
    }

    public String getMessage() {
        return message;
    }

    public List<StudentAttendance> getAttendanceList() {
        return attendanceList;
    }

    private String message;

    public StudentsResponse(String response, String message, List<StudentAttendance> attendanceList) {
        this.response = response;
        this.message = message;
        this.attendanceList = attendanceList;
    }

    private List<StudentAttendance> attendanceList;
}
