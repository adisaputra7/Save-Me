package com.example.nurhadis.SaveMe.Model;

/**
 * Created by Nur Hadi S on 24/06/2017.
 */

public class Incomes {

    private String number;
    private String category;
    private String date;
    private String note;

    public Incomes() {

    }

    public Incomes(String number, String category, String date, String note) {
        this.number = number;
        this.category = category;
        this.date = date;
        this.note = note;
    }


    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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
}
