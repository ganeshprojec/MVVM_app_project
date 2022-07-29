package com.jlp.mvvm_jlp_project.utils;/*
 * Created by Sandeep(Techno Learning) on 17,June,2022
 */

public class AppConstants {
    public static final int CONNECT_TIME_OUT_IN_MIN = 20;
    public static final int WRITE_TIME_OUT_IN_MIN = 2;
    public static final int READ_TIME_OUT_IN_MIN = 2;

    public static final int MIN_PASSWORD_LENGTH = 6;
    public static final int MAX_PASSWORD_LENGTH = 8;

    public static final String SUCCESS = "SUCCESS";
    public static final String ERROR = "ERROR";

    public static final String ERROR_WHILE_GETTING_THE_RESPONSE = "Error while getting the response";
    public static final String ERROR_SOMETHING_WENT_WRONG = "Something Went Wrong";
    public static final String ERROR_RESPONSE_IS_NEITHER_SUCCESS_NOR_ERROR = "Response is neither success nor error";
    public static final String ERROR_WHILE_HANDLING_THE_RESPONSE = "Error while handling the response :";

    public static final int ERROR_NUMBER_FOR_PASSWORD_EXPIRES = 100;

    public static final String COMPONENT_BARCODE_DETAILS_DATA = "Barcode Details Data";
    public static final String LOCATION_BARCODE_DETAILS_DATA = "Location Details Data";
    public static final String ITEM_ENQUIRY_DETAILS_DATA = "Item Enquiry Details Data";

    public static final String FRAGMENT_ITEM_ENQUIRY = "Fragment Item Enquiry";
    public static final String FRAGMENT_ITEM_MOVEMENT_FOR_COMPONENT_BARCODE = "Fragment Item Movement For Component Barcode";
    public static final String FRAGMENT_ITEM_MOVEMENT_FOR_LOCATION_BARCODE = "Fragment Item Movement For Location Barcode";
    public static final String FRAGMENT_MULTI_MOVEMENT_FOR_LOCATION_BARCODE = "Fragment Multi Movement For Location Barcode";
    public static final String FRAGMENT_MULTI_MOVEMENT_FOR_COMPONENT_BARCODE = "Fragment Multi Movement For Component Barcode";
    public static final String FRAGMENT_CARRIER_HANDOVER_DETAILS = "Fragment Carrier Handover Details";
    public static final String FRAGMENT_CARRIER_HANDOVER_DELIVERY_ITEM_DETAILS = "Fragment Carrier Handover Delivery Item Details";
    public static final String FRAGMENT_CARRIER_COLLECTION_DETAILS = "Fragment Carrier Collection Details";
    public static final String FRAGMENT_ROUTE_MANAGEMENT = "Fragment Route Management";
    public static final String FRAGMENT_CHANGE_PASSWORD = "Fragment Change Password";
    public static final String FRAGMENT_CHANGE_PASSWORD_AND_LOGON = "Fragment Change Password And Logon";
    
    //from Existing code
    
    //region View Name static finalants
    public static final String LOGON_VIEW = "LogonView";
    public static final String CHANGE_PASSWORD_VIEW = "ChangePasswordView";
    public static final String MAIN_MENU_VIEW = "MainMenuView";
    public static final String SELECT_BRANCH_VIEW = "SelectBranchView";
    public static final String HANDOVER_DELIVERY_DETAILS_ENTER_DELIVERY_VIEW = "HandoverDeliveryDetailsEnterDeliveryView";
    public static final String TRACK_DELIVERY_SCAN_ITEM_VIEW = "TrackDeliveryScanItemView";
    public static final String TRACK_DELIVERY_DETAILS_VIEW = "TrackDeliveryDetailsView";
    public static final String TRACK_DELIVERY_ITEM_VIEW = "TrackDeliveryItemDetailsView";

    public static final String IN_BAY = "INBAY";
    public static final String ACTIVE = "ACTIVE";
    public static final String LOADED = "LOADED";

    // region Symbol static finalants
    public static final String UK_CURRENCY_SYMBOL = "£";

    //region Exception Messages static finalants
    public static final String AUTHENTICATION_RESULTS_VALID_USER = "Valid User";
    public static final String AUTHENTICATION_RESULTS_INVALID_USER = "InValid User";
    public static final String AUTHENTICATION_RESULTS_EXPIRED_PASSWORD = "Expired Password";
    public static final String AUTHENTICATION_RESULTS_NO_VALID_BRANCHES = "No Valid Branches Setup";

    public static final String CHANGE_PASSWORD_RESULTS = "SUCCESS";
    public static final String CHANGE_PASSWORD_RESULTS_FAILED = "failed";
    public static final String CHANGE_PASSWORD_NEW_PASSWORD_NOT_ACCEPTED = "New password has not been accepted for user";
    public static final String CHANGE_PASSWORD_LOGON_DETAILS_INCORRECT = "Logon details are incorrect for user";

    public static final String ONE_ZERO_ONE = "101";
    public static final String ONE_ZERO_TWO = "102";
    public static final String ONE_ZERO_THREE = "103";

    //region Is Scanner Active
    public static final boolean IS_SCANNER_ACTIVE  = true;
}
