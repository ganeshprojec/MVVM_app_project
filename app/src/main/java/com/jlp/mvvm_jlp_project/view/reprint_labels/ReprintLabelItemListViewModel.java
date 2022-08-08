package com.jlp.mvvm_jlp_project.view.reprint_labels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import com.jlp.mvvm_jlp_project.model.DeliveryGoodProduct;
import com.jlp.mvvm_jlp_project.model.request.reprint_label_detail.RequestEnvelopeReprintLabel;
import com.jlp.mvvm_jlp_project.model.response.reprint_label_detail.ResponseDataReprintLabel;
import com.jlp.mvvm_jlp_project.repository.ReprintLabelDetailRepository;
import com.jlp.mvvm_jlp_project.utils.Resource;
import com.jlp.mvvm_jlp_project.viewmodel.BaseViewModel;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class ReprintLabelItemListViewModel extends BaseViewModel {
    ReprintLabelDetailRepository  reprintLabelDetailRepository;
    public MutableLiveData<List<DeliveryGoodProduct>> reprintLableItemModeldata = new MutableLiveData<>();
    public MutableLiveData<Resource<ResponseDataReprintLabel>> responseDataReprintLabel = new MutableLiveData<>();


    @Inject
    public ReprintLabelItemListViewModel(@NonNull Application application, ReprintLabelDetailRepository repository) {
        super(application);
        this.reprintLabelDetailRepository = repository;
        this.responseDataReprintLabel = repository._responseDataReprintLabel;
    }

    public void findReprintLabelDetail(RequestEnvelopeReprintLabel envelope){
        reprintLabelDetailRepository.findReprintLabelData(envelope);
    }
}
