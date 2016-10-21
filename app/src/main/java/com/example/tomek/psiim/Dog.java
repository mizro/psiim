package com.example.tomek.psiim;

/**
 * Created by Tomek on 2016-10-21.
 */
public class Dog extends Animal{

    private String race;
    private String color;
    private String specialSigns;
    private String UniqNumber;

    public String getSpecialSigns() {
        return specialSigns;
    }

    public void setSpecialSigns(String specialSigns) {
        this.specialSigns = specialSigns;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getUniqNumber() {
        return UniqNumber;
    }

    public void setUniqNumber(String uniqNumber) {
        UniqNumber = uniqNumber;
    }

    public Dog(){

    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }
}
