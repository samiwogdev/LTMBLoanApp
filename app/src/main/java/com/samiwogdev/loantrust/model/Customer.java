package com.samiwogdev.loantrust.model;

import com.google.gson.annotations.SerializedName;

public class Customer {

    private int id;

    private String email;

    private String firstname;

    private String middleName;

    private String lastname;

    private String fullname;

    private String phone;

    private String bvn;

    private String dob;

    private String place_of_birth;

    @SerializedName("income")
    private String monthly_income;

    private String state;

    private String nationality;

    @SerializedName("address")
    private String home_address;

    private String password;

    public Customer(int id, String email, String firstname, String middleName, String lastname, String fullname, String phone, String bvn, String dob, String place_of_birth, String monthly_income, String state, String nationality, String home_address, String password) {
        this.id = id;
        this.email = email;
        this.firstname = firstname;
        this.middleName = middleName;
        this.lastname = lastname;
        this.fullname = fullname;
        this.phone = phone;
        this.bvn = bvn;
        this.dob = dob;
        this.place_of_birth = place_of_birth;
        this.monthly_income = monthly_income;
        this.state = state;
        this.nationality = nationality;
        this.home_address = home_address;
        this.password = password;
    }

    public Customer(int id, String email, String firstname, String fullname, String bvn, String phone){
        this.id = id;
        this.email = email;
        this.firstname = firstname;
        this.fullname = fullname;
        this.bvn = bvn;
        this.phone = phone;
    }

    public Customer( String state, String nationality,  String home_address){
        this.state = state;
        this.nationality = nationality;
        this.home_address = home_address;
    }

    public Customer(String monthly_income){
        this.monthly_income = monthly_income;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBvn() {
        return bvn;
    }

    public void setBvn(String bvn) {
        this.bvn = bvn;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPlace_of_birth() {
        return place_of_birth;
    }

    public void setPlace_of_birth(String place_of_birth) {
        this.place_of_birth = place_of_birth;
    }

    public String getMonthly_income() {
        return monthly_income;
    }

    public void setMonthly_income(String monthly_income) {
        this.monthly_income = monthly_income;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        nationality = nationality;
    }

    public String getHome_address() {
        return home_address;
    }

    public void setHome_address(String home_address) {
        this.home_address = home_address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
