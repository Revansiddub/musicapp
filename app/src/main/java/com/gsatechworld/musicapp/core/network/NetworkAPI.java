package com.gsatechworld.musicapp.core.network;


import com.gsatechworld.musicapp.modules.details.pojo.OnBoadingTrainer;
import com.gsatechworld.musicapp.modules.home.approval.pojo.ApprovalResponse;
import com.gsatechworld.musicapp.modules.home.approval.pojo.ApproveStatus;
import com.gsatechworld.musicapp.modules.home.earnings.pending_payments.pojo.PendingPaymentsResp;
import com.gsatechworld.musicapp.modules.home.earnings.pojo.EarningResponse;
import com.gsatechworld.musicapp.modules.home.payment.adapter.AcceptPayment;
import com.gsatechworld.musicapp.modules.home.payment.pojo.PaymentRequestResponse;
import com.gsatechworld.musicapp.modules.home.settings.pojo.ChangePasswordRequest;
import com.gsatechworld.musicapp.modules.home.settings.pojo.EnrollStudentsResponse;
import com.gsatechworld.musicapp.modules.home.trainer_home.AttendanceRequest;
import com.gsatechworld.musicapp.modules.home.trainer_home.pojo.AvailableTimeSlotResponse;
import com.gsatechworld.musicapp.modules.home.trainer_home.pojo.CancelClass;
import com.gsatechworld.musicapp.modules.home.trainer_home.pojo.DateResponse;
import com.gsatechworld.musicapp.modules.home.trainer_home.pojo.FetchStudentsResponse;
import com.gsatechworld.musicapp.modules.login.pojo.StudentResponse;
import com.gsatechworld.musicapp.modules.login.pojo.TrainerLoginInfo;
import com.gsatechworld.musicapp.modules.login.pojo.TrainerResponse;
import com.gsatechworld.musicapp.modules.otp.pojo.TrainerOTPVerification;
import com.gsatechworld.musicapp.modules.otp.student.StudentOTPRequest;
import com.gsatechworld.musicapp.modules.otp.student.pojo.StudentOTPResponse;
import com.gsatechworld.musicapp.modules.select_subcategory.add_subcategory.AddSubCategory;
import com.gsatechworld.musicapp.modules.select_subcategory.pojo.CategoryResponse;
import com.gsatechworld.musicapp.modules.select_time_slot.pojo.AvailableTimesSlotResponse;
import com.gsatechworld.musicapp.modules.select_trainer.pojo.TrainerInfo;
import com.gsatechworld.musicapp.modules.select_trainer.pojo.TrainersResponse;
import com.gsatechworld.musicapp.modules.student_details.pojo.OnboardingRequest;
import com.gsatechworld.musicapp.modules.student_home.pojo.AddEntrollmentRequest;
import com.gsatechworld.musicapp.modules.student_home.pojo.EntrollmentResponse;
import com.gsatechworld.musicapp.modules.student_home.pojo.UpcomingResponse;
import com.gsatechworld.musicapp.modules.student_home.student_payment.pojo.StudentPaymentRequest;
import com.gsatechworld.musicapp.modules.student_home.student_payment.pojo.StudentPaymentResponse;
import com.gsatechworld.musicapp.modules.student_home.student_profile.pojo.StudentProfileResponse;
import com.gsatechworld.musicapp.modules.welcome.pojo.PinCodeInfo;
import com.gsatechworld.musicapp.select_category.add_category.AddCategory;
import com.gsatechworld.musicapp.select_category.pojo.CategoriesResponse;
import com.gsatechworld.musicapp.utilities.CommonResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface NetworkAPI {

    /* ------------------------------------------------------------- *
     * Interface Methods
     * ------------------------------------------------------------- */
    @Headers({"Content-Type: application/json"})
   @POST("checkPincode")
   Call<CommonResponse> checkAvailability(@Body PinCodeInfo result);

   @POST("availableCategories")
   Call<CategoriesResponse> getCategories(@Body PinCodeInfo info);

    @POST("availableCategories")
    Call<CategoryResponse> getSubCategories(@Body PinCodeInfo info);

    @POST("addNewCategory")
    Call<CommonResponse> addCategory(@Body AddCategory category);

    @POST("loginTrainers")
    Call<TrainerResponse> checkLogin(@Body TrainerLoginInfo category);

    @POST("approvalRequestList")
    Call<ApprovalResponse> approvalRequest(@Query("trainer_id") String trainerId);

    @POST("availableTrainers")
    Call<TrainersResponse> fetchTrainers(@Body TrainerInfo trainerInfo);

    @POST("approvalStatusUpdate")
    Call<CommonResponse> approvalStatusUpdate(@Body ApproveStatus approveStatus);

    @POST("loginStudents")
    Call<StudentResponse> loginStudents(@Query("mobile_number") String mobile);

    @POST("onBoardTrainers")
    Call<CommonResponse> addTrainerDetails(@Body OnBoadingTrainer trainerDetails);

    @POST("getAvailableTimeslot")
    Call<AvailableTimeSlotResponse> getTimeSlots(@Query("trainer_id") String trainerId);

    @POST("trainers/verifyOtp")
    Call<CommonResponse> verifyTrainer(@Body TrainerOTPVerification otpVerification);

    @POST("pendingPayments")
    Call<PendingPaymentsResp> getPendingPayments(@Query("trainer_id") String trainer_Id);

    @POST("addNewSubcategory")
    Call<CommonResponse> addSubCategory(@Body AddSubCategory addSubCategory);

    @POST("trainersChangePassword")
    Call<CommonResponse> changePassword(@Body ChangePasswordRequest changePasswordRequest);

    @GET("earningList")
    Call<EarningResponse> getEarnings(@Query("trainer_id") String trainer_Id);

    @GET("allPaymentsRequest")
    Call<PaymentRequestResponse> getPaymenrRequest(@Query("trainer_id") String trainer_Id);

    @POST("acceptPaymentsRequest")
    Call<CommonResponse> acceptPamentRequest(@Body AcceptPayment acceptPayment);

    @GET("studentsByMonth")
     Call<FetchStudentsResponse> getStudentBydate(@Query("trainer_id") String trainer_ID, @Query("date") String date);

   @POST("addAttendance")
   Call<CommonResponse> addAttendance(@Body AttendanceRequest request);

   @GET("studentEnrollment")
   Call<EntrollmentResponse> fetchEntrollments(@Query("student_id") String student_id);

   @GET("studentsPendingPayments")
   Call<StudentPaymentResponse> getStudentPayments(@Query("student_id") String student_id);

    @POST("getAvailableTimeslot")
    Call<AvailableTimesSlotResponse> fecthTimeslots(@Query("trainer_id") String trainer_id);

    @POST("onBoardStudents")
    Call<CommonResponse> studentRegister(@Body OnboardingRequest onboardingRequest);

    @GET("studentProfile")
    Call<StudentProfileResponse> fetchstudentProfile(@Query("student_id") String student_id);

    @POST("studentsPaymentsRequest")
    Call<CommonResponse> studentsPaymentRequests(@Body StudentPaymentRequest paymentRequest);

    @POST("addEnrollment")
    Call<CommonResponse> addnewEntrollment(@Body AddEntrollmentRequest entrollmentRequest);

    @GET("upcomingClass")
    Call<UpcomingResponse> upcomingResponse(@Query("student_id") String student_id);

    @POST("loginStudents/verifyOtp")
    Call<StudentOTPResponse> verfyStudentOTP(@Body StudentOTPRequest otpRequest);

    @GET("getTrainersDates")
    Call<DateResponse> getDates(@Query("trainer_id")String trainer_id,@Query("month") String month,@Query("year") String year);

    @POST("cancellationClass")
    Call<CommonResponse> cancel_class(@Query("enrollment_id") String enrollment_id,@Query("date") String date,@Query("start_time") String start_time,@Query("end_time")String endtime);

    @GET("forgotPassword")
    Call<CommonResponse> forgotPassword(@Query("email") String email);

    @GET("approvedStudents")
    Call<EnrollStudentsResponse> getEntrolledStudents(@Query("trainer_id") String trainer_id);

    @POST("trainerCancellationClass")
    Call<CommonResponse> cancelClass_trainer(@Body CancelClass cancelClass);














}