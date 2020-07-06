package com.gsatechworld.musicapp.modules.home.settings.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class EnrollStudentsResponse {

    @SerializedName("status")
    public String status;


    @SerializedName("students_list")
    public ArrayList<EnrollStudents> enrollStudents;

    public String getStatus() {
        return status;
    }

    public ArrayList<EnrollStudents> getEnrollStudents() {
        return enrollStudents;
    }

    public class EnrollStudents {
        @SerializedName("student_id")
        public int student_id;
        @SerializedName("student_name")
        public String student_name;
        @SerializedName("mobile_number")
        public String student_mobile;
        @SerializedName("standard")
        public String standard;
        @SerializedName("amount")
        public String amount;
        @SerializedName("date")
        public String date;

        public String getStudent_name() {
            return student_name;
        }

        public String getStudent_mobile() {
            return student_mobile;
        }

        public String getStandard() {
            return standard;
        }

        public String getAmount() {
            return amount;
        }

        public String getDate() {
            return date;
        }

        public int getStudent_id() {
            return student_id;
        }



    }
}
