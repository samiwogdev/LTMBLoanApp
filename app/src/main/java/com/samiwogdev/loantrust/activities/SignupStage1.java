package com.samiwogdev.loantrust.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.samiwogdev.loantrust.R;
import com.samiwogdev.loantrust.api.RetrofitClient;
import com.samiwogdev.loantrust.model.AuthResponse;
import com.samiwogdev.loantrust.storage.CustomerPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupStage1 extends AppCompatActivity implements View.OnClickListener {
    private TextInputLayout auth_email;
    private  TextInputLayout auth_account;
    ProgressBar loader;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_srtage1);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setTitle("Create Profile 1/4");
        }

        auth_email = findViewById(R.id.auth_email);
        auth_account = findViewById(R.id.auth_account);

        findViewById(R.id.auth_proceed).setOnClickListener(this);
        findViewById(R.id.auth_login).setOnClickListener(this);
        loader = findViewById(R.id.loader);
    }

    public void stageTwo(View view) {
        Intent intent = new Intent(this, SignupStage2.class);
        startActivity(intent);
    }

    private void authCustomer(){
        loader.setVisibility(View.VISIBLE);
        String _auth_email = auth_email.getEditText().getText().toString().trim();
        String _auth_account = auth_account.getEditText().getText().toString().trim();

        if (TextUtils.isEmpty(_auth_email)) {
            auth_email.setError("Email is required");
            auth_email.requestFocus();
            loader.setVisibility(View.GONE);
            return;
        }else {
            auth_email.setError("");
            auth_email.clearFocus();
            loader.setVisibility(View.GONE);
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(_auth_email).matches()) {
            auth_email.setError("Enter a valid email");
            auth_email.requestFocus();
            loader.setVisibility(View.GONE);
            return;
        }
        if (TextUtils.isEmpty(_auth_account)) {
            auth_account.setError("Password is required");
            auth_account.requestFocus();
            loader.setVisibility(View.GONE);
            return;
        }else{
            auth_account.setError("");
            auth_account.clearFocus();
            loader.setVisibility(View.GONE);
        }

        Call<AuthResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .authCustomer(_auth_email, _auth_account);

        call.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                AuthResponse authResponse = response.body();
                if (!authResponse.isError()){
                    CustomerPrefManager.getInstance(SignupStage1.this)
                            .saveCustomer(authResponse.getCustomer());
                    Intent intent = new Intent(SignupStage1.this, SignupStage2.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }else{
                    Toast.makeText(SignupStage1.this, authResponse.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                Toast.makeText(SignupStage1.this, t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.auth_proceed:
                authCustomer();
                break;
            case R.id.auth_login:
                startActivity(new Intent(this, Login.class));
                break;
        }

    }
}