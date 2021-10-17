package com.example.ehdqsv2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.ehdqsv2.network.UserService;
import com.example.ehdqsv2.pojo.DepartmentModel;
import com.example.ehdqsv2.pojo.DepartmentsListAdapter;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AllDepartmentsActivity extends AppCompatActivity {

    ArrayList<DepartmentModel.DataModel> departmentsArrayList;
    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;

    SharedPreferences prefs;
    String userId;
    String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiYTRjZDA0Y2RlNzVkZThlYWRmNmY5YjY2ZWJmNTgwYTdkZDc5Y2VmYzkwMzIzZDhmY2FkNzI0M2I3ODYyMzA5YzdlZWMwOTkyOTI4MmZkNGIiLCJpYXQiOiIxNjMwNTk0MzA0LjE4MzExMiIsIm5iZiI6IjE2MzA1OTQzMDQuMTgzMTE5IiwiZXhwIjoiMTY2MjEzMDMwNC4xNzMzMTQiLCJzdWIiOiIxIiwic2NvcGVzIjpbXX0.wzyG37yNpS213RS1iiYi9axzOomK1S3g5js_tMlrzh7lVQ13XN8ogRWChms9JoQkirkLbc1DLXKc261FqXnH-8oTW8-8-wLjQElg4mQovJPbwkTF7ZSke4pWZUNU5c56zKz0lEBiahzhiGaftVyj0W9Tj5jLCR4cDHeryROfSD-xPQ25XZuzzopZqtH-7nF2ISmuTEE3BDEGAqHYsuFvonDYkJb_QhvWSMgmAqnt39Sa8mD7mKoIZzvJ7fH3NZYsy-73vOapwL6VCI38OXQ-yYU1AZOQ3vbu2nkcg_cb3vc_0DoOYCZL3R0loxyEq3j2hAWP1jvDtg1mEu_CB9NKPAyOo-e0BhqsJSoTQ49AdPFIDYDALCccx2-Ge2sUQljbF-F_IE4lvD9XGuH_D4p_hmaGSgtHsS-B1StPbr9lAsmMLzpGsh6p06CbQf3M3v0GLFzUK4kR9J7zVuRpl-mnSRVe-kJPPLQEroF79k54_JGVbAUi03FetOgofUnzW9h9dYIsTdcsu1IOr_mzNBTr16LEjItE76u-G44PYE21TBRCFqlKswEZTXgBSfRqZ3sM0zTAAtaPxscmTsCU_AREYRQBFirwdmUnhcA5T1Agmw5ecKESGbmyIdFXEx4C8a4EK_pSnI29kvfnjqQL-CY1yfirezdmRnjeOEpDOBeZBC0";

    Retrofit retrofit;
    OkHttpClient client;
    UserService apiInterface;
    String hospitalId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_departments);


        departmentsArrayList = new ArrayList<>();


        recyclerView = findViewById(R.id.department_recycler);
        floatingActionButton = findViewById(R.id.floating_action_button);

        prefs = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        userId = prefs.getString("userId", "empty");
//        token = prefs.getString("userToken","empty");
        System.out.println("tttoo = " + prefs.getString("userToken", "empty"));

        Intent myIntent= getIntent();
        Bundle bundle = myIntent.getExtras();

        hospitalId = bundle.getString("hospitalId","1");

        System.out.println("----------------------------hosId = "+hospitalId);


        client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", "Bearer " + token)
                        .build();
                return chain.proceed(newRequest);
            }
        }).build();


        retrofit = new Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://dev.hospitals.design/api/")
                .build();


        getDepartments();


    }


    void getDepartments() {
        apiInterface = retrofit.create(UserService.class);


        Call<DepartmentModel> call2 = apiInterface.getAllDepartments();

        call2.enqueue(new Callback<DepartmentModel>() {
            @Override
            public void onResponse(Call<DepartmentModel> call, Response<DepartmentModel> response) {
                System.out.println("___________________________________________________________fffffffffddddddddddddddd = " +
                        response.body().message + " oo " + response.body().data.size());

                if (response.body().success.equals("true")) {
                    departmentsArrayList = response.body().data;
                    System.out.println("___________________________________________________________fffffffffddddddddddddddd = " +
                            response.body().message + " oo " + response.body().data.size());

                    DepartmentsListAdapter adapter = new DepartmentsListAdapter(AllDepartmentsActivity.this);
                    adapter.setDepartmentsList(departmentsArrayList);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                }
            }

            @Override
            public void onFailure(Call<DepartmentModel> call, Throwable t) {

            }
        });




    }


}