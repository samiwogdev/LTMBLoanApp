package com.samiwogdev.loantrust.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.samiwogdev.loantrust.R;
import com.samiwogdev.loantrust.activities.Terms;

public class SignupStage4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_stage4);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setTitle("Create Profile 4/4");
        }

    }

    public void terms(View view) {
        Intent intent = new Intent(this, Terms.class);
        startActivity(intent);
    }
}