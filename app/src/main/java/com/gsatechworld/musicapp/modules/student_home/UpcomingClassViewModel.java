package com.gsatechworld.musicapp.modules.student_home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.gsatechworld.musicapp.modules.student_home.pojo.UpcomingResponse;

public class UpcomingClassViewModel extends ViewModel {
    public UpcomingClassRepository repository;

    public UpcomingClassViewModel() {
        repository=new UpcomingClassRepository();
    }

    public LiveData<UpcomingResponse> getUpcoming_class(String student_ID,String enrollment_id){
        return repository.fetchUpcoming_class(student_ID,enrollment_id);
    }
}
