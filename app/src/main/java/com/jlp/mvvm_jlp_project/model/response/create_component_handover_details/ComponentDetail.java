package com.jlp.mvvm_jlp_project.model.response.create_component_handover_details;/*
 * Created by Sandeep(Techno Learning) on 28,July,2022
 */

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

public class ComponentDetail {
    @Element(name = "componentNum",required = false)
    public int componentNum;
    @Element(name = "componentId",required = false)
    public String componentId;
    @Element(name = "componentBarcode",required = false)
    public String componentBarcode;
    @Element(name = "componentStatus",required = false)
    public String componentStatus;

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

    public String getComponentBarcode() {
        return componentBarcode;
    }

    public void setComponentBarcode(String componentBarcode) {
        this.componentBarcode = componentBarcode;
    }

    public String getComponentStatus() {
        return componentStatus;
    }

    public void setComponentStatus(String componentStatus) {
        this.componentStatus = componentStatus;
    }
}
