package com.mancy.p2ptext;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mancy.p2ptext.activity.BaseActivity;
import com.mancy.p2ptext.activity.LoginActivity;
import com.mancy.p2ptext.activity.MainActivity;

import butterknife.InjectView;

public class welcomeActivity extends BaseActivity {

    @InjectView(R.id.iv_welcome_icon)
    ImageView ivWelcomeIcon;
    @InjectView(R.id.splash_tv_version)
    TextView splashTvVersion;
    @InjectView(R.id.activity_splash)
    RelativeLayout activitySplash;


    public void initData() {

        // 设置动画

        // 设置版本号
        AppManager.getInstance().addActivity(this);
        setVersion();
        setAnimation();


    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initTitle() {

    }

    @Override
    public int getLayouid() {
        return R.layout.activity_welcome;
    }

    private void setVersion() {
        splashTvVersion.setText(getVersion());

    }

    private String getVersion() {

        //   设置包的管理器

        PackageManager packageManager = getPackageManager();

        // 拿到包的信息
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(getPackageName(), 0);
            // versionCode 每次发布新版本一定要加1

            int versionCode = packageInfo.versionCode;

            // 当前版本号
            String versionName = packageInfo.versionName;

            return versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();

        }


        return "";
    }

    private void setAnimation() {

        AlphaAnimation animation = new AlphaAnimation(0, 1);

        // 动画时长

        animation.setDuration(2000);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // 动画完毕时 执行
                if (isLogin()) {
                    startActivity(new Intent(welcomeActivity.this, MainActivity.class));
                    Log.e("TAG", "onAnimationEnd: 11");
                    finish();
                } else {
                    startActivity(new Intent(welcomeActivity.this, LoginActivity.class));
                    finish();
                }
            }


            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        activitySplash.startAnimation(animation);

    }

    private boolean isLogin() {
        String name = getUser().getData().getName();
        if (TextUtils.isEmpty(name)) {
            return false;
        }

        return true;
    }
}
