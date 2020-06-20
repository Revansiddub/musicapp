package com.gsatechworld.musicapp.modules.home.trainer_home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.gsatechworld.musicapp.utilities.CommonResponse;

public class AttendanceViewModel extends ViewModel {
    AttendanceRepository repository;

    public AttendanceViewModel() {
        this.repository=new AttendanceRepository();
    }

    public LiveData<CommonResponse> addAttendance(AttendanceRequest request){
        return repository.addAttendance(request);
    }
}
