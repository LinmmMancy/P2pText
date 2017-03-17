package com.mancy.p2ptext.text;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mancy.p2ptext.LoadNetHttp;
import com.mancy.p2ptext.R;
import com.mancy.p2ptext.activity.BaseActivity;
import com.mancy.p2ptext.activity.MainActivity;
import com.mancy.p2ptext.bean.UserInfo;
import com.mancy.p2ptext.utils.AppNetConfig;
import com.mancy.p2ptext.utils.LoadNet;

import java.util.HashMap;
import java.util.Map;

import butterknife.InjectView;

/**
 * Created by linmingming(林明明) on 2017/3/16.
 * QQ ：519660797
 * Tel: 17600305015
 * 作用：
 */

public class LoginActivity2 extends BaseActivity {
    @InjectView(R.id.base_title)
    TextView baseTitle;
    @InjectView(R.id.base_back)
    ImageView baseBack;
    @InjectView(R.id.base_setting)
    ImageView baseSetting;
    @InjectView(R.id.tv_login_number)
    TextView tvLoginNumber;
    @InjectView(R.id.login_et_number)
    EditText loginEtNumber;
    @InjectView(R.id.rl_login)
    RelativeLayout rlLogin;
    @InjectView(R.id.tv_login_pwd)
    TextView tvLoginPwd;
    @InjectView(R.id.login_et_pwd)
    EditText loginEtPwd;
    @InjectView(R.id.btn_login)
    Button btnLogin;
    @InjectView(R.id.login_regitster_tv)
    TextView loginRegitsterTv;

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loGIn();
            }
        });

    }

    private void loGIn() {
        String phone = tvLoginNumber.getText().toString().trim();
        String paw = tvLoginPwd.getText().toString().trim();


        if (TextUtils.isEmpty(phone)) {
            showToast("账号不能为空");
            return;
        }
        if (TextUtils.isEmpty(paw)) {
            showToast("密码不能为空");
            return;
        }

        Map<String, String> map = new HashMap<>();
        map.put("phone", phone);
        map.put("password", paw);

        LoadNet.getDataPost(AppNetConfig.LOGIN, map, new LoadNetHttp() {
            @Override
            public void success(String context) {
                JSONObject jsonObject = JSON.parseObject(context);
                Boolean success = jsonObject.getBoolean("success");
                if (success) {
                    UserInfo userInfo = JSON.parseObject(context, UserInfo.class);
                    saveUser(userInfo);
                    startActivity(new Intent(LoginActivity2.this, MainActivity.class));
                    finish();
                } else {
                    showToast(" 账号或密码错误");
                }

            }

            @Override
            public void failure(String error) {

            }
        });

    }

    @Override
    protected void initTitle() {

    }

    @Override
    public int getLayouid() {
        return R.layout.activity_login;
    }

}
