package com.mancy.p2ptext.fragment;

import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.mancy.p2ptext.R;
import com.mancy.p2ptext.adapter.InvesAdapter3;
import com.mancy.p2ptext.bean.InvestAllBean;
import com.mancy.p2ptext.utils.AppNetConfig;

import butterknife.InjectView;

/**
 * Created by Mancy on 2017/3/14.
 */

public class InvestAllFragment extends BaseFragment {
    @InjectView(R.id.invest_all_lv)
    ListView investAllLv;

    @Override
    protected void initListener() {

    }

    @Override
    public int getlayoutid() {
        return R.layout.fragment_invest_all;
    }

    @Override
    protected String getChildUrl() {
        return AppNetConfig.PRODUCT;
    }

    @Override
    protected void initData(String json) {
        InvestAllBean investAllBean = JSON.parseObject(json, InvestAllBean.class);

        InvesAdapter3 adapter = new InvesAdapter3(investAllBean.getData());

        investAllLv.setAdapter(adapter);


    }

}
