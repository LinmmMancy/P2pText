package com.mancy.p2ptext.AllAdapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.mancy.p2ptext.ViewHodler.BaseHodler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mancy on 2017/3/14.
 */

public abstract class InvestAllAdapter3<T> extends BaseAdapter {
    private List<T> list = new ArrayList<>();

    public InvestAllAdapter3(List<T> list) {
        this.list.clear();
        this.list.addAll(list);


    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        BaseHodler baseHodler = null;
        if (convertView == null) {

            baseHodler = gethodler();

            //convertView.setTag(viewholder);

        } else {

            baseHodler = (BaseHodler) convertView.getTag();
        }
               T t = list.get(position);

        baseHodler.setData(t);


        return baseHodler.getView();
    }

    protected abstract BaseHodler gethodler();


}
