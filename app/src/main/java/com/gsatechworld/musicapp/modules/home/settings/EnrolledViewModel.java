package com.gsatechworld.musicapp.modules.home.settings;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.gsatechworld.musicapp.modules.home.settings.pojo.EnrollStudentsResponse;

public class EnrolledViewModel extends ViewModel {
    public EnrolledRepository enrolledRepository;

    public EnrolledViewModel() {
        enrolledRepository=new EnrolledRepository();
    }

    public LiveData<EnrollStudentsResponse> getStudents(String trainerID){
        return enrolledRepository.getStudents(trainerID);
    }


}
