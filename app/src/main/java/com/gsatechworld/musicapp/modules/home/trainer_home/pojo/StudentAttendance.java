package com.gsatechworld.musicapp.modules.home.trainer_home.pojo;

import java.util.List;

public class StudentAttendance {
    public int id;
    public String name;
    public String age;
    public String timing;
    public String phone;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String gender;

    public List<StudentAttendance> getAttendanceList() {
        return attendanceList;
    }

    public List<StudentAttendance> attendanceList;

    public StudentAttendance(int id, String name, String age,String gender, String timing, String phone) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.timing = timing;
        this.phone = phone;
        this.gender=gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getTiming() {
        return timing;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isAttented() {
        return isAttented;
    }

    public void setAttented(boolean attented) {
        isAttented = attented;
    }

    public boolean isAttented;
}

