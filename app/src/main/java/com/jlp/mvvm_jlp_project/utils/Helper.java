package com.jlp.mvvm_jlp_project.utils;/*
 * Created by Sandeep(Techno Learning) on 20,June,2022
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.jlp.mvvm_jlp_project.R;
import com.jlp.mvvm_jlp_project.interfaces.DialogListener;
import com.jlp.mvvm_jlp_project.model.response.authenticate_user.AuthenticationDetails;
import com.jlp.mvvm_jlp_project.pref.AppPreferencesHelper;
import com.jlp.mvvm_jlp_project.view.base.BaseDialogFragment;
import com.jlp.mvvm_jlp_project.view.home.HomeActivity;
import dagger.hilt.android.qualifiers.ActivityContext;

public class Helper {

    /**
     * Hide the keypad once click on button
     *
     * @param context
     * @param view
     */
    public static void hideKeyboard(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * @param activity
     * @param redirectTo
     * @param isFinishCurrentAct
     */
    public static void redirectToActivity(Activity activity, Class<?> redirectTo, boolean isFinishCurrentAct) {
        Intent intent = new Intent(activity, redirectTo);
        activity.startActivity(intent);
        if (isFinishCurrentAct) {
            activity.finish();
        }
    }

    /**
     * To add fragment in main back stack of Fragments
     *
     * @param context
     * @param fragment
     */
    public static void addFragment(@ActivityContext Context context, Fragment fragment) {
        //clearBackStack(context);
        FragmentTransaction transaction = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container_main, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    /**
     * To clear whole back stack on home page,
     * or come back to home from anywhere
     *
     * @param context
     */
    public static void clearBackStack(@ActivityContext Context context) {
        FragmentManager manager = ((AppCompatActivity) context).getSupportFragmentManager();
        if (manager.getBackStackEntryCount() > 0) {
            FragmentManager.BackStackEntry first = manager.getBackStackEntryAt(0);
            manager.popBackStack(first.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }


    /**
     * To get xml file string anywhere providing context
     *
     * @param context
     * @param strResId
     */
    public static String getXmlString(Application context, int strResId) {
        return context.getString(strResId);
    }


    /**
     * To start dialog fragment
     * @param context
     * @param dialogFragment
     * @param bundle
     * @param listener
     */
    public static void startDialogFragment(@ActivityContext Context context, BaseDialogFragment dialogFragment, Bundle bundle, DialogListener listener) {
        AppCompatActivity activity = ((AppCompatActivity) context);
        FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();

        dialogFragment.setListener(listener);
        dialogFragment.setArguments(bundle);

        Fragment prev = activity.getSupportFragmentManager().findFragmentByTag(BaseDialogFragment.PARAM_BUNDLE_DIALOG_TAG);
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        dialogFragment.show(ft, BaseDialogFragment.PARAM_BUNDLE_DIALOG_TAG);
    }

    public static void handleResponseAndDoLogin(AuthenticationDetails authenticationDetails,
                                                Activity activity, AppPreferencesHelper appPreferencesHelper) {
        if(isShowDeliveryCenterList(authenticationDetails)){
            selectDeliveryCenter(authenticationDetails, activity, appPreferencesHelper);
        }else{
            updateSharedPref(appPreferencesHelper, authenticationDetails);
            Helper.redirectToActivity(activity, HomeActivity.class, true);
        }
    }

    /**
     * Select delivery branch dialog decision
     * @param data of type ResponseDataAuthenticateUser
     * @return check the size of deliveryCentreNumber and if its more than one then return true else false
     */
    public static boolean isShowDeliveryCenterList(AuthenticationDetails authenticationDetails) {
        if(authenticationDetails.getDeliveryCentreNumber().size()>1)
            return true;
        else
            return false;
    }

    /**
     * Show a dialog to select delivery center number
     * @param response of type ResponseDataAuthenticateUser
     */
    public static void selectDeliveryCenter(AuthenticationDetails authenticationDetails,
                                      Activity activity, AppPreferencesHelper appPreferencesHelper) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(activity.getResources().getString(R.string.choose_delivery_center));
        final int[] selectedItemPosition = {0};
        builder.setSingleChoiceItems(extractDeliveryCenterNamesInArray(authenticationDetails), selectedItemPosition[0], new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                selectedItemPosition[0] = which;

            }
        });

        builder.setPositiveButton(activity.getResources().getString(R.string.login), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                updateSharedPref(appPreferencesHelper, authenticationDetails);
                Helper.redirectToActivity(activity, HomeActivity.class, true);
            }
        });
        builder.setNegativeButton(activity.getResources().getString(R.string.logout), null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     * get the delivery center name from the response
     * @param response of type ResponseDataAuthenticateUser
     * @return string values of delivery center names used for the dialog
     */
    public static String[] extractDeliveryCenterNamesInArray(AuthenticationDetails authenticationDetails) {
        String[] deliveryCenterName = new String[authenticationDetails.getDeliveryCentreNumber().size()];
        for(int i = 0;  i < authenticationDetails.getDeliveryCentreNumber().size(); i++){
            deliveryCenterName[i] = authenticationDetails.getDeliveryCentreNumber().get(i).getDeliveryCentreName();
        }
        return deliveryCenterName;
    }

    /**
     * Update the shared preferences for further use
     * @param response of type ResponseDataAuthenticateUser to get the username and userId to store
     * @param deliveryCentreId
     * @param deliveryCentreName
     */
    public static void updateSharedPref(AppPreferencesHelper appPreferencesHelper, AuthenticationDetails authenticationDetails) {
        appPreferencesHelper.setUsername(authenticationDetails.getUserName());
        appPreferencesHelper.setUserId(authenticationDetails.getUserId());
        appPreferencesHelper.setDeliveryCentreId(authenticationDetails.getDeliveryCentreNumber().get(0).getDeliveryCentreId());
        appPreferencesHelper.setDeliveryCentreName(authenticationDetails.getDeliveryCentreNumber().get(0).getDeliveryCentreName());
    }
}
