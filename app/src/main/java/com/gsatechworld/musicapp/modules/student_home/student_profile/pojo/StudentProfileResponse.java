package com.gsatechworld.musicapp.modules.student_home.student_profile.pojo;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.annotations.SerializedName;

public class StudentProfileResponse {

    @SerializedName("status")
    private String status;

    @SerializedName("name")
    private String name;

    @SerializedName("mobile_number")
    private String mobile_number;

    @SerializedName("age")
    private String age;

    @SerializedName("standard")
    private String standard;

    @SerializedName("school_name")
    private String school_name;

    @SerializedName("profile_image")
    public String profile_image;


    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }



    public String getProfile_image() {
        return profile_image;
    }



    public String getStandard() {
        return standard;
    }



    public String getName() {
        return name;
    }

    public String getSchool_name() {
        return school_name;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public String getAge() {
        return age;
    }

    public String getStatus() {
        return status;
    }

    @BindingAdapter("profile_image")
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext())
                .load(imageUrl).apply(new RequestOptions().circleCrop())
                .into(view);
    }




}
