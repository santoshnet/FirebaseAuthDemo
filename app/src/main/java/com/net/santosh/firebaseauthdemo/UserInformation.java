package com.net.santosh.firebaseauthdemo;

/**
 * Created by BAPUNU on 14-04-2017.
 */
public class UserInformation {
    String name;
    String address;
    String email;
    String uid;

    UserInformation() {

    }

    UserInformation(String name, String address, String email, String uid) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.uid = uid;

    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
