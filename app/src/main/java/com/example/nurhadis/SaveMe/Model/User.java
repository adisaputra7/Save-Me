package com.example.nurhadis.SaveMe.Model;


public class User {
    private String uidUser;
    private String fullname;
    private String emailUser;
    private String passUser;
    private String incomes;
    private String save;
    private String expenses;


    public User(){

    }

    public User(String fullname, String emailUser, String passUser, String incomes, String save, String expenses) {
        this.fullname = fullname;
        this.emailUser = emailUser;
        this.passUser = passUser;
        this.incomes = incomes;
        this.save = save;
        this.expenses = expenses;
    }

    public String getIncomes() {
        return incomes;
    }

    public void setIncomes(String incomes) {
        this.incomes = incomes;
    }

    public String getSave() {
        return save;
    }

    public void setSave(String save) {
        this.save = save;
    }

    public String getExpenses() {
        return expenses;
    }

    public void setExpenses(String expenses) {
        this.expenses = expenses;
    }

    public String getUid() {
        return uidUser;
    }

    public String getUidUser() {
        return uidUser;
    }

    public void setUidUser(String uidUser) {
        this.uidUser = uidUser;
    }

    public String getPassUser() {
        return passUser;
    }

    public void setPassUser(String passUser) {
        this.passUser = passUser;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

}
