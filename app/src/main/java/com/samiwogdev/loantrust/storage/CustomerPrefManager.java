package com.samiwogdev.loantrust.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.samiwogdev.loantrust.model.Customer;

public class CustomerPrefManager {

    private static final String SHARED_PREF_NAME = "customerPref";
    private static CustomerPrefManager mInstance;
    private Context mCtx;

    private CustomerPrefManager(Context mCtx) {
        this.mCtx = mCtx;
    }


    public static synchronized CustomerPrefManager getInstance(Context mCtx) {
        if (mInstance == null) {
            mInstance = new CustomerPrefManager(mCtx);
        }
        return mInstance;
    }

    public void saveCustomer(Customer customer) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("id", customer.getId());
        editor.putString("email", customer.getEmail());
        editor.putString("firstname", customer.getFirstname());
        editor.putString("fullname", customer.getFullname());
        editor.putString("bvn", customer.getBvn());
        editor.putString("phone", customer.getPhone());
        editor.apply();
    }

    public Customer getCustomer(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new Customer(
                sharedPreferences.getInt("id", -1),
                sharedPreferences.getString("email", null),
                sharedPreferences.getString("firstname", null),
                sharedPreferences.getString("fullname", null),
                sharedPreferences.getString("bvn", null),
                sharedPreferences.getString("phone", null)
        );
    }

    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt("id", -1) != -1;
    }

    public void clear() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

}
