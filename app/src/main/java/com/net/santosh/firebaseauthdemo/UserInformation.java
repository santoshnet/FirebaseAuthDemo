package com.net.santosh.firebaseauthdemo;

/**
 * Created by BAPUNU on 14-04-2017.
 */
public class UserInformation {
    String name;
    String address;

    UserInformation() {

    }

    UserInformation(String name, String address) {
        this.name = name;
        this.address = address;

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
}
