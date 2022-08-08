package com.jlp.mvvm_jlp_project.pref;/*
 * Created by Sandeep(Techno Learning) on 14,July,2022
 */


import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;

import dagger.hilt.android.qualifiers.ActivityContext;

public class AppPreferencesHelper implements PreferencesHelper {

    private static final String PREF_FILE_NAME = "APP_PREF_FILE_DATA";
    private static final String PREF_KEY_USERNAME = "PREF_KEY_USERNAME";
    private static final String PREF_KEY_USER_ID = "PREF_KEY_USER_ID";
    private static final String PREF_KEY_DELIVERY_CENTER_ID = "PREF_KEY_DELIVERY_CENTER_ID";
    private static final String PREF_KEY_DELIVERY_CENTER_NAME = "PREF_KEY_DELIVERY_CENTER_NAME";
    private final SharedPreferences mPrefs;

    @Inject
    public AppPreferencesHelper(@ActivityContext Context context) {
        mPrefs = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public String getUsername() {
        return mPrefs.getString(PREF_KEY_USERNAME, null);
    }

    @Override
    public void setUsername(String username) {
        mPrefs.edit().putString(PREF_KEY_USERNAME, username).apply();
    }

    @Override
    public String getUserId() {
        return mPrefs.getString(PREF_KEY_USER_ID, null);
    }

    @Override
    public void setUserId(String userId) {
        mPrefs.edit().putString(PREF_KEY_USER_ID, userId).apply();
    }

    @Override
    public String getDeliveryCentreId() {
        return mPrefs.getString(PREF_KEY_DELIVERY_CENTER_ID, null);
    }

    @Override
    public void setDeliveryCentreId(String deliveryCentreId) {
        mPrefs.edit().putString(PREF_KEY_DELIVERY_CENTER_ID, deliveryCentreId).apply();
    }

    @Override
    public String getDeliveryCentreName() {
        return mPrefs.getString(PREF_KEY_DELIVERY_CENTER_NAME, null);
    }

    @Override
    public void setDeliveryCentreName(String deliveryCentreName) {
        mPrefs.edit().putString(PREF_KEY_DELIVERY_CENTER_NAME, deliveryCentreName).apply();
    }
}
