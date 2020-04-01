package com.gsatechworld.musicapp.modules.home.trainer_home.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.gsatechworld.musicapp.modules.home.trainer_home.pojo.StudentAttendance;
import com.gsatechworld.musicapp.modules.home.trainer_home.pojo.StudentsResponse;

import java.util.ArrayList;
import java.util.List;

import static com.gsatechworld.musicapp.utilities.Constants.SERVER_RESPONSE_SUCCESS;

public class AttendanceRepository {

    public AttendanceRepository() {
    }


    public LiveData<StudentsResponse> fetchStudents(){
        MutableLiveData<StudentsResponse> mutableLiveData=new MutableLiveData<>();

        List<StudentAttendance> attendanceList=new ArrayList<>();
        attendanceList.add(new StudentAttendance(1,"Jinshad","26","Male","09.00 - 10.00","952626636"));
        attendanceList.add(new StudentAttendance(2,"Fahim","24","Male","10.00 - 11.00","952626636"));
        attendanceList.add(new StudentAttendance(3,"Labeeb","24","Male","09.00 - 10.00","952626636"));
        attendanceList.add(new StudentAttendance(4,"Arun","25","Male","11.00 - 12.00","952626636"));
        attendanceList.add(new StudentAttendance(5,"Anjana","26","Female","11.00 - 12.00","952626636"));
        attendanceList.add(new StudentAttendance(6,"Preeti","23","Feale","10.00 - 11.00","952626636"));
        attendanceList.add(new StudentAttendance(8,"Varun","24","Male","12.00 - 01.00","952626636"));
        attendanceList.add(new StudentAttendance(9,"Prasoon","27","Male","12.00 - 01.00","952626636"));
        attendanceList.add(new StudentAttendance(10,"Ishaq","24","Male","01.00 - 02.00","952626636"));

        mutableLiveData.postValue(new StudentsResponse(SERVER_RESPONSE_SUCCESS,
                "Oops! something went wrong. Please try again later.", attendanceList));
        return mutableLiveData;


    }
}
