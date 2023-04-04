package com.example.app_supportpolywork.view.main_activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
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

        navController.addOnDestinationChangedListener((navControl, navDestination, bundle) -> {
            navDestinationHandler(navDestination);
        });
    }

    private void navDestinationHandler(NavDestination navDestination) {
        int destinationId = navDestination.getId();
        if (destinationId == R.id.homeFragment) {
            mBinding.bottomNavView.getMenu().findItem(R.id.homeFragment).setChecked(true);
        }

        if (destinationId == R.id.jobFragment ||
                destinationId == R.id.jobDetailFragment) {
            mBinding.bottomNavView.getMenu().findItem(R.id.jobFragment).setChecked(true);
        }

        if (destinationId == R.id.cvFragment) {
            mBinding.bottomNavView.getMenu().findItem(R.id.cvFragment).setChecked(true);
        }

        if (destinationId == R.id.profileFragment) {
            mBinding.bottomNavView.getMenu().findItem(R.id.profileFragment).setChecked(true);
        }
    }

    public void closeBottomNav() {
        mBinding.btAppBar.setVisibility(View.GONE);
    }

    public void openBottomNav() {
        mBinding.btAppBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBinding = null;
    }
}