package com.gsatechworld.musicapp.modules.home.trainer_home.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.gsatechworld.musicapp.modules.home.trainer_home.pojo.FetchStudentsResponse;
import com.gsatechworld.musicapp.modules.home.trainer_home.repository.GetStudentRepository;

public class GetStudentsViewModel extends ViewModel {

    public GetStudentRepository studentRepository;

    public GetStudentsViewModel() {

        this.studentRepository=new GetStudentRepository();
    }

    public LiveData<FetchStudentsResponse> getStudents(String trainerID, String date){
        return studentRepository.getStudents(trainerID, date);
    }


}
