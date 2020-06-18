package com.gsatechworld.musicapp.modules.home.earnings.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class EarningResult {


    @SerializedName("student_list")
    private ArrayList<Student_lists> student_list;

    public ArrayList<Student_lists> getStudent_list() {

        return student_list;
    }

}
