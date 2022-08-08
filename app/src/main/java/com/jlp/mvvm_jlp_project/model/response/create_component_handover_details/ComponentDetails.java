package com.jlp.mvvm_jlp_project.model.response.create_component_handover_details;/*
 * Created by Sandeep(Techno Learning) on 28,July,2022
 */

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

public class ComponentDetails {
    @Element(name = "componentDetail",required = false)
    public ComponentDetail componentDetail;

    public ComponentDetail getComponentDetail() {
        return componentDetail;
    }

    public void setComponentDetail(ComponentDetail componentDetail) {
        this.componentDetail = componentDetail;
    }
}
