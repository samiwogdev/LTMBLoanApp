package com.samiwogdev.loantrust.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ProgressBar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.samiwogdev.loantrust.R;
import com.samiwogdev.loantrust.model.Customer;
import com.samiwogdev.loantrust.storage.CustomerPrefManager;
import com.samiwogdev.loantrust.storage.StageTwoPrefManager;

public class SignupStage2 extends AppCompatActivity implements  View.OnClickListener{

    private TextInputLayout stg2_fullname;
    private  TextInputLayout stg2_phone;
    private  TextInputLayout stg2_bvn;

    private AutoCompleteTextView monthly_income_text;
    private ProgressBar loader;
    private ArrayAdapter<String> monthly_income_adapter;
    private final String DEFAULT_INCOME = "Select Monthly Salary";

    String[] monthly_income_items =  {
            "Select Monthly Salary","10,000 - 50,000","50,000 - 200,000","200,000 - Above "
};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_srtage2);

        stg2_fullname = findViewById(R.id.stg2_fullname);
        stg2_phone = findViewById(R.id.stg2_phone);
        stg2_bvn = findViewById(R.id.stg2_bvn);


        monthly_income_text = findViewById(R.id.monthly_income_id);
        loader = findViewById(R.id.loader);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setTitle("Create Profile 2/4");
        }

        findViewById(R.id.stg2_login).setOnClickListener(this);

        monthly_income_adapter = new ArrayAdapter<String>(this,R.layout.monthly_income,monthly_income_items);
        monthly_income_text.setAdapter(monthly_income_adapter);

        monthly_income_text.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
            }
        });

        Customer customer = CustomerPrefManager.getInstance(SignupStage2.this).getCustomer();
        if(customer.getFirstname() != null){
            stg2_fullname.setHint(customer.getFullname());
            stg2_fullname.setEnabled(false);
            stg2_phone.setHint(customer.getPhone());
            stg2_phone.setEnabled(false);
            stg2_bvn.setHint(customer.getBvn());
            stg2_bvn.setEnabled(false);
        }
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.stg2_login:
                createProfile();
        }
    }

    private void createProfile() {
        String _monthly_income = monthly_income_text.getText().toString().trim();
        if(TextUtils.isEmpty(_monthly_income)){
            monthly_income_text.setError("Monthly income is required");
            monthly_income_text.requestFocus();
            loader.setVisibility(View.GONE);
            return;
        }
        if (_monthly_income == DEFAULT_INCOME){
            monthly_income_text.setError("Monthly income is required");
            monthly_income_text.requestFocus();
            loader.setVisibility(View.GONE);
            return;
        }
        loader.setVisibility(View.VISIBLE);
        String income = monthly_income_text.getText().toString().trim();
        Customer customer = new Customer(income);
        StageTwoPrefManager.getInstance(SignupStage2.this)
                .saveCustomerMonthlyIncome(customer);
        Intent intent  = new Intent(SignupStage2.this, SignupStage3.class);
        startActivity(intent);
        loader.setVisibility(View.GONE);
    }
}