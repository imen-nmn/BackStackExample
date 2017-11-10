package com.imennmn.backstackexample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements OnFragmentInteractionListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        replaceFragment(new AFragment(), false);
    }

    public void replaceFragment(Fragment fragment, boolean withBackStack) {
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        String fragmentTag = fragment.getClass().getName();
        fragmentTransaction.replace(R.id.frame, fragment, fragmentTag);
        if (withBackStack)
            fragmentTransaction.addToBackStack(fragmentTag);
        try {
            fragmentTransaction.commitAllowingStateLoss();
        } catch (IllegalStateException ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public void onBackPressed() {
        Fragment currentFragment= getSupportFragmentManager().findFragmentById(R.id.frame);
        super.onBackPressed();

        if(currentFragment!=null && currentFragment.isVisible()) {
            getSupportFragmentManager().beginTransaction().remove(currentFragment).commit();
            getSupportFragmentManager().popBackStack(currentFragment.getTag(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }

//    @Override
//    public void onBackPressed() {
//        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
//            Fragment fragment = getSupportFragmentManager().findFragmentByTag(mFragment.getClass().getName());
//            if (fragment != null && fragment.getClass().getName().equalsIgnoreCase(CFragment.class.getName())) {
//                getSupportFragmentManager().beginTransaction().remove(mFragment).commit();
//            }
//            getSupportFragmentManager().popBackStackImmediate();
//        } else {
//            super.onBackPressed();
//        }
//    }
}
