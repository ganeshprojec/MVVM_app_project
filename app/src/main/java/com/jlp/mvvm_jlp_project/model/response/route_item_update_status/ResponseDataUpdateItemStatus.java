package com.jlp.mvvm_jlp_project.model.response.route_item_update_status;

import android.os.Parcel;
import android.os.Parcelable;

import com.jlp.mvvm_jlp_project.model.ItemStatusDetails;
import com.jlp.mvvm_jlp_project.model.response.DITSErrors;
import com.jlp.mvvm_jlp_project.utils.Constants;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

import javax.inject.Inject;

@Root(name = "UpdateItemStatusResponse", strict = false)
@Namespace(reference = Constants.NAMESPACE)
public class ResponseDataUpdateItemStatus implements Parcelable {

    @Element(name = "ItemStatusDetails", required = false)
    public ItemStatusDetails routeDetails;

    @Element(name = "DITSErrors", required = false)
    private DITSErrors ditsErrors;

    @Inject
    ResponseDataUpdateItemStatus() {
    }

    protected ResponseDataUpdateItemStatus(Parcel in) {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ResponseDataUpdateItemStatus> CREATOR = new Creator<ResponseDataUpdateItemStatus>() {
        @Override
        public ResponseDataUpdateItemStatus createFromParcel(Parcel in) {
            return new ResponseDataUpdateItemStatus(in);
        }

        @Override
        public ResponseDataUpdateItemStatus[] newArray(int size) {
            return new ResponseDataUpdateItemStatus[size];
        }
    };

    public ItemStatusDetails getModel() {
        return routeDetails;
    }

    public void setModel(ItemStatusDetails routeDetails) {
        this.routeDetails = routeDetails;
    }

    public DITSErrors getDitsErrors() {
        return ditsErrors;
    }

    public void setDitsErrors(DITSErrors ditsErrors) {
        this.ditsErrors = ditsErrors;
    }
}
