package com.jlp.mvvm_jlp_project.model.response.find_location_details_for_barcode;


import android.os.Parcel;
import android.os.Parcelable;

import com.jlp.mvvm_jlp_project.model.response.DITSErrors;
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
public class ResponseDataFindLocationDetailsForBarcode implements Parcelable {
    @Element(name = "LocationDetails", required = false)
    public LocationDetails locationDetails;

    @Element(name = "DITSErrors",required = false)
    private DITSErrors ditsErrors;

    @Inject
    ResponseDataFindLocationDetailsForBarcode(){}

    protected ResponseDataFindLocationDetailsForBarcode(Parcel in) {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ResponseDataFindLocationDetailsForBarcode> CREATOR = new Creator<ResponseDataFindLocationDetailsForBarcode>() {
        @Override
        public ResponseDataFindLocationDetailsForBarcode createFromParcel(Parcel in) {
            return new ResponseDataFindLocationDetailsForBarcode(in);
        }

        @Override
        public ResponseDataFindLocationDetailsForBarcode[] newArray(int size) {
            return new ResponseDataFindLocationDetailsForBarcode[size];
        }
    };

    public LocationDetails getLocationDetails() {
        return locationDetails;
    }

    public void setLocationDetails(LocationDetails locationDetails) {
        this.locationDetails = locationDetails;
    }

    public DITSErrors getDitsErrors() {
        return ditsErrors;
    }

    public void setDitsErrors(DITSErrors ditsErrors) {
        this.ditsErrors = ditsErrors;
    }
}
