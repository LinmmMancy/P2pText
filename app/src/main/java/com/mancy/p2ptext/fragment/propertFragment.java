package com.mancy.p2ptext.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mancy.p2ptext.R;
import com.mancy.p2ptext.activity.MainActivity;
import com.mancy.p2ptext.bean.UserInfo;
import com.mancy.p2ptext.utils.AppNetConfig;
import com.mancy.p2ptext.utils.BitmapUtils;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import butterknife.InjectView;

/**
 * Created by Mancy on 2017/3/10.
 */
public class propertFragment extends BaseFragment {

    @InjectView(R.id.tv_settings)
    TextView tvSettings;
    @InjectView(R.id.iv_me_icon)
    ImageView ivMeIcon;
    @InjectView(R.id.rl_me_icon)
    RelativeLayout rlMeIcon;
    @InjectView(R.id.tv_me_name)
    TextView tvMeName;
    @InjectView(R.id.rl_me)
    RelativeLayout rlMe;
    @InjectView(R.id.recharge)
    ImageView recharge;
    @InjectView(R.id.withdraw)
    ImageView withdraw;
    @InjectView(R.id.ll_touzi)
    TextView llTouzi;
    @InjectView(R.id.ll_touzi_zhiguan)
    TextView llTouziZhiguan;
    @InjectView(R.id.ll_zichan)
    TextView llZichan;


    @Override
    protected void initData(String json) {
        initListener();
        MainActivity activity = (MainActivity) getActivity();
        UserInfo user = activity.getUser();

        tvMeName.setText(user.getData().getName());

        //设置
        Picasso.with(getActivity())
                .load(AppNetConfig.BASE_URL + "/images/tx.png")
                .transform(new Transformation() {
                    @Override
                    public Bitmap transform(Bitmap bitmap) {
                        Bitmap circleBitmap = BitmapUtils.circleBitmap(bitmap);
                        bitmap.recycle();

                        return circleBitmap;
                    }

                    @Override
                    public String key() {
                        return "";
                    }
                })
                .into(ivMeIcon);

    }

    @Override
    protected void initListener() {
        llTouzi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), TouziActivity.class));
            }
        });

        llZichan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity(), PieActivity.class));

            }
        });
        llTouziZhiguan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity(), ColumnActivity.class));

            }
        });
        recharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),ReChargeActivity.class));
            }
        });
        withdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),WithDrawActivity.class));
            }
        });

    }


    @Override
    public int getlayoutid() {
        return R.layout.fragment_property;
    }

    @Override
    protected String getChildUrl() {
        return null;
    }


}
