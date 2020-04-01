package com.gsatechworld.musicapp.modules.home.trainer_home.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.gsatechworld.musicapp.modules.home.trainer_home.pojo.StudentsResponse;
import com.gsatechworld.musicapp.modules.home.trainer_home.repository.AttendanceRepository;

public class AttendanceViewModel extends ViewModel {
AttendanceRepository repository;

    public AttendanceViewModel() {

        this.repository=new AttendanceRepository();
    }

    public LiveData<StudentsResponse> getStudentsDetails(){
        return repository.fetchStudents();
    }
}
