package com.example.codingchallenge.model.data;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "characters")
public class CharacterEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String name;
    public String hairColor;
    public String skinColor;
    public String eyeColor;
    public String birthYear;
    public String gender;
    public String height;
    public String mass;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setMass(String mass) {
        this.mass = mass;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    public void setSkinColor(String skinColor) {
        this.skinColor = skinColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public void setBirthYear(String birthYear) {
        this.birthYear = birthYear;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public int getId() {
        return id;
    }

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
        return hairColor;
    }

    public String getSkinColor() {
        return skinColor;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public String getBirthYear() {
        return birthYear;
    }

    public String getGender() {
        return gender;
    }



}

