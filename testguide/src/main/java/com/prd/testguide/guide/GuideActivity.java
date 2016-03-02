package com.prd.testguide.guide;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.prd.testguide.MainActivity;
import com.prd.testguide.R;
import com.prd.testguide.utils.SharePreferencesUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * app导引页面
 * <p/>
 * 作者：李富 on 2016/3/2.
 * 邮箱：lifuzz@163.com
 */
public class GuideActivity extends Activity {

    private int pageCount = 3;

    /**
     * 引导页小点
     */
    private LinearLayout linear_guide_dots;
    /**
     * viewPgaer控件，显示各种图片
     */
    private ViewPager guide_pager;

    /**
     * 适配器数据源
     */
    private List<View> views;
    /**
     * 底部图片
     */
    private ImageView[] dots;

    private GuidePagerAdapter guidePagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_guide);

        initData();

        initLayout();


    }

    private void initLayout() {

        LayoutInflater inflater = LayoutInflater.from(this);

        for (int i = 0; i < pageCount; i++) {

            View view = inflater.inflate(R.layout.guide_content, null);
            TextView tv = (TextView) view.findViewById(R.id.content_page);
            tv.setText("第" + i + "页");

            views.add(view);

        }

        guidePagerAdapter = new GuidePagerAdapter(views);

        guide_pager.setAdapter(guidePagerAdapter);

        dots[0].setEnabled(false);

        guide_pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                clearDots();

                dots[position].setEnabled(false);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    public void clearDots() {

        for (ImageView iv : dots) {
            iv.setEnabled(true);
        }
    }

    private void initData() {
        linear_guide_dots = (LinearLayout) findViewById(R.id.linear_guide_dots);
        guide_pager = (ViewPager) findViewById(R.id.guide_pager);

        dots = new ImageView[pageCount];

        dots[0] = (ImageView) linear_guide_dots.getChildAt(0);
        dots[1] = (ImageView) linear_guide_dots.getChildAt(1);
        dots[2] = (ImageView) linear_guide_dots.getChildAt(2);

        views = new ArrayList<>();
    }


    private class GuidePagerAdapter extends PagerAdapter {

        /**
         * 界面列表
         */
        private List<View> views = null;

        public GuidePagerAdapter(List<View> views) {
            this.views = views;
        }

        @Override
        public int getCount() {
            return views.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            container.addView(views.get(position));

            if (position == views.size() - 1) {


                Log.e("tag", position + "");

                Button content_btn = (Button) views.get(position).findViewById(R.id.content_btn);
                content_btn.setVisibility(View.VISIBLE);
                content_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        SharePreferencesUtils.setFirst(GuideActivity.this);
                        startActivity(new Intent(GuideActivity.this, MainActivity.class));
                        finish();
                    }
                });
            }

            return views.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {


            container.removeView(views.get(position));
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {

            return (view == object);
        }
    }
}
