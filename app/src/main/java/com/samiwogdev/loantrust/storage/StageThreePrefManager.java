package com.samiwogdev.loantrust.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.samiwogdev.loantrust.model.Customer;

public class StageThreePrefManager {
    private static final String SHARED_PREF_NAME = "customer_contact";
    private static StageThreePrefManager PrefManagerInstance;
    private Context stg3Context;

    private StageThreePrefManager(Context mCtx){
        this.stg3Context = mCtx;
    }

    public static synchronized  StageThreePrefManager getInstance(Context mCtx){
        if (PrefManagerInstance == null){
            PrefManagerInstance = new StageThreePrefManager(mCtx);
        }
        return  PrefManagerInstance;
    }

    public void saveCustomerContactInfo(Customer customer){
        SharedPreferences sharedPreferences = stg3Context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("state", customer.getState());
        editor.putString("nationality", customer.getNationality());
        editor.putString("home_address", customer.getHome_address());
        editor.apply();
    }

    public Customer getCustomercontact(){
        SharedPreferences sharedPreferences = stg3Context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return  new Customer(
                sharedPreferences.getString("state", null),
                sharedPreferences.getString("nationality", null),
                sharedPreferences.getString("home_address", null)
        );
    }
}
