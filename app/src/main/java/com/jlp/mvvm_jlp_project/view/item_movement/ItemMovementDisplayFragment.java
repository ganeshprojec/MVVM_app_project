package com.jlp.mvvm_jlp_project.view.item_movement;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.jlp.mvvm_jlp_project.R;

public class ItemMovementDisplayFragment extends Fragment implements View.OnClickListener {

    View rootView;
    ImageView imgClose, imgCloseSecond,imgNextScan;
    private String mParam1;
    private String mParam2;
    AppCompatButton button;
    RecyclerView recyclerViewItemMovement;

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public ItemMovementDisplayFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ItemMovementDisplayFragment newInstance(String param1, String param2) {
        ItemMovementDisplayFragment fragment = new ItemMovementDisplayFragment();
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
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_item_movement_display, container, false);
        imgClose = rootView.findViewById(R.id.imgClose);
        imgNextScan = rootView.findViewById(R.id.img_next_scan);
        imgCloseSecond = rootView.findViewById(R.id.imgCloseSecond);

        return rootView;
    }

    @Override
    public void onClick(View view) {
        Fragment fragment=null;
        switch(view.getId()){
            case R.id.img_next_scan:
                fragment=new ItemMovementLocationBarcodeFragment();
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
}