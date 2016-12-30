package com.studio.roy.pololuandroid.SurfaceView;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Royal on 30/12/2016.
 */

public class value extends Application {

    private String someVariable;
    private int value;
    private List<pt> mylistPath =   new ArrayList<pt>();

    public String getSomeVariable() {
        return someVariable;
    }

    public void setSomeVariable(String someVariable) {
        this.someVariable = someVariable;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public List<pt> getMylistPath() {
        return mylistPath;
    }

    public void setMylistPath(List<pt> mylistPath) {
        this.mylistPath = mylistPath;
    }
}
