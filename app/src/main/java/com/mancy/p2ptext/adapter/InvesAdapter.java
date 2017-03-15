package com.mancy.p2ptext.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mancy.p2ptext.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mancy on 2017/3/14.
 */

public class InvesAdapter extends FragmentPagerAdapter {

    private List<BaseFragment> fragments = new ArrayList<>();


    public InvesAdapter(FragmentManager fm, List<BaseFragment> fragments) {
        super(fm);
        if (fragments != null && fragments.size() > 0) {
            this.fragments = fragments;

        }
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
