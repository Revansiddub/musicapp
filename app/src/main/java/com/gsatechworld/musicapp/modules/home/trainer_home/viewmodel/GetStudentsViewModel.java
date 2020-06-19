package com.gsatechworld.musicapp.modules.home.trainer_home.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.gsatechworld.musicapp.modules.home.trainer_home.pojo.GetStudentsResponse;
import com.gsatechworld.musicapp.modules.home.trainer_home.repository.GetStudentRepository;

public class GetStudentsViewModel extends ViewModel {

    public GetStudentRepository studentRepository;

    public GetStudentsViewModel() {

        this.studentRepository=new GetStudentRepository();
    }

    public LiveData<GetStudentsResponse> getStudents(String trainerID,String date){
        return studentRepository.getStudents(trainerID,date);
    }


}
