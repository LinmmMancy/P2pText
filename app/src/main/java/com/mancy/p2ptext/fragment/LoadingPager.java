package com.mancy.p2ptext.fragment;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.mancy.p2ptext.R;
import com.mancy.p2ptext.UiUtils;

/**
 * Created by Mancy on 2017/3/14.
 */

public abstract class LoadingPager extends FrameLayout {
    private Context context;
    private View loadingView;
    private View errorView;
    private View emptyView;
    private View sucessView;
    private LayoutParams params;

    private ResultState resultSTate;

    public LoadingPager(Context context) {
        super(context);
        this.context = context;

        init();
    }

    public LoadingPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();

    }

    private int STATE_LOADING = 1; // 加载中
    private int STATE_ERROR = 2;// 加载失败
    private int STATE_SUCCESS = 3;//加載成功
    private int STATE_EMPTY = 4;  // 空


    private int current_state = STATE_LOADING;

    private void init() {
        // 设置全屏的属性
        params = new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT);
        //加载布局

        if (loadingView == null) {
            loadingView = View.inflate(context, R.layout.page_loading, null);
            this.addView(loadingView, params);
        }

        if (errorView == null) {
            errorView = View.inflate(context, R.layout.page_error, null);
            this.addView(errorView, params);
        }

        if (emptyView == null) {
            emptyView = View.inflate(context, R.layout.page_empty, null);
            this.addView(emptyView, params);
        }

        // 展示布局

        showSafeView();
    }

    private void showSafeView() {

        //保证在主线程中运行

        UiUtils.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                showView();

            }


        });


    }

    private void showView() {
        errorView.setVisibility(
                current_state == STATE_ERROR ? View.VISIBLE : View.INVISIBLE);
        loadingView.setVisibility(
                current_state == STATE_LOADING ? View.VISIBLE : View.INVISIBLE);
        emptyView.setVisibility(
                current_state == STATE_EMPTY ? View.VISIBLE : View.INVISIBLE);

        if (sucessView == null) {
            sucessView = View.inflate(context, getviewId(), null);
            this.addView(sucessView, params);
        }
        sucessView.setVisibility(
                current_state == STATE_SUCCESS ? View.VISIBLE : View.INVISIBLE);
    }

    protected abstract int getviewId();

    /***
     * 根据不同的网络状态加载相应的页面
     *
     * @return
     */
    public void loadData() {
        //加载网络
        AsyncHttpClient httpClient = new AsyncHttpClient();


        String url = getUrl();

        if (TextUtils.isEmpty(url)) {
            resultSTate = ResultState.SUCCESS;

            loadImage();
            return;
        }

        httpClient.post(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(String content) {
                super.onSuccess(content);
                if (TextUtils.isEmpty(content)) {
                    resultSTate = ResultState.EMPTY;
                    resultSTate.setJson("");

                } else {
                    resultSTate = ResultState.SUCCESS;
                    resultSTate.setJson(content);

                }
                loadImage();
            }

            @Override
            public void onFailure(Throwable error, String content) {
                super.onFailure(error, content);
                resultSTate = ResultState.ERROR;
                resultSTate.setJson(content);
                loadImage();
            }
        });
    }

    private void loadImage() {
        switch (resultSTate) {
            case ERROR:
                current_state = STATE_ERROR;
                break;
            case EMPTY:
                current_state = STATE_EMPTY;
                break;
            case SUCCESS:
                current_state = STATE_SUCCESS;
                break;

        }
        showSafeView();
        if (current_state == STATE_SUCCESS) {

            onSuccess(resultSTate, sucessView);
        }

    }

    protected abstract void onSuccess(ResultState resultSTate, View sucessView);


    public enum ResultState {
        ERROR(2), SUCCESS(3), EMPTY(4);
        private int state;

        ResultState(int state) {
            this.state = state;
        }

        private String json;

        public void setJson(String json) {
            this.json = json;
        }

        public String getJson() {
            return json;
        }
    }

    protected abstract String getUrl();


}
