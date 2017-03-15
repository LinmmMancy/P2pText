package com.mancy.p2ptext.adapter;

import android.view.View;
import android.widget.TextView;

import com.mancy.p2ptext.R;
import com.mancy.p2ptext.UiUtils;
import com.mancy.p2ptext.ViewHodler.BaseHodler;
import com.mancy.p2ptext.bean.InvestAllBean;
import com.mancy.p2ptext.fragment.MyProgress;

import butterknife.InjectView;

/**
 * Created by Mancy on 2017/3/14.
 */
public class InvesHodler extends BaseHodler<InvestAllBean.DataBean> {
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
    @InjectView(R.id.p_progresss)
    MyProgress pProgresss;

    @Override
    protected void setChildData() {
        InvestAllBean.DataBean dataBean = getT();
        pName.setText(dataBean.getName());

    }

    @Override
    public View initView() {
        return UiUtils.getView(R.layout.adapter_invest_all);
    }
}
