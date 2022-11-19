package com.example.app_supportpolywork.view.main_activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.app_supportpolywork.R;
import com.example.app_supportpolywork.databinding.ActivityMainBinding;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        setupBtNavigationWithNavController();
    }

    private void setupBtNavigationWithNavController() {
        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.navHostFragment);
        NavController navController = Objects.requireNonNull(navHostFragment).getNavController();
        NavigationUI.setupWithNavController(mBinding.bottomNavView, navController);

        navController.addOnDestinationChangedListener((navControl, navDestination, bundle) ->
                mBinding.bottomNavView.getMenu().findItem(navDestination.getId()).setChecked(true)
        );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBinding = null;
    }
}