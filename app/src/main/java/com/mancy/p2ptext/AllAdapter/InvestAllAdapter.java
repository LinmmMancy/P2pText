package com.mancy.p2ptext.AllAdapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mancy.p2ptext.R;
import com.mancy.p2ptext.bean.InvestAllBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Mancy on 2017/3/14.
 */

public class InvestAllAdapter extends BaseAdapter {
    private List<InvestAllBean.DataBean> list = new ArrayList<>();

    public InvestAllAdapter(List<InvestAllBean.DataBean> list) {
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
            convertView = View.inflate(parent.getContext(), R.layout.adapter_invest_all, null);

            viewholder = new ViewHolder(convertView);

            convertView.setTag(viewholder);

        } else {

            viewholder = (ViewHolder) convertView.getTag();
        }
              InvestAllBean.DataBean dataBean = list.get(position);

        viewholder.pName.setText(dataBean.getName());

        viewholder.pMoney.setText(dataBean.getMoney());

        viewholder.pMinnum.setText(dataBean.getMemberNum());


        return convertView;
    }

    class ViewHolder {
        @InjectView(R.id.p_name)
        TextView pName;
        @InjectView(R.id.p_money)
        TextView pMoney;
        @InjectView(R.id.p_yearlv)
        TextView pYearlv;
        @InjectView(R.id.p_suodingdays)
        TextView pSuodingdays;
        @InjectView(R.id.p_minzouzi)
        TextView pMinzouzi;
        @InjectView(R.id.p_minnum)
        TextView pMinnum;


        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
