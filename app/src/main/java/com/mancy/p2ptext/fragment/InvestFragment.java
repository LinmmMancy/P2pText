package com.mancy.p2ptext.fragment;

import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mancy.p2ptext.R;
import com.mancy.p2ptext.adapter.InvesAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

/**
 * Created by Mancy on 2017/3/10.
 */
public class InvestFragment extends BaseFragment {


    @InjectView(R.id.base_title)
    TextView baseTitle;
    @InjectView(R.id.base_back)
    ImageView baseBack;
    @InjectView(R.id.base_setting)
    ImageView baseSetting;
    @InjectView(R.id.tv_invest_all)
    TextView tvInvestAll;
    @InjectView(R.id.tv_invest_recommend)
    TextView tvInvestRecommend;
    @InjectView(R.id.tv_invest_hot)
    TextView tvInvestHot;
    @InjectView(R.id.invest_vp)
    ViewPager investVp;

    @Override
    protected void initData(String json) {
        //　设置标题
        initTitle();

        //初始化 Fragment
        initFragment();

        // 初始化 viewPager

        initViewPager();

        initListener();

        initTab();

    }

    private void initTab() {
        selectText(0);
    }

    @Override

    protected void initListener() {
        investVp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                selectText(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        tvInvestHot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                investVp.setCurrentItem(2);
            }
        });
        tvInvestAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                investVp.setCurrentItem(0);
            }
        });
        tvInvestRecommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                investVp.setCurrentItem(1);
            }
        });

    }

    private void selectText(int id) {
        hiddenTextViewState();
        switch (id) {
            case 0:
                tvInvestAll.setBackgroundColor(Color.BLUE);
                break;
            case 1:
                tvInvestRecommend.setBackgroundColor(Color.BLUE);
                break;
            case 2:
                tvInvestHot.setBackgroundColor(Color.BLUE);
                break;
        }

    }

    private void hiddenTextViewState() {
        tvInvestRecommend.setBackgroundColor(Color.WHITE);
        tvInvestHot.setBackgroundColor(Color.WHITE);
        tvInvestAll.setBackgroundColor(Color.WHITE);
    }


    @Override
    protected String getChildUrl() {
        return null;
    }


    private void initViewPager() {

        investVp.setAdapter(new InvesAdapter(getChildFragmentManager(), fragments));
    }

    private List<BaseFragment> fragments = new ArrayList<>();

    private void initFragment() {

        fragments.add(new InvestAllFragment());
        fragments.add(new InvestRecommendFragment());
        fragments.add(new InvestHotFragment());


    }

    private void initTitle() {
        baseTitle.setText("投资");
        baseBack.setVisibility(View.INVISIBLE);
        baseSetting.setVisibility(View.INVISIBLE);

    }

    @Override
    public int getlayoutid() {
        return R.layout.fragment_invest;
    }


}
