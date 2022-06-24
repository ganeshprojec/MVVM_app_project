package com.jlp.mvvm_jlp_project.viewmodel;
import androidx.lifecycle.MutableLiveData;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;


@HiltViewModel
public class ItemEnquiryViewModel extends BaseViewModel {

    private MutableLiveData<String> usernameMutableLiveData;

    @Inject
    public ItemEnquiryViewModel() {

    }


}