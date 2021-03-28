package com.example.covid_dash.states;

import org.parceler.Parcel;

@Parcel
public class User {
    // fields must be package private
    String firstName;
    String lastName;

    // empty constructor needed by the Parceler library
    public User() {
    }

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}