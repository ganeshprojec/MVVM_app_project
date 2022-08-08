package com.jlp.mvvm_jlp_project.utils;/*
 * Created by Sandeep(Techno Learning) on 13,June,2022
 */

import android.app.Activity;
import android.app.Dialog;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.LayoutInflater;
import android.net.ParseException;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.widget.LinearLayout;

import com.google.android.material.snackbar.Snackbar;
import com.jlp.mvvm_jlp_project.R;
import com.jlp.mvvm_jlp_project.model.response.authenticate_user.DeliveryCentreNumber;
import com.jlp.mvvm_jlp_project.model.response.authenticate_user.ResponseDataAuthenticateUser;
import com.jlp.mvvm_jlp_project.view.home.HomeActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.jlp.mvvm_jlp_project.view.common_barcode_scanner.CommonBarcodeScannerFragment;
import com.jlp.mvvm_jlp_project.view.common_printer_list.CommonPrinterListFragment;


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
        Snackbar snackbar = Snackbar.make(activity.findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT);
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
     * @param pDialog Currently visible progress dialog
     */
    public static void hideProgressDialog(ProgressDialog pDialog) {
        if (pDialog!=null) {
            pDialog.dismiss();
        }
    }


    public static void showAmendAlertDialog(final Context context, String deliveryNumber, String labelsPrinted, String printerId,String scrren)
    {

        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.warning_alert_dialog);
        Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);

         TextView headerText =  dialog.findViewById(R.id.title_Alert_Dialog);
         TextView msg =dialog.findViewById(R.id.message_Alert_Dialog);
         ImageView error_worning_icon = dialog.findViewById(R.id.error_worning_icon);
                   error_worning_icon.setVisibility(View.GONE);

        headerText.setText(context.getResources().getString(R.string.delivery_number)+" : "+deliveryNumber);
        msg.setText(labelsPrinted+" "+context.getResources().getString(R.string.lable_printed_on_printer)+" "+printerId);

        TextView  textOK_alert_dialog = dialog.findViewById(R.id.textOK_alert_dialog);
        textOK_alert_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    dialog.dismiss();

                   if(scrren.equals(AppConstants.FRAGMENT_AMEND_LOTS))
                   {
                       CommonPrinterListFragment fragment2 = new CommonPrinterListFragment(AppConstants.FRAGMENT_AMEND_LOTS);
                       FragmentManager fragmentManager =((AppCompatActivity)context).getSupportFragmentManager();
                       FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                       fragmentTransaction.replace(R.id.frame_container_main, fragment2);
                       fragmentTransaction.addToBackStack("TAG");
                       fragmentTransaction.commit();
                   }
                   else if(scrren.equals(AppConstants.FRAGMENT_REPRINT_LABELS))
                   {
                       CommonPrinterListFragment fragment2 = new CommonPrinterListFragment(AppConstants.FRAGMENT_REPRINT_LABELS);
                       FragmentManager fragmentManager =((AppCompatActivity)context).getSupportFragmentManager();
                       FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                       fragmentTransaction.replace(R.id.frame_container_main, fragment2);
                       fragmentTransaction.addToBackStack("TAG");
                       fragmentTransaction.commit();
                   }
                 }
               });
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();

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
