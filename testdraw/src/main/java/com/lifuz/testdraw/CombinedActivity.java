package com.lifuz.testdraw;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

/**
 * 作者：李富 on 2015/9/30.
 * 邮箱：lifuzz@163.com
 */
public class CombinedActivity extends Activity {

    private CombinedChart combinedChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.combine_layout);




//        Log.i("tag", combinedChart.toString());
        initChart();

    }

    private void initChart() {

        combinedChart = (CombinedChart) findViewById(R.id.cbmp_chart);
        combinedChart.setEnabled(true);

        combinedChart.setDescription("");
        combinedChart.setNoDataText("");

        combinedChart.setBackgroundColor(Color.WHITE);
        combinedChart.setDrawGridBackground(false);
        combinedChart.setDrawBarShadow(false);

        // draw bars behind lines
        combinedChart.setDrawOrder(new CombinedChart.DrawOrder[] {
                CombinedChart.DrawOrder.BAR, CombinedChart.DrawOrder.BUBBLE, CombinedChart.DrawOrder.CANDLE, CombinedChart.DrawOrder.LINE, CombinedChart.DrawOrder.SCATTER
        });

//        combinedChart.set
//        combinedChart.setDrawMarkerViews();



        setdata();
    }

    private void setdata() {


        ArrayList<String> xVal = new ArrayList<>();
        ArrayList<Entry> yVal1 = new ArrayList<>();

        ArrayList<BarEntry> yVal2 = new ArrayList<>();

        for (int i =0 ;i <5 ;i++) {
            xVal.add(i+"");
            yVal1.add(new Entry(5,i));
            yVal2.add(new BarEntry(i +3,i));
        }

        BarDataSet barDataSet = new BarDataSet(yVal2,"tongji");
        BarData barData = new BarData(xVal,barDataSet);
        LineDataSet lineDataSet = new LineDataSet(yVal1,"biaozhun");
        lineDataSet.setDrawCircles(false);
        lineDataSet.setHighLightColor(Color.RED);
        //设置是否显示值
        lineDataSet.setDrawValues(false);
        lineDataSet.setDrawCubic(false);
//        lineDataSet.setHighLightColor(0);
        lineDataSet.setHighlightEnabled(false);
//        lineDataSet.setAxisDependency(YAxis.AxisDependency.RIGHT);

//        lineDataSet.setDrawHorizontalHighlightIndicator(true);

//        lineDataSet.setDrawFilled(true);

        LineData lineData = new LineData(xVal,lineDataSet);





        CombinedData combinedData = new CombinedData(xVal);
        combinedData.setData(barData);
        combinedData.setData(lineData);

        LimitLine ll = new LimitLine(10f);
        ll.setLabelPosition(LimitLine.LimitLabelPosition.LEFT_BOTTOM);
//        combinedChart.setVisibleXRangeMaximum(10f);


        combinedChart.setData(combinedData);
        combinedChart.animateX(10);

    }
}
