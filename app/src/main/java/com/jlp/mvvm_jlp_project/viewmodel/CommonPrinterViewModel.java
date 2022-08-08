package com.jlp.mvvm_jlp_project.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.jlp.mvvm_jlp_project.model.request.printer_list.RequestEnveloperPrinterList;
import com.jlp.mvvm_jlp_project.model.response.printer_list.ResponseDataPrinterList;
import com.jlp.mvvm_jlp_project.repository.CommonPrinterListRepository;
import com.jlp.mvvm_jlp_project.utils.Resource;
import javax.inject.Inject;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class CommonPrinterViewModel extends  BaseViewModel
{

    CommonPrinterListRepository commonPrinterListRepository;
    public MutableLiveData<Resource<ResponseDataPrinterList>> responseDataPrinterList = new MutableLiveData<>();

    @Inject
    public CommonPrinterViewModel(@NonNull Application application, CommonPrinterListRepository repository) {
        super(application);
        this.commonPrinterListRepository = repository;
        this.responseDataPrinterList = repository._responseDataPrinterList;

    }

    public void getPrinterList(RequestEnveloperPrinterList envelope){
        commonPrinterListRepository.findPrinterListData(envelope);
    }
}
