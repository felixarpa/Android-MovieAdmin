package idi.felixjulen.movieadmin.presentation.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PagerViewAdapter extends FragmentPagerAdapter {

    private Fragment[] fragments;

    public PagerViewAdapter(FragmentManager fm, Fragment... fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        if (position >= 0 && position < fragments.length) {
            return fragments[position];
        }
        return null;
    }

    @Override
    public int getCount() {
        return fragments.length;
    }
}
