package com.jlp.mvvm_jlp_project.model.response.record_location_of_item;


import android.os.Parcel;
import android.os.Parcelable;

import com.jlp.mvvm_jlp_project.model.request.record_location_of_item.LocationItemDetails;
import com.jlp.mvvm_jlp_project.model.response.DITSErrors;
import com.jlp.mvvm_jlp_project.model.response.find_location_details_for_barcode.LocationDetails;
import com.jlp.mvvm_jlp_project.utils.Constants;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

import javax.inject.Inject;

/**
 *  Created by Sandeep(Techno Learning) on 16,June,2022
 */

@Root(name = "FindLocationDetailsForBarcodeResponse", strict = false)
@Namespace(reference = Constants.NAMESPACE)
public class ResponseDataRecordLocationOfItem implements Parcelable {
    @Element(name = "LocationItemDetails", required = false)
    public LocationItemDetails locationItemDetails;

    @Element(name = "DITSErrors",required = false)
    private DITSErrors ditsErrors;

    @Inject
    ResponseDataRecordLocationOfItem(){}

    protected ResponseDataRecordLocationOfItem(Parcel in) {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ResponseDataRecordLocationOfItem> CREATOR = new Creator<ResponseDataRecordLocationOfItem>() {
        @Override
        public ResponseDataRecordLocationOfItem createFromParcel(Parcel in) {
            return new ResponseDataRecordLocationOfItem(in);
        }

        @Override
        public ResponseDataRecordLocationOfItem[] newArray(int size) {
            return new ResponseDataRecordLocationOfItem[size];
        }
    };

    public LocationItemDetails getLocationItemDetails() {
        return locationItemDetails;
    }

    public void setLocationItemDetails(LocationItemDetails locationItemDetails) {
        this.locationItemDetails = locationItemDetails;
    }

    public DITSErrors getDitsErrors() {
        return ditsErrors;
    }

    public void setDitsErrors(DITSErrors ditsErrors) {
        this.ditsErrors = ditsErrors;
    }
}
