package com.jlp.mvvm_jlp_project.utils;/*
 * Created by Sandeep(Techno Learning) on 20,June,2022
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.jlp.mvvm_jlp_project.R;

import dagger.hilt.android.qualifiers.ActivityContext;

public class Helper {

    public static void hideKeyboard(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void redirectToActivity(Activity activity, Class<?> redirectTo, boolean isFinishCurrentAct) {
        Intent intent = new Intent(activity, redirectTo);
        activity.startActivity(intent);
        if (isFinishCurrentAct) {
            activity.finish();
        }
    }

    public static void addFragment(@ActivityContext Context context, Fragment fragment) {
        hideKeyboard((AppCompatActivity) context, fragment.getView());
        clearBackStack(context);

        FragmentTransaction transaction = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.frame_container_main, fragment); //main_fragment_container
        transaction.addToBackStack(((AppCompatActivity) context).getString(R.string.backstack_tag));
        transaction.commit();
    }


    public static void clearBackStack(@ActivityContext Context context) {
        FragmentManager manager = ((AppCompatActivity) context).getSupportFragmentManager();
        if (manager.getBackStackEntryCount() > 0) {
            FragmentManager.BackStackEntry first = manager.getBackStackEntryAt(0);
            manager.popBackStack(first.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }


    public static String getXmlString(@ActivityContext Context context, int strResId) {
        return ((AppCompatActivity)context).getString(strResId);
    }

}
