package com.example.carson.tryeverything;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Carson on 2017-02-27.
 */

public class SectionsPagerAdapter extends FragmentStatePagerAdapter {
    protected Context mContext;

    public SectionsPagerAdapter( FragmentManager fm) {
        super(fm);

    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        int number = position;
        switch (number) {
            case 0: // Fragment # 0 - This will show FirstFragment
                return TabFragment1.newInstance();
            case 1: // Fragment # 0 - This will show FirstFragment different title
                return TabFragment2.newInstance();
            case 2: // Fragment # 1 - This will show SecondFragment
                return TabFragment3.newInstance();
            default:
                return null;
        }

        // return PlaceholderFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "All";
            case 1:
                return "Unfinished";
            case 2:
                return "Completed";
        }
        return null;
    }
}
