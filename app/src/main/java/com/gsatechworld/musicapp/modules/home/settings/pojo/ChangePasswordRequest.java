package com.gsatechworld.musicapp.modules.home.settings.pojo;

import com.google.gson.annotations.SerializedName;

public class ChangePasswordRequest {
    @SerializedName("trainer_id")
    public String trainerID;

    @SerializedName("old_password")
    public String oldPassword;

    @SerializedName("new_password")
    public String newPassword;

    public ChangePasswordRequest(String trainerID, String oldPassword, String newPassword) {
        this.trainerID = trainerID;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public String getTrainerID() {
        return trainerID;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }


}
