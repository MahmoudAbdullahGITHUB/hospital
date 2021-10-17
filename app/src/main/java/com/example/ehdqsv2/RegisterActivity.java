package com.example.ehdqsv2;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ehdqsv2.network.ApiClient;
import com.example.ehdqsv2.pojo.RegisterResponse;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {


    private EditText inputEmail, inputName, inputPhone, inputPassword;
    private Button btnSignIn, btnSignUp;
    SharedPreferences prefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        btnSignIn = (Button) findViewById(R.id.sign_in_button);
        btnSignUp = (Button) findViewById(R.id.sign_up_button);
        inputEmail = (EditText) findViewById(R.id.email);
        inputName = (EditText) findViewById(R.id.name);
        inputPhone = (EditText) findViewById(R.id.phone);
        inputPassword = (EditText) findViewById(R.id.password);

        prefs = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);


        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(inputName.getText().toString()) || TextUtils.isEmpty(inputEmail.getText().toString()) || TextUtils.isEmpty(inputPhone.getText().toString()) || TextUtils.isEmpty(inputPassword.getText().toString())) {
                    String message = "All inputs required..";
                    Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_SHORT).show();

                } else {
                    RegisterRequest registerRequest = new RegisterRequest();
                    registerRequest.setName(inputName.getText().toString());
                    registerRequest.setEmail(inputEmail.getText().toString());
                    registerRequest.setPhone_number(inputPhone.getText().toString());
                    registerRequest.setPassword(inputPassword.getText().toString());

                    registerUser(registerRequest);
                }


            }
        });

    }

    public void registerUser(RegisterRequest registerRequest) {
        Call<RegisterResponse> registerResponseCall = ApiClient.getService().registerUser(registerRequest);
        registerResponseCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                System.out.println("regNameSuc1 = "+response.body().getSuccess());

                if (!response.body().getSuccess().equals("false")) {

                    String message = "Successful..";
                    Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_SHORT).show();
//                    System.out.println("regName = "+response.body().getData().getName());
                    System.out.println("regNameSuc = "+response.body().getSuccess());
                    SharedPreferences.Editor myEditor = prefs.edit();
                    myEditor.putString("userId",response.body().getData().getId());
                    myEditor.putString("userToken",response.body().getData().getToken());
                    System.out.println("userIdLogin = "+response.body().getData().getId());
                    System.out.println("userTokenLogin = "+response.body().getData().getId());
                    myEditor.apply();
//                    startActivity(new Intent(RegisterActivity.this, HospitalActivity.class));
                    startActivity(new Intent(RegisterActivity.this, AllHospitalsActivity.class));
                    finish();

                } else {
                    String message = "Error occurred please try again ..";
                    Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                String message = t.getLocalizedMessage();
                Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });

    }
}