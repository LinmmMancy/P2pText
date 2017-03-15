package com.mancy.p2ptext.ViewHodler;

import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by Mancy on 2017/3/14.
 */

public abstract class BaseHodler<T> {
    private final View rootView;

    public BaseHodler() {
        rootView = initView();
        rootView.setTag(this);
        ButterKnife.inject(this, rootView);
    }

    public View getView() {
        return rootView;

    }

    private T t;

    public void setData(T t) {

        this.t = t;
        setChildData();
    }

    public T getT() {
        return t;
    }


    protected abstract void setChildData();


    public abstract View initView();
}
