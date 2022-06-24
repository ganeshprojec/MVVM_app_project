package com.jlp.mvvm_jlp_project.view.itemenquiry;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jlp.mvvm_jlp_project.R;
import com.jlp.mvvm_jlp_project.adapters.ItemEnquiryAdapter;
import com.jlp.mvvm_jlp_project.model.ItemEnquiryModel;

import java.util.ArrayList;
import java.util.List;


public class ItemEnquiryDisplayFragment extends Fragment implements View.OnClickListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    View rootView;
    ImageView imgClose, imgCloseSecond,imgNextScan;
    private String mParam1;
    private String mParam2;
    AppCompatButton button;
    RecyclerView recyclerView;

    public ItemEnquiryDisplayFragment() {

    }

    public static ItemEnquiryDisplayFragment newInstance(String param1, String param2) {
        ItemEnquiryDisplayFragment fragment = new ItemEnquiryDisplayFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_item_enquiry_display, container, false);
        imgClose = rootView.findViewById(R.id.imgClose);
        imgNextScan = rootView.findViewById(R.id.imgnextscan);
        imgCloseSecond = rootView.findViewById(R.id.imgCloseSecond);
        imgNextScan.setOnClickListener(this);
        recyclerView
                = rootView.findViewById(R.id.recyclerView);
        List<ItemEnquiryModel> list = new ArrayList<>();
        list = getData();
        ItemEnquiryAdapter adapter;
        adapter
                = new ItemEnquiryAdapter(
                list, getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(
                new LinearLayoutManager(getContext()));


              return rootView;
    }
    public void onClick(View view){
        Fragment fragment=null;
        switch(view.getId()){
            case R.id.imgnextscan:
                fragment=new ItemEnquiryFragment();
                replaceFragment(fragment);
                break;
        }
    }
    public void replaceFragment(Fragment someFragment){
        FragmentTransaction transaction= getFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container_main,someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    // Sample data for ItemEnquiryRecyclerView
    public List<ItemEnquiryModel> getData()
    {
        List<ItemEnquiryModel> list = new ArrayList<>();
        list.add(new ItemEnquiryModel("Delivery Number",
                "12345"
        ));
        list.add(new ItemEnquiryModel("Route Number",
                "R34567"
        ));
        list.add(new ItemEnquiryModel("Delivery Date",
                "24/7/2022"));
        list.add(new ItemEnquiryModel("Last Recorded Location",
                "JLP building 1"));
        list.add(new ItemEnquiryModel("Time of last move",
                "23/5/2022 12:23"));
        list.add(new ItemEnquiryModel("Last UserId",
                "JL123"));
        list.add(new ItemEnquiryModel("Product Code",
                "23213"));
        list.add(new ItemEnquiryModel("Product Description",
                "It contains papers and books "));
        list.add(new ItemEnquiryModel("Lot Number",
                "2 of 4"));
        list.add(new ItemEnquiryModel("Address",
                "Mr.John,20 London Road MK 46"));

        return list;
    }



    public void addHomeFragment(Fragment fragment) {
        clearBackStack();

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.main_fragment_container, fragment);
        transaction.addToBackStack(getString(R.string.backstack_tag));
        transaction.commit();
    }

    private void clearBackStack() {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        if (manager.getBackStackEntryCount() > 0) {
            FragmentManager.BackStackEntry first = manager.getBackStackEntryAt(0);
            manager.popBackStack(first.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }


    }


}