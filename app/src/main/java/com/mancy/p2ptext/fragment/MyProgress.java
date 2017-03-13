package com.mancy.p2ptext.fragment;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.mancy.p2ptext.R;

/**
 * Created by Mancy on 2017/3/13.
 */

public class MyProgress extends View {

    private Paint paint;
    private TypedArray typedArray;
    private int roundColor;   //  圆环的颜色
    private int sweepColor = Color.RED;
    private int sweepAre = 60;
    private int measuredHeight;
    private int measuredWidth;


    private int roundWidth = 10;
    private int progress;

    public MyProgress(Context context) {
        super(context);
        init();
    }


    public MyProgress(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();

        //自定义属性
        typedArray = context.obtainStyledAttributes(attrs, R.styleable.progress);

        //第一个参数获取attrs里面的配置属性名
        roundColor = typedArray.getColor(R.styleable.progress_roundColor, Color.GRAY);

        sweepColor = typedArray.getColor(R.styleable.progress_sweepColor, Color.RED);

        sweepAre = typedArray.getInteger(R.styleable.progress_sweepArc, 0);

        typedArray.recycle();


    }

    private void init() {
        //画笔
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //前两个 参数是圆心的坐标
        //宽
        int cx = measuredWidth / 2;
        // 高
        int cy = measuredHeight / 2;
        // 设置控件的宽度 - 圆环的宽度的一半

        int radius = cx - roundWidth / 2;
        // 设置圆环的颜色
        paint.setColor(roundColor);
        //设置的圆环的宽度
        paint.setStrokeWidth(roundWidth);
        canvas.drawCircle(cx, cy, radius, paint);

        // 画弧

        //设置圆环中间矩形的左上顶点和右下顶点
/**
 *
 *   //存放了圆环中间矩形的左上顶点和右下顶点
 RectF rectf = new RectF(roundWidth / 2, roundWidth / 2,
 measuredWidth - roundWidth / 2, measuredHeight - roundWidth / 2);
 */
        RectF rectf = new RectF(roundWidth / 2, roundWidth / 2,
                measuredWidth - roundWidth / 2, measuredHeight - roundWidth / 2);
        paint.setColor(sweepColor);

        //第二个参数是起始角 第三个参数多少度
        canvas.drawArc(rectf, 0, sweepAre * 360 / 100, false, paint);

        //画文字
        String text = sweepAre + "%";
        Rect rect = new Rect();

        // 第一个参数是文本   第二个参数是文字的截取长度，第三个是放测量的容器

        paint.setColor(Color.BLUE);

        paint.setStrokeWidth(0);

        paint.setTextSize(30);

        paint.getTextBounds(text, 0, text.length(), rect);

        float tx = measuredWidth / 2 - rect.width() / 2;

        float ty = measuredHeight / 2 + rect.height() / 2;

        canvas.drawText(text, tx, ty, paint);


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measuredHeight = getMeasuredHeight(); // 控件的高
        measuredWidth = getMeasuredWidth();// 控件的宽
    }


    public void setprogress(int progress) {
        this.progress = progress;
    }
}
