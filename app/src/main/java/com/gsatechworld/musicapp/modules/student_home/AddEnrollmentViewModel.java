package com.gsatechworld.musicapp.modules.student_home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.gsatechworld.musicapp.modules.student_home.pojo.AddEntrollmentRequest;
import com.gsatechworld.musicapp.utilities.CommonResponse;

public class AddEnrollmentViewModel extends ViewModel {

    public AddEnrollmentRepository enrollmentRepository;

    public AddEnrollmentViewModel() {
        this.enrollmentRepository=new AddEnrollmentRepository();
    }

    public LiveData<CommonResponse> addNewEnrollment(AddEntrollmentRequest entrollmentRequest){
        return enrollmentRepository.addNewEnrollment(entrollmentRequest);
    }
}
