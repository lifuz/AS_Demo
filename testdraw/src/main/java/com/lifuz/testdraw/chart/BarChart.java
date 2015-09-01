package com.lifuz.testdraw.chart;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.lifuz.testdraw.R;
import com.lifuz.testdraw.bean.Chart;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.SeriesSelection;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 作者：李富 on 2015/8/25.
 * 邮箱：lifuzz@163.com
 */
public class BarChart extends Activity {

    private RequestQueue queue;
    private List<Chart> charts;
    private LinearLayout count_chart;
    private GraphicalView chartView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.count_layout);
        init();

    }

    public void init() {
        queue = Volley.newRequestQueue(this);

        count_chart = (LinearLayout) findViewById(R.id.count_chart);


        Log.d("tag", "cehsi");
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(getResources().getString(R.string.count_url)
                , new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {

//                Log.i("tag",jsonArray.toString());

                charts = new ArrayList<>();
                Chart chart = null;

                for (int i = 0; i < jsonArray.length(); i++) {

                    chart = new Chart();

                    try {
                        JSONObject object = jsonArray.getJSONObject(i);

                        chart.setDate(object.getString("date"));
                        chart.setCount(object.getString("count"));
                        charts.add(chart);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

                Collections.reverse(charts);
                Log.d("tag",charts.toString());


                chartView = ChartFactory.getBarChartView(BarChart.this, getDataSet(), getRenderer(),
                        org.achartengine.chart.BarChart.Type.DEFAULT);

                count_chart.addView(chartView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));

                chartView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SeriesSelection seriesSelection = chartView.getCurrentSeriesAndPoint();

                        if (seriesSelection != null) {
                            Toast.makeText(BarChart.this,seriesSelection.getXValue() + "",Toast.LENGTH_SHORT).show();

                        }
                    }
                });



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("tag", volleyError.getMessage() + "  ");
                volleyError.printStackTrace();

            }
        });

        queue.add(jsonArrayRequest);

    }

    /**
     * 构造数据
     *
     * @return
     */
    public XYMultipleSeriesDataset getDataSet() {

        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        CategorySeries series = new CategorySeries("统计图");

        for (Chart chart : charts) {
            series.add(Double.parseDouble(chart.getCount()));
        }

        dataset.addSeries(series.toXYSeries());

        return dataset;
    }

    /**
     * 构造渲染器
     *
     * @return
     */
    public XYMultipleSeriesRenderer getRenderer() {

        XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
//        renderer.setXTitle("日期");
//        renderer.setYTitle("总数");
        renderer.setAxesColor(Color.BLACK);
        renderer.setLabelsColor(Color.BLACK);

//        renderer.setPanEnabled(false);
        // 设置X轴的最小数字和最大数字



        renderer.setXAxisMin(0);
        renderer.setXAxisMax(5);
        // 设置Y轴的最小数字和最大数字
        renderer.setYAxisMin(0);
        renderer.setYAxisMax(10000);

        for (int i = 1; i <= charts.size(); i++) {
            renderer.addXTextLabel(i, charts.get(i - 1).getDate());
        }


        renderer.setZoomButtonsVisible(false);
        // 设置渲染器允许放大缩小
        renderer.setZoomEnabled(false,false);
        // 消除锯齿
        renderer.setAntialiasing(true);
        // 设置背景颜色
        renderer.setApplyBackgroundColor(true);
        renderer.setBackgroundColor(Color.BLUE);

        // 设置每条柱子的颜色
        SimpleSeriesRenderer sr = new SimpleSeriesRenderer();
        sr.setColor(Color.CYAN);
        renderer.addSeriesRenderer(sr);
        // 设置每个柱子上是否显示数值
        renderer.getSeriesRendererAt(0).setDisplayChartValues(true);
        renderer.getSeriesRendererAt(0).setChartValuesTextSize(40);
        renderer.getSeriesRendererAt(0).setChartValuesTextAlign(Paint.Align.RIGHT);
//        renderer.setChartTitleTextSize(40);
        // X轴的近似坐标数  (这样不显示横坐标)
        renderer.setXLabels(0);
        // Y轴的近似坐标数
        renderer.setYLabels(6);
        // 刻度线与X轴坐标文字左侧对齐
        renderer.setXLabelsAlign(Paint.Align.CENTER);
        // Y轴与Y轴坐标文字左对齐
        renderer.setYLabelsAlign(Paint.Align.LEFT);
        // 允许左右拖动,但不允许上下拖动.
        renderer.setPanEnabled(true, false);
        // 柱子间宽度
        renderer.setBarSpacing(0.01f);
        // 设置X,Y轴单位的字体大小
        renderer.setAxisTitleTextSize(20);
        renderer.setLabelsTextSize(30);
        renderer.setXLabelsColor(Color.BLACK);
        renderer.setMarginsColor(Color.WHITE);
        renderer.setMargins(new int[]{0, 0, 0, 0});
        renderer.setClickEnabled(true);
//        renderer.setLegendHeight(0);
        renderer.setShowLegend(false);
//        renderer.setShowLabels(false);

//        renderer.setPointSize(0);
//        renderer.setPanEnabled(false);

        return renderer;



    }


}
