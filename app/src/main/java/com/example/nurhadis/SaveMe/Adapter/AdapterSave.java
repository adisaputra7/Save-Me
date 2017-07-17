package com.example.nurhadis.SaveMe.Adapter;

/**
 * Created by Nur Hadi S on 14/07/2017.
 */

public class AdapterSave {
    private String name;
    private String note;
    private String value;
    private String date;

    public AdapterSave() {

    }

    public AdapterSave(String name, String note, String value, String date) {
        this.name = name;
        this.note = note;
        this.value = value;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

