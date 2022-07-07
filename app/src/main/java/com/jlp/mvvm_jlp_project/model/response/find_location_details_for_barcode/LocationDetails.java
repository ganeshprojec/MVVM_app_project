package com.jlp.mvvm_jlp_project.model.response.find_location_details_for_barcode;/*
 * Created by Sandeep(Techno Learning) on 04,July,2022
 */

import android.os.Parcel;
import android.os.Parcelable;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import javax.inject.Inject;

@Root(name = "LocationDetails", strict = false)
public class LocationDetails implements Parcelable {

    @Inject public LocationDetails() {}

    @Element(name = "locationId",required = false)
    public String locationId;
    @Element(name = "name15",required = false)
    public String name15;

    protected LocationDetails(Parcel in) {
        locationId = in.readString();
        name15 = in.readString();
    }

    public static final Creator<LocationDetails> CREATOR = new Creator<LocationDetails>() {
        @Override
        public LocationDetails createFromParcel(Parcel in) {
            return new LocationDetails(in);
        }

        @Override
        public LocationDetails[] newArray(int size) {
            return new LocationDetails[size];
        }
    };

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getName15() {
        return name15;
    }

    public void setName15(String name15) {
        this.name15 = name15;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(locationId);
        parcel.writeString(name15);
    }
}
