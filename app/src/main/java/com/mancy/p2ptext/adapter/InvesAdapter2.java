package com.mancy.p2ptext.adapter;

import android.view.View;
import android.widget.TextView;

import com.mancy.p2ptext.AllAdapter.InvestAllAdapter2;
import com.mancy.p2ptext.R;
import com.mancy.p2ptext.UiUtils;
import com.mancy.p2ptext.bean.InvestAllBean;

import java.util.List;

/**
 * Created by Mancy on 2017/3/14.
 */

public class InvesAdapter2 extends InvestAllAdapter2<InvestAllBean.DataBean> {


    public InvesAdapter2(List<InvestAllBean.DataBean> list) {
        super(list);
    }

    @Override
    protected void setData(InvestAllBean.DataBean dataBean, View view) {
        TextView pname = (TextView) view.findViewById(R.id.p_name);

        pname.setText(dataBean.getName());


    }

    @Override
    public View initView() {
        return UiUtils.getView(R.layout.adapter_invest_all);
    }
}
