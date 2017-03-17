package com.mancy.p2ptext.fragment;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mancy.p2ptext.R;
import com.mancy.p2ptext.activity.BaseActivity;

import butterknife.InjectView;

public class WithDrawActivity extends BaseActivity {

    @InjectView(R.id.base_title)
    TextView baseTitle;
    @InjectView(R.id.base_back)
    ImageView baseBack;
    @InjectView(R.id.base_setting)
    ImageView baseSetting;
    @InjectView(R.id.account_zhifubao)
    TextView accountZhifubao;
    @InjectView(R.id.select_bank)
    RelativeLayout selectBank;
    @InjectView(R.id.chongzhi_text)
    TextView chongzhiText;
    @InjectView(R.id.view)
    View view;
    @InjectView(R.id.et_input_money)
    EditText etInputMoney;
    @InjectView(R.id.chongzhi_text2)
    TextView chongzhiText2;
    @InjectView(R.id.textView5)
    TextView textView5;
    @InjectView(R.id.btn_tixian)
    Button btnTixian;

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        etInputMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String money = s.toString().trim();

                if (TextUtils.isEmpty(money)) {
                    btnTixian.setClickable(false);
                    btnTixian.setBackgroundResource(R.drawable.btn_02);
                } else {
                    btnTixian.setClickable(true);
                    btnTixian.setBackgroundResource(R.drawable.btn_01);
                }

                btnTixian.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showToast("提现成功拉");
                    }
                });


            }
        });

    }

    @Override
    protected void initTitle() {
        baseTitle.setText("提现");

        baseBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        baseSetting.setVisibility(View.GONE);

    }

    @Override
    public int getLayouid() {
        return R.layout.activity_with_draw;
    }

}
