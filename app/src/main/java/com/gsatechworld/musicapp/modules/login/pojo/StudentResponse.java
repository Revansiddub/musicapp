package com.gsatechworld.musicapp.modules.login.pojo;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StudentResponse {


    @SerializedName("status")
    @Expose
    public String status;

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<User> getUser() {
        return user;
    }

    @SerializedName("message")
    @Expose
    public String message;

    @SerializedName("user")
    @Expose
    public List<User> user = null;

    public class User {

        @SerializedName("student_id")
        @Expose
        public Integer studentId;

        public Integer getStudentId() {
            return studentId;
        }

        public String getStudentName() {
            return studentName;
        }

        public Integer getPincodeId() {
            return pincodeId;
        }

        public String getPincode() {
            return pincode;
        }

        public String getProfile() {
            return profile;
        }

        public Integer getTrainerStatus() {
            return trainerStatus;
        }

        @SerializedName("student_name")
        @Expose
        public String studentName;
        @SerializedName("pincode_id")
        @Expose
        public Integer pincodeId;
        @SerializedName("pincode")
        @Expose
        public String pincode;
        @SerializedName("profile")
        @Expose
        public String profile;
        @SerializedName("trainer_status")
        @Expose
        public Integer trainerStatus;



    }


}