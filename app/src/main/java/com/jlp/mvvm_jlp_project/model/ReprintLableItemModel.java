package com.jlp.mvvm_jlp_project.model;/*
 * Created by Sandeep(Techno Learning) on 29,June,2022
 */

public class ReprintLableItemModel {

    private String title;
    private String value;
    private boolean isSelected = false;



    public ReprintLableItemModel(String title,String value,boolean isSelected) {
        this.title = title;
        this.value = value;
        this.isSelected = isSelected;

    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }







}