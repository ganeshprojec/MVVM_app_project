package com.jlp.mvvm_jlp_project.view.home;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.google.android.material.navigation.NavigationView;
import com.jlp.mvvm_jlp_project.adapters.MenuAdapter;
import com.jlp.mvvm_jlp_project.databinding.ActivityHomeMainBinding;
import com.jlp.mvvm_jlp_project.interfaces.ClickListener;
import com.jlp.mvvm_jlp_project.model.DrawerMenuItem;
import com.jlp.mvvm_jlp_project.R;
import com.jlp.mvvm_jlp_project.utils.SpacesItemDecoration;
import com.jlp.mvvm_jlp_project.view.auth.AuthActivity;
import com.jlp.mvvm_jlp_project.view.auth.ChangePasswordFragment;
import com.jlp.mvvm_jlp_project.view.itemenquiry.ItemEnquiryFragment;
import com.jlp.mvvm_jlp_project.viewmodel.MenuViewModel;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MenuActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener, ClickListener {

    protected final String TAG = getClass().getSimpleName();
    private @NonNull
    ActivityHomeMainBinding binding;
    private MenuViewModel menuViewModel;
    private ProgressDialog mDialog;
    private MenuAdapter adapter;
    private ArrayList<DrawerMenuItem> menuList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        menuViewModel = new ViewModelProvider(this).get(MenuViewModel.class);

        initView();
        initEvents();
    }

    private void initView() {
        binding.homeLayout.homeTopheader.imgClose.setImageResource(R.drawable.ic_logout_24);
        binding.homeLayout.homeTopheader.imgCloseSecond.setVisibility(View.VISIBLE);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void initEvents() {
        binding.homeLayout.homeTopheader.imgCloseSecond.setOnClickListener(this);
        binding.homeLayout.homeTopheader.imgClose.setOnClickListener(this);
        menuList = getMenuList();
        adapter = new MenuAdapter(menuList, this, this);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);

        binding.homeLayout.homeContainer.recyHomeMenu.setLayoutManager(layoutManager);
        binding.homeLayout.homeContainer.recyHomeMenu.setAdapter(adapter);

        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.spacing);
        binding.homeLayout.homeContainer.recyHomeMenu.addItemDecoration(new SpacesItemDecoration(spacingInPixels));
    }


    public void showErrorMessage(String message) {
        if (message == null || message.isEmpty()) return;
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    public String getXmlString(int strResId) {
        return getString(strResId);
    }


    public void navigateToHome() {
        /*Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);*/

        finish();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.imgClose:

                onExitApp();
                break;

            case R.id.imgCloseSecond:

                changePassword();
                break;

        }

    }


    public void onExitApp() {
        //navigateToHome();
        //logout from Session Management
        //showErrorMessage("Clicked");
        Intent intent = new Intent(this, AuthActivity.class);
        startActivity(intent);

        finish();
    }


    public void changePassword() {
        addFragment(new ChangePasswordFragment());
    }


    public void onClickClose(View view) {
        // Clear all fragments
        clearBackStack();
    }


    public void onCloseSecond(View view) {
        showErrorMessage(getString(R.string.str_debug_error));
    }


    public void onOptionMenu(View view) {
        // Sidebar open
        binding.drawerLayout.openDrawer(Gravity.LEFT);
    }


    public void addFragment(Fragment fragment) {
        clearBackStack();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.frame_container_main, fragment); //main_fragment_container
        transaction.addToBackStack(getString(R.string.backstack_tag));
        transaction.commit();
    }


    public void clearBackStack() {
        FragmentManager manager = getSupportFragmentManager();
        if (manager.getBackStackEntryCount() > 0) {
            FragmentManager.BackStackEntry first = manager.getBackStackEntryAt(0);
            manager.popBackStack(first.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }


    @Override
    public void onClickItem(int index, Object model) {
        DrawerMenuItem item = (DrawerMenuItem) model;
        //showErrorMessage("" + item.getTitle() + " " + index);

        if (getString(R.string.str_item_enquiry).equalsIgnoreCase(item.getTitle())) {
            onPressItemEnquiry();
        } else if (getString(R.string.str_item_movements).equalsIgnoreCase(item.getTitle())) {
            onPressItemMovements();
        } else if (getString(R.string.str_movements).equalsIgnoreCase(item.getTitle())) {
            onPressMovements();
        } else if (getString(R.string.str_reprint_labels).equalsIgnoreCase(item.getTitle())) {
            onPressReprintLabels();
        } else if (getString(R.string.str_amend_lots).equalsIgnoreCase(item.getTitle())) {
            onPressAmendLots();
        } else if (getString(R.string.str_route_management).equalsIgnoreCase(item.getTitle())) {
            onPressRouteManagement();
        } else if (getString(R.string.str_track_handover_delivery).equalsIgnoreCase(item.getTitle())) {
            onPressTrackDelivery();
        } else if (getString(R.string.str_handover_delivery).equalsIgnoreCase(item.getTitle())) {
            onPressDeliveryDetails();
        } else {

        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_item_enquiry) {
            onPressItemEnquiry();
        } else if (id == R.id.nav_item_movements) {
            onPressItemMovements();
        } else if (id == R.id.nav_movements) {
            onPressMovements();
        } else if (id == R.id.nav_reprint_labels) {
            onPressReprintLabels();

        } else if (id == R.id.nav_amend_lots) {
            onPressAmendLots();
        } else if (id == R.id.nav_route_management) {
            onPressRouteManagement();
        } else if (id == R.id.nav_track_handover_delivery) {
            onPressTrackDelivery();
        } else if (id == R.id.nav_handover_delivery_details) {
            onPressDeliveryDetails();
        }

        DrawerLayout drawer = (DrawerLayout) binding.drawerLayout;
        drawer.closeDrawer(Gravity.LEFT);
        return true;
    }


    // Add in navigation also - menu list item
    public ArrayList<DrawerMenuItem> getMenuList() {
        ArrayList<DrawerMenuItem> list = new ArrayList<DrawerMenuItem>();
        list.add(new DrawerMenuItem(getXmlString(R.string.str_item_enquiry),R.drawable.ic_1_item_enquiry));
        list.add(new DrawerMenuItem(getXmlString(R.string.str_item_movements), R.drawable.ic_2_item_movements));
        list.add(new DrawerMenuItem(getXmlString(R.string.str_movements), R.drawable.ic_3_movements));
        list.add(new DrawerMenuItem(getXmlString(R.string.str_reprint_labels), R.drawable.ic_4_reprint_labels));
        list.add(new DrawerMenuItem(getXmlString(R.string.str_amend_lots), R.drawable.ic_5_amend_lots));
        list.add(new DrawerMenuItem(getXmlString(R.string.str_route_management), R.drawable.ic_6_route_management));
        list.add(new DrawerMenuItem(getXmlString(R.string.str_track_handover_delivery), R.drawable.ic_7_carier_collection_details));
        list.add(new DrawerMenuItem(getXmlString(R.string.str_handover_delivery), R.drawable.ic_8_carrier_handover_details));

        return list;
    }


    public void onPressItemEnquiry() {
        addFragment(new ItemEnquiryFragment());
    }

    public void onPressItemMovements() {
        // Add Fragment
        addFragment(new MainFragment());
    }

    public void onPressMovements() {
        // Add Fragment
        addFragment(new MainFragment());
    }

    public void onPressReprintLabels() {
        // Add Fragment
        addFragment(new MainFragment());
    }

    public void onPressAmendLots() {
        // Add Fragment
        addFragment(new MainFragment());
    }

    public void onPressRouteManagement() {
        // Add Fragment
        addFragment(new MainFragment());

    }

    public void onPressTrackDelivery() {
        // Add Fragment
        addFragment(new MainFragment());
    }

    public void onPressDeliveryDetails() {
        // Add Fragment
        addFragment(new MainFragment());

    }


}
