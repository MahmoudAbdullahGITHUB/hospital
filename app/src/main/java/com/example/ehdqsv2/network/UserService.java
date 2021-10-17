package com.example.ehdqsv2.network;

import com.example.ehdqsv2.pojo.AddHospital;
import com.example.ehdqsv2.LoginRequest;
import com.example.ehdqsv2.pojo.DepartmentModel;
import com.example.ehdqsv2.pojo.HospitalModel;
import com.example.ehdqsv2.pojo.LoginResponse;
import com.example.ehdqsv2.RegisterRequest;
import com.example.ehdqsv2.pojo.QuestionModel;
import com.example.ehdqsv2.pojo.RegisterResponse;
import com.example.ehdqsv2.pojo.SubmitModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserService {

    @POST("login")
    Call<LoginResponse> loginUser(@Body LoginRequest loginRequest);

    @POST("register")
    Call<RegisterResponse> registerUser(@Body RegisterRequest registerRequest);

    @POST("addHospital")
    Call<AddHospital> addHospital(@Body AddHospital addHospital);

    @GET("questions")
    Call<QuestionModel> getQuestions(@Query("hospital_id") String hospitalId,@Query("department_id") String departmentId);

    @POST("submitAnswers")
    Call<QuestionModel> submitAnswers(@Body SubmitModel submitModel);

    @GET("hospitals")
    Call<HospitalModel> getAllHospitals();

    @GET("departments")
    Call<DepartmentModel> getAllDepartments();


//    @POST("generateReport")
//    Call<>

}
