package com.mancy.p2ptext.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by Mancy on 2017/3/13.
 */

public abstract class BaseFragment extends Fragment {

    private LoadingPager loadingPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        loadingPager = new LoadingPager(getActivity()) {

            @Override
            protected void onSuccess(ResultState resultSTate, View sucessView) {
                ButterKnife.inject(BaseFragment.this, sucessView);
                initData(resultSTate.getJson());
            }

            @Override
            protected String getUrl() {
                return getChildUrl();
            }

            @Override
            protected int getviewId() {
                return getlayoutid();
            }
        };
        return loadingPager;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        initListener();
//
//        initData();
        loadingPager.loadData();

    }

    protected abstract void initListener();


    public abstract int getlayoutid();


    protected abstract String getChildUrl();


    protected abstract void initData(String json);


    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.reset(this);
    }
}
