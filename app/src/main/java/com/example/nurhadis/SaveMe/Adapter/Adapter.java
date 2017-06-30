package com.example.nurhadis.SaveMe.Adapter;

public class Adapter {
    private String category;
    private String note;
    private String value;

    public Adapter() {

    }

    public Adapter(String category, String note, String value) {
        this.category = category;
        this.note = note;
        this.value = value;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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
