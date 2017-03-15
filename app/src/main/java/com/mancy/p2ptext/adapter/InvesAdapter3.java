package com.mancy.p2ptext.adapter;

import com.mancy.p2ptext.AllAdapter.InvestAllAdapter3;
import com.mancy.p2ptext.ViewHodler.BaseHodler;
import com.mancy.p2ptext.bean.InvestAllBean;

import java.util.List;

/**
 * Created by Mancy on 2017/3/14.
 */

public class InvesAdapter3 extends InvestAllAdapter3<InvestAllBean.DataBean> {


    public InvesAdapter3(List<InvestAllBean.DataBean> list) {
        super(list);
    }

    @Override
    protected BaseHodler gethodler() {
        return new InvesHodler();
    }
}
