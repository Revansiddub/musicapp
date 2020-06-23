package com.gsatechworld.musicapp.modules.student_home.student_profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.gsatechworld.musicapp.modules.student_home.student_profile.pojo.StudentProfileResponse;

public class ProfileViewModel extends ViewModel {
    public ProfileRepository profileRepository;


    public ProfileViewModel() {
        this.profileRepository=new ProfileRepository();
    }

    public LiveData<StudentProfileResponse> fetchStudentProfile(String student_id){
        return profileRepository.fetchProfile(student_id);
    }
}
