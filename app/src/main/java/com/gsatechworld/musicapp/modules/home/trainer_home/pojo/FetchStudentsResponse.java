package com.gsatechworld.musicapp.modules.home.trainer_home.pojo;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.annotations.SerializedName;
import com.gsatechworld.musicapp.R;

import java.util.ArrayList;

public class FetchStudentsResponse {


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


    public class GetStudentsResult {


        @SerializedName("time_slots")
        private ArrayList<Time_slots> time_slots;

        public ArrayList<Time_slots> getTime_slots() {
            return time_slots;
        }

        public class Time_slots {
            @SerializedName("start_time")
            private String start_time;

            @SerializedName("end_time")
            private String end_time;

            @SerializedName("student_list")
            public ArrayList<Student_list> student_list;

            public String getStart_time() {
                return start_time;
            }

            public String getEnd_time() {
                return end_time;
            }
            public ArrayList<Student_list> getStudent_list() {
                return student_list;
            }



            public class Student_list {
                @SerializedName("student_name")
                private String student_name;

                @SerializedName("student_age")
                private String student_age;

                @SerializedName("student_id")
                private int student_id;

                @SerializedName("enrollment_name")
                private String enrollment_name;

                @SerializedName("mobile_number")
                private String mobile_number;

                @SerializedName("student_image")
                private String student_image;


                @SerializedName("enrollment_id")
                private int enrollment_id;


                @SerializedName("attendance")
                public String attendance;


                public int getEnrollment_id() {
                    return enrollment_id;
                }

                public String getStudent_name() {
                    return student_name;
                }

                public String getStudent_age() {
                    return student_age;
                }

                public int getStudent_id() {
                    return student_id;
                }

                public String getEnrollment_name() {
                    return enrollment_name;
                }

                public String getMobile_number() {
                    return mobile_number;
                }

                public String getStudent_image() {
                    return student_image;
                }

                public String getAttendance() {
                    return attendance;
                }


            }
        }
    }

    @BindingAdapter({ "student_image" })
    public static void loadImage(ImageView imageView, String imageURL) {
        Glide.with(imageView.getContext())
                .setDefaultRequestOptions(new RequestOptions()
                        .circleCrop())
                .load(imageURL)
                .into(imageView);
    }
}

