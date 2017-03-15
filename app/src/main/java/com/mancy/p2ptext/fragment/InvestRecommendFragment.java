package com.mancy.p2ptext.fragment;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mancy.p2ptext.R;
import com.mancy.p2ptext.UiUtils;
import com.mancy.p2ptext.utils.randomLayout.StellarMap;

import java.util.Random;

import butterknife.InjectView;

/**
 * Created by Mancy on 2017/3/14.
 */

public class InvestRecommendFragment extends BaseFragment {
    @InjectView(R.id.invest_rec_sm)
    StellarMap investRecSm;
    private String[] datas = new String[]{"新手福利计划", "财神道90天计划", "硅谷钱包计划", "30天理财计划(加息2%)", "180天理财计划(加息5%)", "月月升理财计划(加息10%)",
            "中情局投资商业经营", "大学老师购买车辆", "屌丝下海经商计划", "美人鱼影视拍摄投资", "Android培训老师自己周转", "养猪场扩大经营",
            "旅游公司扩大规模", "铁路局回款计划", "屌丝迎娶白富美计划"
    };
    private String[] onedatas = new String[datas.length / 2];
    private String[] twodatas = new String[datas.length - datas.length / 2];

    private Random random = new Random();

    @Override
    protected void initListener() {

    }

    @Override
    public int getlayoutid() {
        return R.layout.fragment_invest_recomend;
    }

    @Override
    protected String getChildUrl() {
        return null;
    }

    @Override
    protected void initData(String json) {

        for (int i = 0; i < datas.length; i++) {
            if (i < datas.length / 2) {
                onedatas[i] = datas[i];
            } else {
                twodatas[i - datas.length / 2] = datas[i];
            }

            investRecSm.setAdapter(new RecommendAdapter());

            investRecSm.setRegularity(5, 7);
            //设置初始化显示的组别，以及是否需要使用动画
            investRecSm.setGroup(0, true);
            investRecSm.setInnerPadding(UiUtils.dp2px(10), UiUtils.dp2px(10),
                    UiUtils.dp2px(10), UiUtils.dp2px(10));


        }

    }

    class RecommendAdapter implements StellarMap.Adapter {


        @Override
        public int getGroupCount() {
            return 2;
        }

        @Override
        public int getCount(int group) {
            if (group == 0) {
                return datas.length / 2;
            } else {
                return datas.length - datas.length / 2;
            }

        }

        @Override
        public View getView(int group, int position, View convertView) {
            final TextView tv = new TextView(getActivity());

            if (group == 0) {
                tv.setText(onedatas[position]);
            } else {
                tv.setText(twodatas[position]);
            }
            int red = random.nextInt(125);
            int green = random.nextInt(125);
            int blue = random.nextInt(125);

            tv.setTextColor(Color.rgb(red, green, blue));
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(), tv.getText().toString(), Toast.LENGTH_SHORT).show();

                }
            });

            return tv;
        }

        @Override
        public int getNextGroupOnPan(int group, float degree) {
            return 0;
        }

        @Override
        public int getNextGroupOnZoom(int group, boolean isZoomIn) {
            if (group == 0) {

                return 1;
            } else {
                return 0;
            }
        }
    }
}
