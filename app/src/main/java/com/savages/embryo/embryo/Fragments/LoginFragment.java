package com.savages.embryo.embryo.Fragments;

import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.savages.embryo.embryo.Bean.Constants;
import com.savages.embryo.embryo.Bean.User;
import com.savages.embryo.embryo.Login;
import com.savages.embryo.embryo.MyProfile;
import com.savages.embryo.embryo.R;
import com.savages.embryo.embryo.Server.RequestInterface;
import com.savages.embryo.embryo.Server.ServerRequest;
import com.savages.embryo.embryo.Server.ServerResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class LoginFragment extends Fragment implements View.OnClickListener {
    private AppCompatButton btn_login;
    private EditText et_name, et_phone,et_city;
    private TextView tv_register;
    private ProgressBar progress;
    private SharedPreferences pref;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login,container,false);
        initViews(view);
        return view;
    }

    private void initViews(View view){

        pref = getActivity().getPreferences(0);

        btn_login = (AppCompatButton)view.findViewById(R.id.btn_login);
        et_name = (EditText)view.findViewById(R.id.et_name);
        et_phone = (EditText)view.findViewById(R.id.et_phone_no);
        et_city= (EditText)view.findViewById(R.id.et_city);
        tv_register = (TextView)view.findViewById(R.id.tv_register);
        progress = (ProgressBar)view.findViewById(R.id.progress);

        btn_login.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){



            case R.id.btn_login:
                String name = et_name.getText().toString();
                String phone = et_phone.getText().toString();
                String city = et_city.getText().toString();

                if(!name.isEmpty() && !phone.isEmpty()&& !city.isEmpty()) {

                    progress.setVisibility(View.VISIBLE);
                    loginProcess(name,phone,city);

                } else {

                    Snackbar.make(getView(), "Fields are empty !", Snackbar.LENGTH_LONG).show();
                }
                break;

        }
    }
    private void loginProcess(String email,String password,String city){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface requestInterface = retrofit.create(RequestInterface.class);

        User user = new User();
        user.setName(email);
        user.setPhone(password);
        ServerRequest request = new ServerRequest();
        request.setOperation(Constants.LOGIN_OPERATION);
        request.setUser(user);
        Call<ServerResponse> response = requestInterface.operation(request);

        response.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, retrofit2.Response<ServerResponse> response) {

                ServerResponse resp = response.body();
                Snackbar.make(getView(), resp.getMessage(), Snackbar.LENGTH_LONG).show();

                if(resp.getResult().equals(Constants.SUCCESS)){
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putBoolean(Constants.IS_LOGGED_IN,true);
                    editor.putString(Constants.NAME,resp.getUser().getName());
                    editor.putString(Constants.PHONE,resp.getUser().getPhone());
                    editor.putString(Constants.CITY,resp.getUser().getCity());
                    editor.putString(Constants.UNIQUE_ID,resp.getUser().getUnique_id());
                    editor.apply();
                    goToProfile();

                }


                progress.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {

                progress.setVisibility(View.INVISIBLE);
                Log.d(Constants.TAG,"failed");
                Snackbar.make(getView(), t.getLocalizedMessage(), Snackbar.LENGTH_LONG).show();

            }
        });
    }



    /*private void goToRegister(){

        Fragment register = new RegisterFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_frame,register);
        ft.commit();
    }*/

    private void goToProfile(){

        Intent intent = new Intent(getActivity(), MyProfile.class);
        startActivity(intent);
        getActivity().finish();
    }
}
