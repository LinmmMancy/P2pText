package com.mancy.p2ptext.fragment;

import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.mancy.p2ptext.R;
import com.mancy.p2ptext.activity.BaseActivity;

import java.util.ArrayList;

import butterknife.InjectView;

public class PieActivity extends BaseActivity {


    @InjectView(R.id.base_title)
    TextView baseTitle;
    @InjectView(R.id.base_back)
    ImageView baseBack;
    @InjectView(R.id.base_setting)
    ImageView baseSetting;
    @InjectView(R.id.chart)
    PieChart chart;
    @InjectView(R.id.activity_pie)
    LinearLayout activityPie;

    @Override
    protected void initData() {


        Typeface mTf = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");

        chart.setDescription("");
        chart.setHoleRadius(52f);
        chart.setTransparentCircleRadius(57f);
        chart.setCenterText("MPChart\nAndroid");
        chart.setCenterTextTypeface(mTf);
        chart.setCenterTextSize(18f);
        chart.setUsePercentValues(true);
        PieData mChartData = generateDataPie(1);
        mChartData.setValueFormatter(new PercentFormatter());
        mChartData.setValueTypeface(mTf);
        mChartData.setValueTextSize(11f);
        mChartData.setValueTextColor(Color.WHITE);
        // set data
        chart.setData((PieData) mChartData);

        Legend l = chart.getLegend();
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);

        // do not forget to refresh the chart
        // chart.invalidate();
        chart.animateXY(900, 900);

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initTitle() {

        baseTitle.setText("饼状图");
        baseBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        baseSetting.setVisibility(View.GONE);

    }

    @Override
    public int getLayouid() {
        return R.layout.activity_pie;
    }


    private PieData generateDataPie(int cnt) {

        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();

        for (int i = 0; i < 4; i++) {
            entries.add(new PieEntry((float) ((Math.random() * 70) + 30), "Quarter " + (i + 1)));
        }

        PieDataSet d = new PieDataSet(entries, "");

        // space between slices
        d.setSliceSpace(2f);
        d.setColors(ColorTemplate.VORDIPLOM_COLORS);

        PieData cd = new PieData(d);
        return cd;
    }

    private ArrayList<String> getQuarters() {

        ArrayList<String> q = new ArrayList<String>();
        q.add("1st Quarter");
        q.add("2nd Quarter");
        q.add("3rd Quarter");
        q.add("4th Quarter");

        return q;
    }

}
