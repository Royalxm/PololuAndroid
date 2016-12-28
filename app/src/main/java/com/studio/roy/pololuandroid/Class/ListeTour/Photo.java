package com.studio.roy.pololuandroid.Class.ListeTour;

/**
 * Created by Royal on 16/12/2016.
 */

public class Photo {

    int IdPhoto;
    String NamePhoto;
    byte[] DataPhoto;

    public Photo() {
    }

    public int getIdPhoto() {
        return IdPhoto;
    }

    public void setIdPhoto(int idPhoto) {
        IdPhoto = idPhoto;
    }

    public String getNamePhoto() {
        return NamePhoto;
    }

    public void setNamePhoto(String namePhoto) {
        NamePhoto = namePhoto;
    }

    public  byte[] getDataPhoto() {
        return DataPhoto;
    }

    public void setDataPhoto( byte[] dataPhoto) {
        DataPhoto = dataPhoto;
    }
}
