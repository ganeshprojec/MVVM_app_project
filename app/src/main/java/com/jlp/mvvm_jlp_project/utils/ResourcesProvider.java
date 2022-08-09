package com.jlp.mvvm_jlp_project.utils;/*
 * Created by Sandeep(Techno Learning) on 02,August,2022
 */

import android.content.Context;

import androidx.annotation.StringRes;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.hilt.android.qualifiers.ApplicationContext;

@Singleton
public class ResourcesProvider {

    private Context mContext;

    @Inject public ResourcesProvider(@ApplicationContext Context context) {
        mContext = context;
    }

    public String getString(@StringRes int stringResId) {
        return mContext.getString(stringResId);
    }
}

//@Singleton
//class ResourcesProvider @Inject constructor(
//    @ApplicationContext private val context: Context
//) {
//    fun getString(@StringRes stringResId: Int): String {
//        return context.getString(stringResId)
//    }
//}
