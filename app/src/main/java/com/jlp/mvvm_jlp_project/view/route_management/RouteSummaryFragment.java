package com.jlp.mvvm_jlp_project.view.route_management;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.AppBarLayout;
import com.jlp.mvvm_jlp_project.R;
import com.jlp.mvvm_jlp_project.adapters.RouteDeliveryDetailsAdapter;
import com.jlp.mvvm_jlp_project.databinding.FragmentRouteSummaryNewBinding;
import com.jlp.mvvm_jlp_project.interfaces.AppBarStateChangeListener;
import com.jlp.mvvm_jlp_project.interfaces.ClickListener;
import com.jlp.mvvm_jlp_project.interfaces.DialogListener;
import com.jlp.mvvm_jlp_project.model.DeliveryDetails;
import com.jlp.mvvm_jlp_project.model.ItemStatusDetails;
import com.jlp.mvvm_jlp_project.model.LotsInfo;
import com.jlp.mvvm_jlp_project.model.RouteDeliveryDetails;
import com.jlp.mvvm_jlp_project.model.response.route_details.ResponseDataRouteDetails;
import com.jlp.mvvm_jlp_project.model.response.route_item_update_status.ResponseDataUpdateItemStatus;
import com.jlp.mvvm_jlp_project.utils.Helper;
import com.jlp.mvvm_jlp_project.utils.Resource;
import com.jlp.mvvm_jlp_project.utils.SpacesItemDecoration;
import com.jlp.mvvm_jlp_project.utils.Utils;
import com.jlp.mvvm_jlp_project.view.base.BaseDialogFragment;
import com.jlp.mvvm_jlp_project.view.base.BaseFragment;
import com.jlp.mvvm_jlp_project.view.common_barcode_scanner.CommonBarcodeScannerFragment;
import com.jlp.mvvm_jlp_project.viewmodel.RouteSummaryViewModel;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class RouteSummaryFragment extends BaseFragment implements ClickListener, View.OnClickListener, DialogListener {

    /*private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;*/
    private @NonNull
    FragmentRouteSummaryNewBinding binding;

    private RouteSummaryViewModel viewModel;
    private RouteDeliveryDetailsAdapter adapter;
    private ArrayList<DeliveryDetails> listDeliveryDetails = new ArrayList<>();
    private ProgressDialog progressDialog;
    private RouteDeliveryDetails details = new RouteDeliveryDetails();
    private ArrayList<ItemStatusDetails> missingItemsList = new ArrayList<>();

    private int currentPosition = 0;
    private int missingListPosition = -1;
    private String userAuthorizationId = "";

    public RouteSummaryFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //initPageParam();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(RouteSummaryViewModel.class);

        initObserver(view);
        initListener();


        currentPosition = 0;
        dummySummary();
        //dummyDeliveries();
        callRouteDeliveryDetails(viewModel.summary.getDeliveryNum().getDeliveryIds().get(currentPosition));

    }


    /**
     * @Author Ganesh
     * <p>
     * For setting data for route summary in ViewModel
     */
    private void dummySummary() {
        /*viewModel.summary.setRouteNumber("" + getString(R.string.dummy_route_number));
        viewModel.summary.setDeliveryDate("" + getString(R.string.dummy_delivery_date));
        viewModel.summary.setTotalDeliveries("" + getString(R.string.dummy_total_deliveries));
        viewModel.summary.setTotalLots("" + getString(R.string.dummy_total_lots));
        viewModel.summary.setDeliveryLotsLoaded("" + getString(R.string.dummy_total_lots_loaded));

        DeliveryNum deliveryNum = new DeliveryNum();
        deliveryNum.getDeliveryIds().clear();
        deliveryNum.getDeliveryIds().add("39721571");
        deliveryNum.getDeliveryIds().add("39721571");
        deliveryNum.getDeliveryIds().add("39721571");*/

        viewModel.summary = CommonBarcodeScannerFragment.routeSummary;
        //viewModel.summary.setDeliveryNum(deliveryNum);
        updateSummaryOnView();
    }


    /**
     * @param view
     * @Author Ganesh
     * <p>
     * For ViewModel Data-Observers defined here
     */
    private void initObserver(View view) {

        viewModel.responseRouteDeliveryDetails.observe(getViewLifecycleOwner(), new Observer<Resource<ResponseDataRouteDetails>>() {

            @Override
            public void onChanged(Resource<ResponseDataRouteDetails> response) {
                if (response.status != null) {
                    switch (response.status) {
                        case LOADING: {
                            if (currentPosition == 0) {
                                progressDialog = Utils.showProgressBar(getContext());
                            }
                            break;
                        }
                        case ERROR: {
                            Utils.hideProgressDialog(progressDialog);
                            //Log.e("errorSummary",""+response.message);
                            Utils.showErrorMessage(getActivity(), response.message);
                            break;
                        }
                        case SUCCESS: {
                            details = response.data.getRouteDeliveryDetails();
                            showRouteDeliveryDetailsOnView();

                            currentPosition++;
                            if (currentPosition < viewModel.summary.getDeliveryNum().getDeliveryIds().size()) {
                                callRouteDeliveryDetails(viewModel.summary.getDeliveryNum().getDeliveryIds().get(currentPosition));
                                //viewModel.callUpdateStatus();
                            } else {
                                Utils.hideProgressDialog(progressDialog);
                                viewModel.getBackupList().clear();
                                viewModel.getBackupList().addAll(listDeliveryDetails);
                            }

                            break;
                        }
                    }
                }
            }
        });


        viewModel.responseUpdateItemStatus.observe(getViewLifecycleOwner(), new Observer<Resource<ResponseDataUpdateItemStatus>>() {

            @Override
            public void onChanged(Resource<ResponseDataUpdateItemStatus> response) {
                if (response.status != null) {
                    switch (response.status) {
                        case LOADING: {
                            if (missingListPosition <= 0)
                                progressDialog = Utils.showProgressBar(getContext());
                            break;
                        }
                        case ERROR: {
                            Utils.hideProgressDialog(progressDialog);
                            Utils.showErrorMessage(getActivity(), response.message);
                            break;
                        }
                        case SUCCESS: {

                            ItemStatusDetails itemStatusDetails = response.data.getModel();
                            if (missingListPosition == -1) {
                                Utils.hideProgressDialog(progressDialog);
                                updateRouteDeliveryDetailsOnView(itemStatusDetails);
                            } else if (missingListPosition < missingItemsList.size()) {
                                missingListPosition++;
                                if (missingListPosition != missingItemsList.size()) {
                                    ItemStatusDetails itemStatusUpdate = missingItemsList.get(missingListPosition);
                                    //TODO: SetUsername after session management
                                    itemStatusUpdate.setUserName("ganesh123");
                                    itemStatusUpdate.setUserId("TPU111");
                                    itemStatusUpdate.setUserIdAuthorized("" + userAuthorizationId);
                                    itemStatusUpdate.setComponentStatus(LotsInfo.MISSING);

                                    callUpdateItemStatus(itemStatusUpdate);
                                } else {
                                    Utils.hideProgressDialog(progressDialog);
                                    missingListPosition = -1;
                                    Helper.addFragment(getActivity(), new SummaryFragment());
                                }
                            }


                            break;
                        }
                    }
                }
            }
        });


    }

    /**
     * @param
     * @Author Ganesh
     * <p>
     * For updating summary / setting summary on UI
     */
    public void updateSummaryOnView() {

        // Summary
        binding.layoutSummaryIncl.txtRouteNumberValue.setText("" + viewModel.summary.getRouteNumber());
        binding.layoutSummaryIncl.txtDeliveryDateValue.setText("" + viewModel.summary.getFormattedDeliveryDate());
        binding.layoutSummaryIncl.txTotalDeliveriesValue.setText("" + viewModel.summary.getTotalNumberOfDeliveriesCount());//summary.getTotalDeliveries()
        binding.layoutSummaryIncl.txTotalLotsValue.setText("" + viewModel.summary.getTotalLots());
        binding.layoutSummaryIncl.txTotalLotsLoadedValue.setText("" + viewModel.summary.getDeliveryLotsLoaded());
    }

    /**
     * @param
     * @Author Ganesh
     * <p>
     * For updating / setting summary on UI
     */
    public void showRouteDeliveryDetailsOnView() {
        // Add List to existing arrayList & notifydataset changed
        ArrayList<DeliveryDetails> tempList = details.getDetailsList();
        if (currentPosition == 0) {
            listDeliveryDetails.clear();
        }
        listDeliveryDetails.addAll(tempList);
        adapter.notifyDataSetChanged();
    }

    /**
     * @param itemStatusDetails ItemStatusDetails response
     * @Author Ganesh
     * <p>
     * For updating the status of individual lot, after sending for updateStatus service
     */
    public void updateRouteDeliveryDetailsOnView(ItemStatusDetails itemStatusDetails) {
        // TODO: Notify Adapter

        //Log.e("Response", "" + itemStatusDetails.toString());
    }


    /**
     * @param
     * @Author Ganesh
     * <p>
     * For initialization of objects, variables, & listeners, setting adapters
     */
    private void initListener() {
        showIconToolbar();

        setToolTitle(getString(R.string.str_route_management));

        //Dummy list funciton
        listDeliveryDetails = viewModel.getDummyList(getActivity());
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(RecyclerView.VERTICAL);
        //llm.setAutoMeasureEnabled(false);

        binding.recyList.setHasFixedSize(true);
        adapter = new RouteDeliveryDetailsAdapter(listDeliveryDetails, getActivity(), this, childListener);
        binding.recyList.setLayoutManager(llm);
        binding.recyList.setAdapter(adapter);
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.margin_padding_tiny);
        binding.recyList.addItemDecoration(new SpacesItemDecoration(spacingInPixels));

        binding.appbarLayoutRoute.addOnOffsetChangedListener(appBarStateChangeListener);
    }

    /**
     * @param title Title name
     * @Author Ganesh
     * <p>
     * For setting / updating toolbar title
     */
    private void setToolTitle(String title) {
        binding.homeTopheader1.txtToolbarTitle.setText("" + title);
        binding.homeTopheader.txtToolbarTitle.setText("" + title);
    }

    /**
     * @param deliveryId deliveryId from Summary
     * @Author Ganesh
     * <p>
     * For calling web service, for getting delivery details.
     */
    public void callRouteDeliveryDetails(String deliveryId) {
        // check Is empty, It will not empty maximum case
        if (Utils.isInternetAvailable(getActivity())) {
            viewModel.callRouteDetails(deliveryId);
        } else {
            Utils.hideProgressDialog(progressDialog);
            Utils.showErrorMessage(getActivity(), getResources().getString(R.string.please_check_internet_connection));
        }
    }

    /**
     * @param itemUpdateStatus ItemStatusDetails request status update
     * @Author Ganesh
     * <p>
     * For call webservice Status update for individual LOT, requesting update status
     */
    public void callUpdateItemStatus(ItemStatusDetails itemUpdateStatus) {

        if (Utils.isInternetAvailable(getActivity())) {
            viewModel.callUpdateStatus(itemUpdateStatus);
        } else {
            Utils.hideProgressDialog(progressDialog);
            Utils.showErrorMessage(getActivity(), getResources().getString(R.string.please_check_internet_connection));
        }
    }


    /**
     * @param
     * @Author Ganesh
     * <p>
     * For showing or setting icons on Toolbar.
     */
    private void showIconToolbar() {
        // Second Done Icon Enable
        binding.homeTopheader1.imgCloseSecond.setVisibility(View.VISIBLE);
        binding.homeTopheader.imgCloseSecond.setVisibility(View.VISIBLE);
        binding.homeTopheader1.imgCloseSecond.setImageResource(R.drawable.ic_done_24);
        binding.homeTopheader.imgCloseSecond.setImageResource(R.drawable.ic_done_24);
        binding.homeTopheader1.imgCloseSecond.setOnClickListener(this);
        binding.homeTopheader.imgCloseSecond.setOnClickListener(this);


        // Third Filter Icon Enable
        binding.homeTopheader1.imgCloseThird.setVisibility(View.VISIBLE);
        binding.homeTopheader.imgCloseThird.setVisibility(View.VISIBLE);
        binding.homeTopheader1.imgCloseThird.setImageResource(R.drawable.ic_filter_alt_24);
        binding.homeTopheader.imgCloseThird.setImageResource(R.drawable.ic_filter_alt_24);
        binding.homeTopheader1.imgCloseThird.setOnClickListener(this);
        binding.homeTopheader.imgCloseThird.setOnClickListener(this);
    }


    /*public static RouteSummaryFragment newInstance(String param1, String param2) {
        RouteSummaryFragment fragment = new RouteSummaryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);

        return fragment;
    }

    private void initPageParam() {
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }*/

    @Override
    protected View initViewBinding(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRouteSummaryNewBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    /**
     * @param index position of list
     * @param model Data over list item
     * @Author Ganesh
     * <p>
     * For click event handle for individual items
     */
    @Override
    public void onClickItem(int index, Object model) {

    }


    /**
     * @param view
     * @Author Ganesh
     * <p>
     * For ClickListeners for individual views
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgCloseSecond:

                // Check is this partial commit.
                if (DeliveryDetails.missingItemCount(listDeliveryDetails) <= 0) {

                    Helper.addFragment(getActivity(), new SummaryFragment());
                } else {
                    FilterByLocationDialogFragment dialogPartialSubmitFragment = new FilterByLocationDialogFragment();
                    Bundle bundlePartial = new Bundle();
                    bundlePartial.putBoolean(BaseDialogFragment.PARAM_NOT_ALERT_DIALOG, true);
                    //bundle.putString(BaseDialogFragment.PARAM_PAGE_NAME, getString(R.string.str_route_management));
                    bundlePartial.putString(BaseDialogFragment.PARAM_TITLE, getString(R.string.str_partial_sumit_title));
                    bundlePartial.putString(BaseDialogFragment.PARAM_MESSAGE, getString(R.string.str_partial_submit_message));
                    bundlePartial.putString(BaseDialogFragment.PARAM_HINT, getString(R.string.str_enter_authorization));
                    bundlePartial.putString(BaseDialogFragment.PARAM_POSITIVE_BUTTON, getString(R.string.str_ok));
                    bundlePartial.putString(BaseDialogFragment.PARAM_NEGATIVE_BUTTON, getString(R.string.str_cancel));

                    Helper.startDialogFragment(getActivity(), dialogPartialSubmitFragment, bundlePartial, partialSubmitDialogListener);
                }


                break;

            case R.id.imgCloseThird:
                FilterByLocationDialogFragment dialogFragment = new FilterByLocationDialogFragment();
                Bundle bundle = new Bundle();
                bundle.putBoolean(BaseDialogFragment.PARAM_NOT_ALERT_DIALOG, true);
                bundle.putString(BaseDialogFragment.PARAM_PAGE_NAME, getString(R.string.str_route_management));
                bundle.putString(BaseDialogFragment.PARAM_TITLE, getString(R.string.dummy_route_number));

                Helper.startDialogFragment(getActivity(), dialogFragment, bundle, this);
                break;
        }
    }


    /**
     * @param inputText String which is passed
     * @param flag      for custom boolean value, anything extra, clear,canceled event
     * @Author Ganesh
     * <p>
     * For onFinishedDialogListeners, gettting inputText
     */
    @Override
    public void onFinishDialog(String inputText, Boolean flag) {
        // Apply Filter
        if (!flag) {
            listDeliveryDetails.clear();
            listDeliveryDetails.addAll(viewModel.getFilterByLocation(inputText));
            adapter.notifyDataSetChanged();
        } else {
            // clear filter
            listDeliveryDetails.clear();
            listDeliveryDetails.addAll(viewModel.getBackupList());
            adapter.notifyDataSetChanged();
            //Utils.showErrorMessage(getActivity(), "Filter Cleared : ");
        }
    }


    /**
     * @Author Ganesh
     * <p>
     * For Dialog Listenr for partial listener
     * @param inputText Getting text input
     * @param flag custom boolean for clear, cancel
     */
    DialogListener partialSubmitDialogListener = new DialogListener() {
        @Override
        public void onFinishDialog(String inputText, Boolean flag) {
            // check for ok
            // Update status of All NOT_LOADED item as missing using for loop & then open Summary fragment
            userAuthorizationId = inputText;
            missingListPosition = 0;
            missingItemsList = DeliveryDetails.getAllMissingItems(listDeliveryDetails);
            ItemStatusDetails firstUpdate = missingItemsList.get(missingListPosition);
            firstUpdate.setUserIdAuthorized(userAuthorizationId);
            firstUpdate.setUserName("ganesh123");
            firstUpdate.setUserId("TPU111");
            firstUpdate.setComponentStatus(LotsInfo.MISSING);
            callUpdateItemStatus(firstUpdate);

            //Utils.showErrorMessage(getActivity(), "Authorization taken.");
        }
    };

    /**
     * @Author Ganesh
     * <p>
     * For Click Listener for childItem in list.
     * @param index position of list
     * @param model Data over list item
     */
    ClickListener childListener = new ClickListener() {
        @Override
        public void onClickItem(int index, Object model) {
            // Lots click listener
            // Get this created from List
            ItemStatusDetails itemUpdateStatus = new ItemStatusDetails();
            itemUpdateStatus.setDeliveryId("13177609");
            itemUpdateStatus.setComponentId("DpobygNy");
            itemUpdateStatus.setComponentNum("1");
            itemUpdateStatus.setComponentStatus("Missing");
            itemUpdateStatus.setProductCode("13177609");
            itemUpdateStatus.setUserId("TPUXXX");
            itemUpdateStatus.setUserIdAuthorized("TPUXXX");
            itemUpdateStatus.setUserName("DD");

            callUpdateItemStatus(itemUpdateStatus);
        }
    };

    /**
     * @Author Ganesh
     * <p>
     * For appbar collapsible event handler
     * @param index position of list
     * @param model Data over menu item
     */
    AppBarStateChangeListener appBarStateChangeListener = new AppBarStateChangeListener() {
        @Override
        public void onStateChanged(AppBarLayout appBarLayout, State state) {
            if (state.name().equalsIgnoreCase(State.COLLAPSED.name())) {
                binding.secondHeader.setVisibility(View.VISIBLE);
            } else if (state.name().equalsIgnoreCase(State.EXPANDED.name())) {
                binding.secondHeader.setVisibility(View.GONE);
            }
        }
    };

}