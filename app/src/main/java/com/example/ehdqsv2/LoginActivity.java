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
import android.widget.TextView;
import android.widget.Toast;

import com.example.ehdqsv2.network.ApiClient;
import com.example.ehdqsv2.pojo.LoginResponse;
import com.google.firebase.auth.FirebaseAuth;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {

    private EditText inputName, inputPassword;
    private FirebaseAuth auth;
    private Button  btnLogin;
    private TextView btnSignup , btnReset;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputName = (EditText) findViewById(R.id.name);
        inputPassword = (EditText) findViewById(R.id.password);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnReset =  findViewById(R.id.btn_reset_password);
        btnSignup = findViewById(R.id.btn_signup);

        prefs = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);


        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(inputName.getText().toString()) || TextUtils.isEmpty(inputPassword.getText().toString())) {

                    String message = "All inputs required..";
                    Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();

                } else {

                    LoginRequest loginRequest = new LoginRequest();
                    loginRequest.setUsername(inputName.getText().toString());
                    loginRequest.setPassword(inputPassword.getText().toString());
                    loginUser(loginRequest);
                }
            }
        });

    }

    public void loginUser(LoginRequest loginRequest) {
        Call<LoginResponse> loginResponseCall = ApiClient.getService().loginUser(loginRequest);
        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {

                    LoginResponse loginResponse = response.body();
                    SharedPreferences.Editor myEditor = prefs.edit();
                    myEditor.putString("userId",response.body().getData().getId());
                    myEditor.putString("userToken",response.body().getData().getToken());
                    System.out.println("userIdLogin = "+response.body().getData().getId());
                    System.out.println("userTokenLogin = "+response.body().getData().getId());
                    myEditor.apply();

//                    startActivity(new Intent(LoginActivity.this, HospitalActivity.class).putExtra("data", loginResponse));
//                    startActivity(new Intent(LoginActivity.this, HospitalActivity.class));
                    startActivity(new Intent(LoginActivity.this, AllHospitalsActivity.class));

                    finish();

                } else {
                    String message = "Error occurred please try again ..";
                    Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                String message = t.getLocalizedMessage();
                Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();

            }
        });
    }

}

