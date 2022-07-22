package com.jlp.mvvm_jlp_project.model.response.find_deliveries_and_delivery_items;/*
 * Created by Sandeep(Techno Learning) on 21,July,2022
 */

import org.simpleframework.xml.Element;

public class ComponentDetails {

    @Element(name = "componentNum",required = false)
    public int componentNum;
    @Element(name = "componentId",required = false)
    public String componentId;
    @Element(name = "componentBarcode",required = false)
    public int componentBarcode;
    @Element(name = "componentStatus",required = false)
    public String componentStatus;
    @Element(name = "componentStatusText",required = false)
    public int componentStatusText;

    public int getComponentNum() {
        return componentNum;
    }

    public void setComponentNum(int componentNum) {
        this.componentNum = componentNum;
    }

    public String getComponentId() {
        return componentId;
    }

    public void setComponentId(String componentId) {
        this.componentId = componentId;
    }

    public int getComponentBarcode() {
        return componentBarcode;
    }

    public void setComponentBarcode(int componentBarcode) {
        this.componentBarcode = componentBarcode;
    }

    public String getComponentStatus() {
        return componentStatus;
    }

    public void setComponentStatus(String componentStatus) {
        this.componentStatus = componentStatus;
    }

    public int getComponentStatusText() {
        return componentStatusText;
    }

    public void setComponentStatusText(int componentStatusText) {
        this.componentStatusText = componentStatusText;
    }
}
