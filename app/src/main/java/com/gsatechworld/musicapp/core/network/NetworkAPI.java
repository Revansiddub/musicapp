package com.gsatechworld.musicapp.core.network;


import com.gsatechworld.musicapp.modules.details.pojo.OnBoadingTrainer;
import com.gsatechworld.musicapp.modules.details.pojo.TrainerDetails;
import com.gsatechworld.musicapp.modules.home.approval.pojo.ApprovalResponse;
import com.gsatechworld.musicapp.modules.home.approval.pojo.ApproveStatus;
import com.gsatechworld.musicapp.modules.home.earnings.pending_payments.pojo.PaymentResponse;
import com.gsatechworld.musicapp.modules.home.earnings.pojo.EarningResponse;
import com.gsatechworld.musicapp.modules.home.payment.adapter.AcceptPayment;
import com.gsatechworld.musicapp.modules.home.payment.pojo.PaymentRequestResponse;
import com.gsatechworld.musicapp.modules.home.settings.pojo.ChangePasswordRequest;
import com.gsatechworld.musicapp.modules.home.trainer_home.pojo.AvailableTimeSlotResponse;
import com.gsatechworld.musicapp.modules.login.pojo.StudentResponse;
import com.gsatechworld.musicapp.modules.login.pojo.StudentsInfo;
import com.gsatechworld.musicapp.modules.login.pojo.TrainerLoginInfo;
import com.gsatechworld.musicapp.modules.login.pojo.TrainerResponse;
import com.gsatechworld.musicapp.modules.otp.pojo.TrainerOTPVerification;
import com.gsatechworld.musicapp.modules.select_subcategory.add_subcategory.AddSubCategory;
import com.gsatechworld.musicapp.modules.select_subcategory.pojo.CategoryResponse;
import com.gsatechworld.musicapp.modules.select_time_slot.pojo.TimeSlotResponse;
import com.gsatechworld.musicapp.modules.select_trainer.pojo.TrainerInfo;
import com.gsatechworld.musicapp.modules.select_trainer.pojo.TrainersResponse;
import com.gsatechworld.musicapp.modules.welcome.pojo.PinCodeInfo;
import com.gsatechworld.musicapp.select_category.add_category.AddCategory;
import com.gsatechworld.musicapp.select_category.pojo.CategoriesResponse;
import com.gsatechworld.musicapp.utilities.CommonResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
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
    Call<PaymentResponse> getPendingPayments(@Query("trainer_id") String trainer_Id);

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














}