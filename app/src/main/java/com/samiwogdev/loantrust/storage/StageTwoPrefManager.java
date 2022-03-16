package com.samiwogdev.loantrust.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.samiwogdev.loantrust.model.Customer;

public class StageTwoPrefManager {

    private static final String SHARED_PREF_NAME = "customer_monthly_income";
    private static StageTwoPrefManager PrefManagerInstance;
    private Context stgContext;

    private StageTwoPrefManager(Context mCtx){
        this.stgContext = mCtx;
    }

    public static synchronized StageTwoPrefManager getInstance(Context mCtx){
        if(PrefManagerInstance == null){
            PrefManagerInstance = new StageTwoPrefManager(mCtx);
        }
        return PrefManagerInstance;
    }

    public void saveCustomerMonthlyIncome(Customer customer){
        SharedPreferences sharedPreferences = stgContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("income", customer.getMonthly_income());
        editor.apply();
    }

    public Customer getCustomer(){
        SharedPreferences sharedPreferences = stgContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return  new Customer(
                sharedPreferences.getString("income", null)
        );
    }
}
