package com.gsatechworld.musicapp.core.network;


import com.gsatechworld.musicapp.modules.home.approval.pojo.ApprovalResponse;
import com.gsatechworld.musicapp.modules.login.pojo.StudentResponse;
import com.gsatechworld.musicapp.modules.login.pojo.StudentsInfo;
import com.gsatechworld.musicapp.modules.login.pojo.TrainerLoginInfo;
import com.gsatechworld.musicapp.modules.login.pojo.TrainerResponse;
import com.gsatechworld.musicapp.modules.select_subcategory.pojo.CategoryResponse;
import com.gsatechworld.musicapp.modules.select_trainer.pojo.TrainerInfo;
import com.gsatechworld.musicapp.modules.select_trainer.pojo.TrainersResponse;
import com.gsatechworld.musicapp.modules.welcome.pojo.PinCodeInfo;
import com.gsatechworld.musicapp.select_category.add_category.AddCategory;
import com.gsatechworld.musicapp.select_category.pojo.CategoriesResponse;
import com.gsatechworld.musicapp.utilities.CommonResponse;

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

    @GET("approvalRequestList")
    Call<ApprovalResponse> approvalRequest(@Query("trainer_id") int trainerId);

    @POST("availableTrainers")
    Call<TrainersResponse> fetchTrainers(@Body TrainerInfo trainerInfo);

    @POST("approvalStatusUpdate")
    Call<TrainersResponse> approvalStatusUpdate(@Body TrainerInfo trainerInfo);

    @POST("loginStudents")
    Call<StudentResponse> loginStudents(@Query("mobile_number") String mobile);







}