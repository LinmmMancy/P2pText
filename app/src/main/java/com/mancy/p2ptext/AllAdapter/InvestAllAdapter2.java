package com.mancy.p2ptext.AllAdapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mancy on 2017/3/14.
 */

public abstract class InvestAllAdapter2<T> extends BaseAdapter {
    private List<T> list = new ArrayList<>();

    public InvestAllAdapter2(List<T> list) {
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

        ViewHolder viewholder = null;

        if (convertView == null) {
            convertView = initView();

            viewholder = new ViewHolder(convertView);

            //convertView.setTag(viewholder);

        } else {

            viewholder = (ViewHolder) convertView.getTag();
        }
        T t = list.get(position);

        setData(t, convertView);


        return convertView;
    }

    protected abstract void setData(T t, View view);

    public abstract View initView();

    class ViewHolder {


        ViewHolder(View view) {
            view.setTag(this);
        }
    }
}
