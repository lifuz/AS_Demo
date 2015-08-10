package com.lifuz.testwaterfall;

import android.app.ActionBar;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lifuz.testwaterfall.bean.TaskParam;
import com.lifuz.testwaterfall.image.ImageLoaderTask;
import com.lifuz.testwaterfall.widget.LazyScrollView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private LazyScrollView waterfall_scroll;
    private LinearLayout waterfall_container;
    private Display display;

    private int column_count = 3; //显示列数
    private int itemWidth; //每列的宽度；

    private AssetManager assetManager;//assets文件夹管理器

    private int page_count = 15;//每次加载多少张图片
    private int current_page = 0;//目前的页数
    private List<String> list_image;//图片路径集合

    private ArrayList<LinearLayout> waterfall_items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = this.getWindowManager().getDefaultDisplay();

        itemWidth = display.getWidth() / column_count;//根据屏幕大小计算每列大小

        assetManager = this.getAssets();//获取Assets文件夹管理器

        initLayout();
    }

    private void initLayout() {

        waterfall_scroll = (LazyScrollView) findViewById(R.id.waterfall_scroll);
        waterfall_scroll.getView();
        waterfall_scroll.setOnScrollListener(new LazyScrollView.OnScrollListener() {
            @Override
            public void onBotom() {

                addItemToContainer(++current_page, page_count);
            }

            @Override
            public void onTop() {

                Log.d("tag", "Scroll to top");

            }

            @Override
            public void onScroll() {

                Log.d("tag", "Scroll");

            }
        });

        waterfall_container = (LinearLayout) findViewById(R.id.waterfall_container);
        //这个list容器放的是LinearLayout，这些Linearlayout里主要放ImageView
        waterfall_items = new ArrayList<>();
        //根据列数确定放到waterfall_container里的LinearLayout数量，即每个LinearLayout都充当一列
        for (int i = 0; i < column_count; i++) {

            //定义一个LinearLayout布局
            LinearLayout itemLayout = new LinearLayout(this);
            //定义布局的高度和宽度
            LinearLayout.LayoutParams itemParam = new LinearLayout.LayoutParams(itemWidth,
                    ActionBar.LayoutParams.WRAP_CONTENT);

            //设置组件的间隔
            itemLayout.setPadding(2,2,2,2);

            //设置线性布局的方向，这里设置是垂直方向
            itemLayout.setOrientation(LinearLayout.VERTICAL);

            //设置布局的高度和宽度
            itemLayout.setLayoutParams(itemParam);
            waterfall_items.add(itemLayout);
            //把布局放入waterfall_container中
            waterfall_container.addView(itemLayout);


        }

        try {
            //获取图片资源
            list_image = Arrays.asList(assetManager.list("images"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //第一次加载
        addItemToContainer(current_page,page_count);

    }

    private void addItemToContainer(int pageIndex, int pageCount) {

        int j = 0;
        int imageCount = list_image.size();

        for (int i = pageIndex * pageCount; i < pageCount * (pageIndex + 1) && i < imageCount; i++) {
            //判断这张图片在一行的什么位置
            j = j >= column_count ? 0 : j;
            addImage(list_image.get(i), j++);
        }

    }

    private void addImage(String fileName, int columnIndex) {
        //获取图片的布局
        ImageView item = (ImageView) LayoutInflater.from(this).inflate(R.layout.waterfall_item, null);
        //把图片的imageView放到，相应位置的LinearLayout中
        waterfall_items.get(columnIndex).addView(item);

        TaskParam param = new TaskParam();
        param.setAssetManager(assetManager);
        param.setFileName(fileName);
        param.setItemWidth(itemWidth);

        //选择加载图片的View
        ImageLoaderTask task = new ImageLoaderTask(item);

        //异步加载图片
        task.execute(param);

    }


}
