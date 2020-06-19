package com.gsatechworld.musicapp.modules.home.trainer_home.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GetStudentsResponse {


    @SerializedName("result")
    private GetStudentsResult result;

    @SerializedName("status")
    private String status;

    public GetStudentsResult getResult() {
        return result;
    }

    public String getStatus() {
        return status;
    }



    public static class GetStudentsResult {

        @SerializedName("date")
        private ArrayList<Dates> dates;

        public ArrayList<Dates> getDates() {
            return dates;
        }



        public static class Dates {
            @SerializedName("date")
            private String date;

            @SerializedName("time_slot")
            private ArrayList<Time_slots> time_slots;

            public String getDate() {
                return date;
            }

            public ArrayList<Time_slots> getTime_slots() {
                return time_slots;
            }

            public static class Time_slots {
                @SerializedName("start_time")
                private String start_time;

                @SerializedName("end_time")
                private String end_time;

                @SerializedName("student_list")
                private ArrayList<Studentslist> student_list;

                public String getStart_time() {
                    return start_time;
                }

                public String getEnd_time() {
                    return end_time;
                }

                public ArrayList<Studentslist> getStudent_list() {
                    return student_list;
                }



                public static class Studentslist{
                    @SerializedName("student_name")
                    private String student_name;

                    @SerializedName("student_age")
                    private String student_age;

                    @SerializedName("student_id")
                    private String student_id;

                    @SerializedName("mobile_number")
                    private String mobile_number;

                    @SerializedName("student_image")
                    private String student_image;

                    public String getStudent_name() {
                        return student_name;
                    }

                    public String getStudent_age() {
                        return student_age;
                    }

                    public String getStudent_id() {
                        return student_id;
                    }

                    public String getMobile_number() {
                        return mobile_number;
                    }

                    public String getStudent_image() {
                        return student_image;
                    }


                }
            }
        }
    }
}
