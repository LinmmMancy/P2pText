package com.mancy.p2ptext;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mancy.p2ptext.activity.MainActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class welcomeActivity extends AppCompatActivity {

    @InjectView(R.id.iv_welcome_icon)
    ImageView ivWelcomeIcon;
    @InjectView(R.id.splash_tv_version)
    TextView splashTvVersion;
    @InjectView(R.id.activity_splash)
    RelativeLayout activitySplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.inject(this);

        initData();
    }

    private void initData() {
        // 设置动画
        setAnimation();
        // 设置版本号

        setVersion();


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
                startActivity(new Intent(welcomeActivity.this, MainActivity.class));
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        activitySplash.startAnimation(animation);

    }
}
