package com.gsatechworld.musicapp.modules.student_home.student_payment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.gsatechworld.musicapp.modules.student_home.student_payment.pojo.StudentPaymentResponse;

public class StudentPaymentViewModel extends ViewModel {

    public StudentPaymentRepository paymentRepository;

    public StudentPaymentViewModel() {
     this.paymentRepository=new StudentPaymentRepository();
    }

    public LiveData<StudentPaymentResponse> fetchStudentPayment(String studentId){
        return paymentRepository.getPaymentList(studentId);
    }
}
