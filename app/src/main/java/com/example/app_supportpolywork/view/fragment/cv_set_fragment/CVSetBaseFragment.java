package com.example.app_supportpolywork.view.fragment.cv_set_fragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.app_supportpolywork.R;

public class CVSetBaseFragment extends Fragment {

    protected void navigate(Class<? extends Fragment> fragmentClass) {
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.setReorderingAllowed(true);

        transaction.setCustomAnimations(
                R.anim.fade_in_in,
                R.anim.fade_in_out,
                R.anim.fade_out_in,
                R.anim.fade_out_out
        );
        transaction.replace(R.id.fragmentContainer, fragmentClass, null);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    protected void popBackStack() {
        getParentFragmentManager().popBackStack();
    }


}
