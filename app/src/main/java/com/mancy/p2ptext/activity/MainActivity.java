package com.mancy.p2ptext.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.mancy.p2ptext.AppManager;
import com.mancy.p2ptext.R;
import com.mancy.p2ptext.fragment.HomeFragment;
import com.mancy.p2ptext.fragment.InvestFragment;
import com.mancy.p2ptext.fragment.MoreFragment;
import com.mancy.p2ptext.fragment.propertFragment;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.main_fl)
    FrameLayout mainFl;

    @InjectView(R.id.main_rg)
    RadioGroup mainRg;
    @InjectView(R.id.activity_main)
    LinearLayout activityMain;
    private HomeFragment homeFragment;
    private InvestFragment investFragment;
    private MoreFragment moreFragment1;
    private propertFragment propertFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        initData();

        AppManager.getInstance().addActivity(this);

        mainRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switchFragment(checkedId);
            }
        });

    }

    private void initData() {
        switchFragment(R.id.rb_main);

    }

    private void switchFragment(int checkedId) {

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        hideFragment(ft);
        switch (checkedId) {
            case R.id.rb_main:
                if (homeFragment == null) {

                    homeFragment = new HomeFragment();
                    ft.add(R.id.main_fl, homeFragment);
                }
                ft.show(homeFragment);

                break;
            case R.id.rb_invest:
                if (investFragment == null) {

                    investFragment = new InvestFragment();
                    ft.add(R.id.main_fl, investFragment);
                }
                ft.show(investFragment);
                break;
            case R.id.rb_more:
                if (moreFragment1 == null) {

                    moreFragment1 = new MoreFragment();
                    ft.add(R.id.main_fl, moreFragment1);
                }
                ft.show(moreFragment1);
                break;
            case R.id.rb_propert:
                if (propertFragment == null) {

                    propertFragment = new propertFragment();
                    ft.add(R.id.main_fl, propertFragment);
                }
                ft.show(propertFragment);
                break;
        }
        ft.commit();
    }

    private void hideFragment(FragmentTransaction ft) {
        if (homeFragment != null) {
            ft.hide(homeFragment);
        }
        if (investFragment != null) {
            ft.hide(investFragment);
        }
        if (moreFragment1 != null) {
            ft.hide(moreFragment1);
        }
        if (propertFragment != null) {
            ft.hide(propertFragment);
        }
    }


    private boolean isdowns = false;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            if (isdowns) {
                finish();
            }
            Toast.makeText(this, "再点，我就死给你看", Toast.LENGTH_SHORT).show();
            isdowns = true;
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    isdowns = false;
                }
            }, 2000);
            return true;
        }

        return super.onKeyDown(keyCode, event);

    }
}
