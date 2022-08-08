package com.jlp.mvvm_jlp_project.model;/*
 * Created by Sandeep(Techno Learning) on 29,June,2022
 */

public class TitleValueDataModel {
    private int number;
    private int title;
    private String value;

    public TitleValueDataModel(int title, String value) {
        this.title = title;
        this.value = value;
    }

    public TitleValueDataModel(int number, int title, String value) {
        this.number = number;
        this.title = title;
        this.value = value;
    }

    public int getTitle() {
        return title;
    }
    public void setTitle(int title) {
        this.title = title;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}