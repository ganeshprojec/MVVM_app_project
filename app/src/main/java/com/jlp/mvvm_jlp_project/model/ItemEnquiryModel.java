package com.jlp.mvvm_jlp_project.model;/*
 * Created by Sandeep(Techno Learning) on 29,June,2022
 */

public class ItemEnquiryModel {
    private String title;
    private String value;


    public ItemEnquiryModel(String deliveryNumber,
                            String deliveryNumbers
    )
    {
        this.title = deliveryNumber;
        this.value = deliveryNumbers;

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
}