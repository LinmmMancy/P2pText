package com.mancy.p2ptext.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;

/**
 * Created by Mancy on 2017/3/13.
 */

public class MyscrollView extends ScrollView {

    private int lastY;
    private View childView;

    public MyscrollView(Context context) {
        super(context);
    }

    public MyscrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private Rect rect = new Rect();

    private boolean isAnimtionEnd = true;

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        if (getChildCount() == 0 || !isAnimtionEnd) {

            return super.onTouchEvent(ev);
        }

        int eventY = (int) ev.getY();

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //  保存 第一次点击的位置

                lastY = eventY;
                break;
            case MotionEvent.ACTION_MOVE:
                if (isNeedMove()) {
                    int dy = eventY - lastY;

                    // 当我们没有记录的时候   需要记录的原来的坐标

                    if (rect.isEmpty()) {
                        rect.set(childView.getLeft(), childView.getTop(),
                                childView.getRight(), childView.getBottom());
                    }
                    // 记录原来的位置

                    childView.layout(
                            childView.getLeft(),
                            childView.getTop() + dy / 2,
                            childView.getRight(),
                            childView.getBottom() + dy / 2
                    );


                }
                lastY = eventY;
                break;
            case MotionEvent.ACTION_UP:
                // 当原来的位置有记录的时候并且动画是结束的时候再执行
                if (!rect.isEmpty() && isAnimtionEnd) {
                    //获取原来的高度和现在拉动位置的差

                    int translateY = childView.getBottom() - rect.bottom;

                    TranslateAnimation animation = new TranslateAnimation(0, 0, 0, -translateY);

                    animation.setDuration(200);

                    animation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            isAnimtionEnd = false;
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            //清楚动画
                            childView.clearAnimation();

                            //回到原来记录的位置
                            childView.layout(rect.left, rect.top,
                                    rect.right, rect.bottom);
                            // 把原来记录的位置清除掉

                            rect.setEmpty();
                            isAnimtionEnd = true;
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                    childView.startAnimation(animation);
                }
                break;
        }

        return super.onTouchEvent(ev);
    }

    /*
    * 拦截事件
    * 返回true 拦截
    * 返回false 不拦截
    *
    * */

    private int downy, lastx, downx;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean isOnIntercept = false;
        int eventx = (int) ev.getX();
        int eventy = (int) ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastY = downy = eventy;
                lastx = downx = eventx;
                break;
            case MotionEvent.ACTION_MOVE:
                int absx = Math.abs(eventx - downx);
                int absy = Math.abs(eventy - downy);
                if (absy > absx && absy >= 20) {
                    isOnIntercept = true;
                }
                lastY = eventy;
                lastx = eventx;
                break;
        }
        return isOnIntercept;
    }


    private boolean isNeedMove() {
        // 获取 scrollView的高度

        int scrollView = this.getMeasuredHeight();
        int childHeight = childView.getMeasuredHeight();

        int dy = childHeight - scrollView;
        // 拿到滑动的距离  往下滑动是变小  往上滑是变大

        int scrollY = getScrollY();

        if (scrollY <= 0 || scrollY >= dy) {
            return true;
        }
        return false;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        //　这个方法是在布局加载完成后使用
        // 判断
        if (getChildCount() > 0) {
            childView = getChildAt(0);
        }
    }
}
