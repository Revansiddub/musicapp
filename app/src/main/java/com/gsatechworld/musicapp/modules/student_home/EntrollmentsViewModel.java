package com.gsatechworld.musicapp.modules.student_home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.gsatechworld.musicapp.modules.student_home.pojo.EntrollmentResponse;
import com.gsatechworld.musicapp.modules.student_home.pojo.Entrollments;

public class EntrollmentsViewModel extends ViewModel {
    public EntrollmentsRepository entrollmentsRepository;


    public EntrollmentsViewModel() {
        this.entrollmentsRepository=new EntrollmentsRepository();
    }

    public LiveData<EntrollmentResponse> fetchStudentEntrollments(String student_Id){
        return entrollmentsRepository.fetchEntrollments(student_Id);
    }
}
