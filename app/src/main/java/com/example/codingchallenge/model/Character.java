package com.example.codingchallenge.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Character implements Parcelable {
    private String name;
    private String height;
    private String mass;
    private String hair_color;
    private String skin_color;
    private String eye_color;
    private String birth_year;
    private String gender;

    //Getters
    public String getName() {
        return name;
    }
    public String getHeight() {
        return height;
    }
    public String getMass() {
        return mass;
    }
    public String getHairColor() {
        return hair_color;
    }
    public String getSkinColor() {
        return skin_color;
    }
    public String getEyeColor() {
        return eye_color;
    }
    public String getBirthYear() {
        return birth_year;
    }
    public String getGender() {
        return gender;
    }

    // Parcelable implementation
    protected Character(Parcel in) {
        name = in.readString();
        height = in.readString();
        mass = in.readString();
        hair_color = in.readString();
        skin_color = in.readString();
        eye_color = in.readString();
        birth_year = in.readString();
        gender = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(height);
        dest.writeString(mass);
        dest.writeString(hair_color);
        dest.writeString(skin_color);
        dest.writeString(eye_color);
        dest.writeString(birth_year);
        dest.writeString(gender);

    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Character> CREATOR = new Creator<Character>() {
        @Override
        public Character createFromParcel(Parcel in) {
            return new Character(in);
        }

        @Override
        public Character[] newArray(int size) {
            return new Character[size];
        }
    };
}

