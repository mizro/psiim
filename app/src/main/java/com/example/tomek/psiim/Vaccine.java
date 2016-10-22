package com.example.tomek.psiim;

/**
 * Created by Tomek on 2016-10-22.
 */
public class Vaccine {

    private String date;
    private String dogName;
    private String ownerid;
    private String type;
    private String nextdate;

    public String getNextdate() {
        return nextdate;
    }

    public void setNextdate(String nextdate) {
        this.nextdate = nextdate;
    }

    public Vaccine(){

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDogName() {
        return dogName;
    }

    public void setDogName(String dogName) {
        this.dogName = dogName;
    }

    public String getOwnerid() {
        return ownerid;
    }

    public void setOwnerid(String ownerid) {
        this.ownerid = ownerid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

