package com.edgegoodgame.simulationexamexe2;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class SwipeAdapter extends FragmentStatePagerAdapter {
    public SwipeAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {



        if (position == 0){
            //return new BlueFragment();
            return BlueFragment.getInstance();
        }else if (position == 1){
           return new GreenFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
