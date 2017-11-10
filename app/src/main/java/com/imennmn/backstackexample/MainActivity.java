package com.imennmn.backstackexample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
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
}
