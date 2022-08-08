package com.jlp.mvvm_jlp_project.model.response.route_details;

import android.os.Parcel;
import android.os.Parcelable;

import com.jlp.mvvm_jlp_project.model.RouteDeliveryDetails;
import com.jlp.mvvm_jlp_project.model.response.DITSErrors;
import com.jlp.mvvm_jlp_project.utils.Constants;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

import javax.inject.Inject;

@Root(name = "FindDeliveriesAndDeliveryItemsResponse", strict = false)
@Namespace(reference = Constants.NAMESPACE)
public class ResponseDataRouteDetails implements Parcelable {

    @Element(name = "DeliveryDetails", required = false)
    public RouteDeliveryDetails routeDetails;

    @Element(name = "DITSErrors", required = false)
    private DITSErrors ditsErrors;

    @Inject
    ResponseDataRouteDetails() {
    }

    protected ResponseDataRouteDetails(Parcel in) {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ResponseDataRouteDetails> CREATOR = new Creator<ResponseDataRouteDetails>() {
        @Override
        public ResponseDataRouteDetails createFromParcel(Parcel in) {
            return new ResponseDataRouteDetails(in);
        }

        @Override
        public ResponseDataRouteDetails[] newArray(int size) {
            return new ResponseDataRouteDetails[size];
        }
    };

    public RouteDeliveryDetails getRouteDeliveryDetails() {
        return routeDetails;
    }

    public void setRouteDeliveryDetails(RouteDeliveryDetails routeDetails) {
        this.routeDetails = routeDetails;
    }

    public DITSErrors getDitsErrors() {
        return ditsErrors;
    }

    public void setDitsErrors(DITSErrors ditsErrors) {
        this.ditsErrors = ditsErrors;
    }
}






