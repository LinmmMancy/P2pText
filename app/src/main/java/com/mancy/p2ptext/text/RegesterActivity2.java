package com.mancy.p2ptext.text;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mancy.p2ptext.LoadNetHttp;
import com.mancy.p2ptext.R;
import com.mancy.p2ptext.activity.BaseActivity;
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

public class RegesterActivity2 extends BaseActivity {
    @InjectView(R.id.base_title)
    TextView baseTitle;
    @InjectView(R.id.base_back)
    ImageView baseBack;
    @InjectView(R.id.base_setting)
    ImageView baseSetting;
    @InjectView(R.id.et_register_number)
    EditText etRegisterNumber;
    @InjectView(R.id.et_register_name)
    EditText etRegisterName;
    @InjectView(R.id.et_register_pwd)
    EditText etRegisterPwd;
    @InjectView(R.id.et_register_pwdagain)
    EditText etRegisterPwdagain;
    @InjectView(R.id.btn_register)
    Button btnRegister;

    @Override
    protected void initData() {



    }

    @Override
    protected void initListener() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //获取四个参数
                String name = etRegisterName.getText().toString().trim();
                String number = etRegisterNumber.getText().toString().trim();
                String paw = etRegisterPwd.getText().toString().trim();
                String prwagin = etRegisterPwdagain.getText().toString().trim();

                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(number)
                        || TextUtils.isEmpty(paw) || TextUtils.isEmpty(prwagin)) {
                    showToast("都不能为空");

                    return;

                }

                Map<String, String> map = new HashMap<String, String>();

                map.put("name", name);
                map.put("number", number);
                map.put("password", paw);


                LoadNet.getDataPost(AppNetConfig.REGISTER, map, new LoadNetHttp() {
                    @Override
                    public void success(String context) {
                        //解析数据
                        JSONObject jsonObject = JSON.parseObject(context);
                        Boolean isExist = jsonObject.getBoolean("isExist");
                        if (isExist) {
                            showToast("已经注册过");
                            return;

                        }else {
                            showToast("注册成功");
                        }


                    }

                    @Override
                    public void failure(String error) {

                    }
                });

            }
        });

    }

    @Override
    protected void initTitle() {
        baseTitle.setText("11");


    }

    @Override
    public int getLayouid() {
        return R.layout.activity_regester;
    }

}
