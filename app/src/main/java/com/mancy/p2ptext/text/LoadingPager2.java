package com.mancy.p2ptext.text;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.mancy.p2ptext.R;
import com.mancy.p2ptext.UiUtils;
import com.mancy.p2ptext.fragment.LoadingPager;

/**
 * Created by linmingming(林明明) on 2017/3/16.
 * QQ ：519660797
 * Tel: 17600305015
 * 作用：
 */

public abstract class LoadingPager2 extends FrameLayout {

    private Context context;
    private View loadingView;
    private View successView;
    private View errorView;
    private View emptyView;

    private LayoutParams params;

    private LoadingPager.ResultState resultSTate;

    public LoadingPager2(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public LoadingPager2(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    private int STATE_LOADING = 1;
    private int STATE_ERROR = 1;
    private int STATE_SUCCESS = 1;
    private int STATE_EMPTY = 1;

    private int current_state = STATE_LOADING;

    private void init() {
        params = new LayoutParams(LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        //加载布局
        if (errorView == null) {
            errorView = View.inflate(context, R.layout.page_error, null);
            this.addView(emptyView, params);
        }
        if (loadingView == null) {
            loadingView = View.inflate(context, R.layout.page_loading, null);
            this.addView(loadingView, params);
        }
        if (emptyView == null) {
            emptyView = View.inflate(context, R.layout.page_empty, null);
            this.addView(emptyView, params);
        }
        showSaveView();


    }
    //需要在分线程中运行

    private void showSaveView() {
        UiUtils.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //展示布局
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
        if (successView == null) {
            successView = View.inflate(context, getlayoutId(), null);
            this.addView(successView, params);

        }
        successView.setVisibility(
                current_state == STATE_SUCCESS ? View.VISIBLE : View.INVISIBLE);
    }

    protected abstract int getlayoutId();

    public void loadData() {

        AsyncHttpClient httpClient = new AsyncHttpClient();


        String url = getUrl();

        if (TextUtils.isEmpty(url)) {
            resultSTate = LoadingPager.ResultState.SUCCESS;
            loadimage();
            return;

        }
        httpClient.post(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(String content) {
                super.onSuccess(content);
                if (TextUtils.isEmpty(content)) {
                    resultSTate = LoadingPager.ResultState.EMPTY;
                    resultSTate.setJson("");
                } else {
                    if (TextUtils.isEmpty(content)) {
                        resultSTate = LoadingPager.ResultState.SUCCESS;
                        resultSTate.setJson(content);
                    }
                    loadimage();
                }

            }

            @Override
            public void onFailure(Throwable error, String content) {
                super.onFailure(error, content);
                if (TextUtils.isEmpty(content)) {
                    resultSTate = LoadingPager.ResultState.ERROR;
                    resultSTate.setJson(content);
                    loadimage();
                }
            }
        });
    }

    private void loadimage() {
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
        showSaveView();
        if (current_state == STATE_SUCCESS) {
            onsuccess(current_state, successView);
        }

    }

    protected abstract void onsuccess(int current_state, View successView);

    public enum ResultStates {
        ERROR(2), SUCCESS(3), EMPTY(4),;
        private int state;

        ResultStates(int state) {
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

