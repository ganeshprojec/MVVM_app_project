package com.jlp.mvvm_jlp_project.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class TemplateViewModel extends BaseViewModel {

    @Inject
    public TemplateViewModel(@NonNull Application application) {
        super(application);

    }


}
