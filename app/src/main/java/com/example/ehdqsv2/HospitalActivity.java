package com.example.ehdqsv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ehdqsv2.network.ApiClient;
import com.example.ehdqsv2.pojo.AddHospital;
import com.example.ehdqsv2.pojo.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HospitalActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    EditText edt_hospitalname, edt_country, edt_address;
    Button btn_save;
    Spinner aSpinner;

    LoginResponse loginResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital);

        edt_address = (EditText) findViewById(R.id.adress);
        edt_hospitalname = (EditText) findViewById(R.id.hospitalname);
        edt_country = (EditText) findViewById(R.id.country);
        btn_save = (Button) findViewById(R.id.btn_save);


        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(edt_address.getText().toString()) || TextUtils.isEmpty(aSpinner.toString())) {
                    String message = "All inputs required..";
                    Toast.makeText(HospitalActivity.this, message, Toast.LENGTH_SHORT).show();

                } else {
                    AddHospital addHospital = new AddHospital();
                    addHospital.setAddress(edt_address.getText().toString());
                    addHospital.setHospital_type_id(aSpinner.toString());
                    addHospital(addHospital);
                }
            }
        });


        aSpinner = findViewById(R.id.aSpinner);

        aSpinner.setOnItemSelectedListener(this);

        Intent intent = new Intent();
        if (intent.getExtras() != null) {
            loginResponse = (LoginResponse) intent.getSerializableExtra("data");
//            Log.e("TAG", "=====>" + loginResponse.getEmail());

        }

    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        System.out.println("************************hosId = "+i +"  ");
        if (i != 0){
            final Intent intent;
            intent = new Intent(HospitalActivity.this, HospitaType1Activity.class);
            Bundle bundle = new Bundle();
            bundle.putString("hospitalId", String.valueOf(i));
            intent.putExtras(bundle);
            startActivity(intent);
        }


//        switch (i) {
//            case 1:
//                intent = new Intent(HospitalActivity.this, HospitaType1Activity.class);
//                intent.putExtra("hospitalId", i);
//                startActivity(intent);
//                break;
//            case 2:
////                intent = new Intent(HospitalActivity.this, HospitaType2Activity.class);
////                startActivity(intent);
//                break;
//            case 3:
////                intent = new Intent(HospitalActivity.this, HospitaType3Activity.class);
////                startActivity(intent);
//        }

    }


    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void addHospital(AddHospital addHospital) {
        Call<AddHospital> addHospitalCall = ApiClient.getService().addHospital(addHospital);
        addHospitalCall.enqueue(new Callback<AddHospital>() {
            @Override
            public void onResponse(Call<AddHospital> call, Response<AddHospital> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(HospitalActivity.this, "Successful", Toast.LENGTH_SHORT).show();

                } else {
                    String message = "Error occurred please try again ..";
                    Toast.makeText(HospitalActivity.this, message, Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<AddHospital> call, Throwable t) {

            }
        });
    }


}