package com.samiwogdev.loantrust.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.samiwogdev.loantrust.Dashboard;
import com.samiwogdev.loantrust.R;
import com.samiwogdev.loantrust.api.RetrofitClient;
import com.samiwogdev.loantrust.model.Customer;
import com.samiwogdev.loantrust.model.LoginResponse;
import com.samiwogdev.loantrust.storage.CustomerPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Login extends AppCompatActivity implements View.OnClickListener {

     private TextInputLayout log_email;
     private  TextInputLayout log_password;
     TextView welcomeText;
    ProgressBar loader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        try{
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}

        log_email = findViewById(R.id.log_email);
        log_password = findViewById(R.id.log_password);

        findViewById(R.id.access_but).setOnClickListener(this);
        findViewById(R.id.forgotPassword).setOnClickListener(this);
        findViewById(R.id.signup).setOnClickListener(this);

        welcomeText = findViewById(R.id.welcomeText);
        loader = findViewById(R.id.loader);

        Customer customer = CustomerPrefManager.getInstance(Login.this).getCustomer();
        if(customer.getFirstname() != null){
            welcomeText.setText("Welcome Back " + customer.getFirstname());
        }else{
            welcomeText.setText("Welcome Back");
        }
    }

    private void signup(){
        Customer customer = CustomerPrefManager.getInstance(Login.this).getCustomer();
        if(customer.getFirstname() != null){
            AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
            builder.setTitle(Html.fromHtml("<font color='#ed5a0b'>Warning</font>"));
            builder.setMessage( Html.fromHtml("<font color='#007bff'> This action will clear your saved \n username, password, and other \n saved settings from this device \n Do you want to continue?</font>"));
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    CustomerPrefManager.getInstance(Login.this).clear();
                    startActivity(new Intent(Login.this, SignupStage1.class));
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            AlertDialog ad = builder.create();
            ad.show();
        }else{
            startActivity(new Intent(Login.this, SignupStage1.class));
        }

    }
    private void customerLogin(){
        loader.setVisibility(View.VISIBLE);
        String _log_email = log_email.getEditText().getText().toString().trim();
        String _log_password = log_password.getEditText().getText().toString().trim();

        if (TextUtils.isEmpty(_log_email)) {
            log_email.setError("Email is required");
            log_email.requestFocus();
            loader.setVisibility(View.GONE);
            return;
        }else {
            log_email.setError("");
            log_email.clearFocus();
            loader.setVisibility(View.GONE);
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(_log_email).matches()) {
            log_email.setError("Enter a valid email");
            log_email.requestFocus();
            loader.setVisibility(View.GONE);
            return;
        }
        if (TextUtils.isEmpty(_log_password)) {
            log_password.setError("Password is required");
            log_password.requestFocus();
            loader.setVisibility(View.GONE);
            return;
        }else{
            log_password.setError("");
            log_password.clearFocus();
            loader.setVisibility(View.GONE);
        }

        Call<LoginResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .login(_log_email, _log_password);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body();
                if (!loginResponse.isError()){
                    CustomerPrefManager.getInstance(Login.this)
                            .saveCustomer(loginResponse.getCustomer());
                    Intent intent = new Intent(Login.this, Dashboard.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }else{
                    Toast.makeText(Login.this, loginResponse.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(Login.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        loader.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.access_but:
                customerLogin();
                break;
            case R.id.forgotPassword:
                startActivity(new Intent(this, PasswordReset.class));
                break;
            case R.id.signup:
                signup();
                break;
        }
    }
}