package com.studio.roy.pololuandroid.Class;

import android.net.Uri;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.Serializable;

/**
 * Created by Royal on 25/11/2016.
 */

public class User  {

    String name;
    String email;
    String password;
    Uri photoUrl;
    FirebaseAuth user;


    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Uri getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(Uri photoUrl) {
        this.photoUrl = photoUrl;
    }

    public FirebaseAuth getUser() {
        return user;
    }

    public void setUser(FirebaseAuth user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
