package com.jlp.mvvm_jlp_project.model.request.route_item_update_status;

import com.jlp.mvvm_jlp_project.model.ItemStatusDetails;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import javax.inject.Inject;

@Root(name = "UpdateItemStatusRequest", strict = false)
public class RequestDataUpdateItemStatus {

    @Element(name = "ItemStatusDetails", required = false)
    ItemStatusDetails itemStatus = new ItemStatusDetails();

    @Inject
    public RequestDataUpdateItemStatus() {
    }

    public ItemStatusDetails getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(ItemStatusDetails itemStatus) {
        this.itemStatus = itemStatus;
    }


}
