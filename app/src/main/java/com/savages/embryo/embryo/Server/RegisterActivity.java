package com.savages.embryo.embryo.Server;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.savages.embryo.embryo.Bean.Constants;
import com.savages.embryo.embryo.Bean.Patient;
import com.savages.embryo.embryo.Bean.PreferenceManager;
import com.savages.embryo.embryo.MyProfile;
import com.savages.embryo.embryo.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {
    private EditText name;
    private EditText mobile;
    private EditText city;
    private EditText NPP;
    private EditText NMC;
    private EditText gen_disease;
    private EditText DOC;
    private SharedPreferences pref;
    private Button Submit;
    private com.savages.embryo.embryo.Bean.PreferenceManager PreferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initViews();
        PreferenceManager = new PreferenceManager(this);
        if (!PreferenceManager.isFirstTimeLaunch()) {
          goToProfile();
            finish();
        }
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(RegisterBookAdActivity.this, "click tw hua", Toast.LENGTH_SHORT).show();
                String patientName = name.getText().toString();
                PreferenceManager.setMobile( mobile.getText().toString());
                PreferenceManager.setCity(city.getText().toString());
                PreferenceManager.setNPP(NPP.getText().toString());
                PreferenceManager.setNOM(NMC.getText().toString());
                PreferenceManager.setDOC(DOC.getText().toString());
                PreferenceManager.setName(name.getText().toString());
                PreferenceManager.setGenDisease(gen_disease.getText().toString());

                // TODO: 18-06-2016


                if(!patientName.isEmpty())
                {//registerAdProcess(patientName,patientPhone, patientCity, patientNopp, patientNom, patientGenetic,patientDoc);
                    goToProfile();
                    PreferenceManager.setFirstTimeLaunch(false);
              }
                else {
                    Toast.makeText(RegisterActivity.this, "Fields are empty!", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    private void goToProfile() {
        Intent intent = new Intent(RegisterActivity.this,MyProfile.class);
        startActivity(intent);
        RegisterActivity.this.finish();
    }

 /*   private void registerAdProcess(String patientName, String patientPhone, String patientCity, String patientNopp, String patientNom, String patientGenetic, String patientDoc) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface RequestInterfaceAdBean = retrofit.create(RequestInterface.class);


        Patient pt = new Patient();

        pt.setName(patientName);
        pt.setMobile(patientPhone);
        pt.setCity(patientCity);
        pt.setNPP(patientNopp);
        pt.setNMC(patientNom);
        pt.setGen_disease(patientGenetic);
        pt.setDOC(patientDoc);



        ServerRequest request = new ServerRequest();
        request.setOperation(Constants.REGISTER_OPERATION);
        request.setPatient(pt);

        Call<ServerResponse> response = RequestInterfaceAdBean.operation(request);

        response.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, retrofit2.Response<ServerResponse> response) {
                //Toast.makeText(RegisterBookAdActivity.this, "yha tk sab sahi hai", Toast.LENGTH_SHORT).show();
                //Toast.makeText(RegisterBookAdActivity.this, pref.getString(Constants.EMAIL, null), Toast.LENGTH_SHORT).show();
                ServerResponse resp = response.body();
              //  Toast.makeText(RegisterActivity.this, "here2", Toast.LENGTH_SHORT).show();
                //Toast.makeText(RegisterBookAdActivity.this, resp.getMessage(), Toast.LENGTH_SHORT).show();
                //Snackbar.make(getView(), resp.getMessage(), Snackbar.LENGTH_LONG).show();

                //progress.setVisibility(View.INVISIBLE);
*//*
                if(!resp.getMessage().toString().equalsIgnoreCase("Invalid Email"))
                    goToOtp();
*//*
            }


            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {

                //progress.setVisibility(View.INVISIBLE);
                Log.d(Constants.TAG,"failed");
                //Toast.makeText(RegisterBookAdActi.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                //Snackbar.make(getView(), t.getLocalizedMessage(), Snackbar.LENGTH_LONG).show();

            }
        });}*/


    public void initViews(){

        pref = this.getSharedPreferences(Constants.PREFERENCE_NAME, Context.MODE_PRIVATE);

        name = (EditText) findViewById(R.id.name_register);
        mobile = (EditText) findViewById(R.id.phone_register);
        city = (EditText) findViewById(R.id.city_register);
        NPP = (EditText) findViewById(R.id.nopp_register);
        NMC = (EditText) findViewById(R.id.nom_register);
        gen_disease = (EditText) findViewById(R.id.genetic_register);
        DOC =(EditText)findViewById(R.id.doctor_register);
        Submit= (Button) findViewById(R.id.submit_btn);


    }
}
