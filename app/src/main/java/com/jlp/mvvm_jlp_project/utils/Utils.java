package com.jlp.mvvm_jlp_project.utils;/*
 * Created by Sandeep(Techno Learning) on 13,June,2022
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.ParseException;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.material.snackbar.Snackbar;
import com.jlp.mvvm_jlp_project.R;
import com.jlp.mvvm_jlp_project.model.response.authenticate_user.DeliveryCentreNumber;
import com.jlp.mvvm_jlp_project.model.response.authenticate_user.ResponseDataAuthenticateUser;
import com.jlp.mvvm_jlp_project.view.home.HomeActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    /**
     * Create a progress bar which shows the progress
     *
     * @param context
     * @return Progress Dialog to hide from where it is called
     */
    public static ProgressDialog showLoadingDialog(Context context) {
        ProgressDialog mDialog = new ProgressDialog(context);
        mDialog.setMessage("Loading...");
        mDialog.setCancelable(false);
        mDialog.show();
        return mDialog;
    }

    /**
     * Show the snackbar for error messages
     * @param activity On which activity we are showing this
     * @param message error message
     */
    public static void showErrorMessage(Activity activity, String message) {
        Snackbar snackbar = Snackbar.make(activity.findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG*2);
        View snackBarView = snackbar.getView();
        snackBarView.setBackgroundColor(activity.getResources().getColor(R.color.snackbar_background));
        snackbar.setTextColor(activity.getResources().getColor(R.color.red));
        snackbar.show();
    }

    /**
     * Checks if the Internet connection is available.
     * @return Returns true if the Internet connection is available. False otherwise.
     **/
    public static boolean isInternetAvailable(Context ctx) {
        // using received context (typically activity) to get SystemService causes memory link as this holds strong reference to that activity.
        // use application level context instead, which is available until the app dies.
        ConnectivityManager connectivityManager = (ConnectivityManager) ctx.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        // if network is NOT available networkInfo will be null
        // otherwise check if we are connected
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        return false;
    }

    /**
     * Show progressbar for long running tasks like api call
     * @param context
     * @return
     */
    public static ProgressDialog showProgressBar(Context context){
        ProgressDialog pDialog = ProgressDialog.show(context,
                "",
                "Loading...",
                false);
        return pDialog;
    }

    /**
     * Hide progressbar
     *
     * @param pDialog Currently visible progress dialog
     */
    public static void hideProgressDialog(ProgressDialog pDialog) {
        if (pDialog != null) {
            pDialog.dismiss();
        }
    }

    /**
     * Convert date into format
     *
     * @param inputDate input date that is to be converted in format dd/MM/YYYY
     */
    public static String formatDate(String inputDate) {

        String formattedDate = "";
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date date = null;
            //date = inputFormat.parse("2020-05-03T00:00:00");
            date = inputFormat.parse(inputDate);
            formattedDate = outputFormat.format(date);
            if (formattedDate == null || formattedDate.length() == 0) {
                formattedDate = inputDate;
            }
            //System.out.println("coverted: "+formattedDate);
        } catch (ParseException | java.text.ParseException e) {
            e.printStackTrace();
        }

        return "" + formattedDate;
    }
}
