package com.example.nurhadis.SaveMe.Adapter;

public class Adapter {
    private String category;
    private String notes;
    private String value;

    public Adapter() {

    }

    public Adapter(String category, String notes, String value) {
        this.category = category;
        this.notes = notes;
        this.value = value;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
