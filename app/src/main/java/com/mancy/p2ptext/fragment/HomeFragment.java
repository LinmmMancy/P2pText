package com.mancy.p2ptext.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.mancy.p2ptext.LoadNetHttp;
import com.mancy.p2ptext.R;
import com.mancy.p2ptext.bean.HomeBean;
import com.mancy.p2ptext.utils.AppNetConfig;
import com.mancy.p2ptext.utils.LoadNet;
import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Mancy on 2017/3/10.
 */
public class HomeFragment extends Fragment {
    @InjectView(R.id.base_title)
    TextView baseTitle;
    @InjectView(R.id.base_back)
    ImageView baseBack;
    @InjectView(R.id.base_setting)
    ImageView baseSetting;
    @InjectView(R.id.tv_home_product)
    TextView tvHomeProduct;
    @InjectView(R.id.tv_home_yearrate)
    TextView tvHomeYearrate;
    @InjectView(R.id.banner)
    Banner banner;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_home, null);
        ButterKnife.inject(this, view);
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //初始化数据
        initData();
        //初始化监听
        initListener();
    }

    private void initListener() {
        baseTitle.setText("首页");
        // 隐藏 首页左右两边的图
        baseBack.setVisibility(View.INVISIBLE);
        baseSetting.setVisibility(View.INVISIBLE);
    }

    private void initData() {

        LoadNet.getDataPost(AppNetConfig.INDEX, new LoadNetHttp() {
            @Override
            public void success(String context) {
                Log.i("http", "联网成功: " + context);

                HomeBean homeBean = JSON.parseObject(context, HomeBean.class);

                tvHomeProduct.setText(homeBean.getProInfo().getName());

                tvHomeYearrate.setText(Double.parseDouble(homeBean.getProInfo().getYearRate()) / 100 + "%");

                initBanner(homeBean);


            }


            @Override
            public void failure(String error) {
                Log.i("http", "联网失败: " + error);
            }
        });
    }

    /**
     * private void initBanner(HomeBean homeBean) {
     * //设置图片加载器
     * banner.setImageLoader(new GlideImageLoader());
     * //转化成url集合
     * List<String> urls = new ArrayList<>();
     * for (int i = 0; i < homeBean.getImageArr().size(); i++) {
     * urls.add(AppNetConfig.BASE_URL+homeBean.getImageArr().get(i).getIMAURL());
     * }
     * //设置图片集合
     * banner.setImages(urls);
     * //banner设置方法全部调用完毕时最后调用
     * banner.start();
     * }
     *
     * @param homeBean
     */

    private void initBanner(HomeBean homeBean) {
        //设置图片的请求方式
        banner.setImageLoader(new GlideImageLoader());

        List<String> urls = new ArrayList<>();

        for (int i = 0; i < homeBean.getImageArr().size(); i++) {
            urls.add(AppNetConfig.BASE_URL + homeBean.getImageArr().get(i).getIMAURL());
            Log.e("TAG", "initBanner:循环成功 ");
        }

        //设置图片的集合
        banner.setImages(urls);
        Log.e("TAG", "initBanner: 集合成功");

        //图片的加载完毕后调用

        banner.start();
        Log.e("TAG", "initBanner: 加载完毕");
    }


    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            /**
             注意：
             1.图片加载器由自己选择，这里不限制，只是提供几种使用方法
             2.返回的图片路径为Object类型，由于不能确定你到底使用的那种图片加载器，
             传输的到的是什么格式，那么这种就使用Object接收和返回，你只需要强转成你传输的类型就行，
             切记不要胡乱强转！
             */
            //Picasso 加载图片简单用法
            Picasso.with(context).load((String) path).into(imageView);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

}

