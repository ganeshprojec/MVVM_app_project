package com.jlp.mvvm_jlp_project.view.home;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.google.android.material.navigation.NavigationView;
import com.jlp.mvvm_jlp_project.R;
import com.jlp.mvvm_jlp_project.adapters.MenuAdapter;
import com.jlp.mvvm_jlp_project.databinding.ActivityHomeMainBinding;
import com.jlp.mvvm_jlp_project.interfaces.ClickListener;
import com.jlp.mvvm_jlp_project.model.DrawerMenuItem;
import com.jlp.mvvm_jlp_project.pref.AppPreferencesHelper;
import com.jlp.mvvm_jlp_project.utils.AppConstants;
import com.jlp.mvvm_jlp_project.utils.Helper;
import com.jlp.mvvm_jlp_project.utils.SpacesItemDecoration;
import com.jlp.mvvm_jlp_project.view.auth.ChangePasswordFragment;
import com.jlp.mvvm_jlp_project.view.auth.LoginFragment;
import com.jlp.mvvm_jlp_project.view.base.BaseActivity;
import com.jlp.mvvm_jlp_project.viewmodel.HomeViewModel;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, ClickListener {

    protected final String TAG = getClass().getSimpleName();
    private @NonNull
    ActivityHomeMainBinding binding;
    private HomeViewModel menuViewModel;
    private ProgressDialog mDialog;
    private MenuAdapter adapter;
    private ArrayList<DrawerMenuItem> menuList = new ArrayList<>();

    @Inject
    AppPreferencesHelper appPreferencesHelper;

    @Override
    protected void initViewBinding() {
        binding = ActivityHomeMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            ((TextView) binding.navView.getHeaderView(0).findViewById(R.id.txtHeaderUsername)).setText(appPreferencesHelper.getUsername());
        } catch (Exception ex) {
            Log.e(TAG, "Exception: " + ex);
        }
        menuViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        initEvents();
    }


    /**
     * @param
     * @author Ganesh
     * <p>
     * init events or listeners, initialization of variables
     */
    private void initEvents() {
        binding.homeLayout.homeTopHeader.imgCloseSecond.setVisibility(View.VISIBLE);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) binding.homeLayout.homeTopHeader.imgCloseSecond.getLayoutParams();
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        binding.homeLayout.homeTopHeader.imgCloseSecond.setLayoutParams(params);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        menuList = menuViewModel.getMenuList();
        binding.homeLayout.homeTopHeader.imgClose.setVisibility(View.GONE);
        menuList = menuViewModel.getMenuList();
        adapter = new MenuAdapter(menuList, this, this);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);

        binding.homeLayout.homeContainer.recyHomeMenu.setLayoutManager(layoutManager);
        binding.homeLayout.homeContainer.recyHomeMenu.setAdapter(adapter);

        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.spacing);
        binding.homeLayout.homeContainer.recyHomeMenu.addItemDecoration(new SpacesItemDecoration(spacingInPixels));
    }

    /**
     * @param view
     * @author Ganesh
     * <p>
     * For clearing backstack & come back to home page
     */
    public void onClickClose(View view) {
        // Clear all fragments -default
        Helper.clearBackStack(this);
    }


    /**
     * @param view
     * @author Ganesh
     * <p>
     * For customizable click events if it is visible, Developer needs to handle exact working on respective page
     */
    public void onCloseSecond(View view) {
        Helper.addFragment(this, new LoginFragment());
    }

    /**
     * @param view
     * @author Ganesh
     * <p>
     * For clicking on options menu option, open drawer
     */
    public void onOptionMenu(View view) {
        // Sidebar open
        binding.drawerLayout.openDrawer(Gravity.LEFT);
    }


    /**
     * @param index position of list
     * @param model Data over menu item
     * @author Ganesh
     * <p>
     * For clicking & handling individual item from MENU TYLES
     */
    @Override
    public void onClickItem(int index, Object model) {
        DrawerMenuItem item = (DrawerMenuItem) model;
        menuViewModel.loadListItem(item, this);
    }

    /**
     * @param item position of list
     * @author Ganesh
     * <p>
     * For clicking & handling individual item from MENU Drawer
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        menuViewModel.loadMenu(id, this);
        DrawerLayout drawer = (DrawerLayout) binding.drawerLayout;
        drawer.closeDrawer(Gravity.LEFT);
        return true;
    }


}
